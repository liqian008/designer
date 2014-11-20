package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.dao.ITagDao;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.TimeUtil;

@Service
public class HotServiceImpl implements IHotService, InitializingBean {
	

	@Autowired
	private ITagDao tagDao;
	@Autowired
    private IAlbumActionLogDao albumActionLogDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private ITagAlbumService tagAlbumService;
	
	/*热门专辑的缓存cache*/
	private Map<Short, List<Album>> hotAlbumMap = new ConcurrentHashMap<Short, List<Album>>();
	/*热门专辑的缓存时间cache*/
	private Map<Short, Long> timeAlbumMap = new ConcurrentHashMap<Short, Long>();
	
	/*热门设计师的缓存cache*/
	private Map<Short, List<User>> hotDesignerMap = new ConcurrentHashMap<Short, List<User>>();
	/*热门设计师的缓存时间cache*/
	private Map<Short, Long> timeDesignerMap = new ConcurrentHashMap<Short, Long>();
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(HotServiceImpl.class);
	
	private List<Tag> hotTagList = null; 

	
	// TODO 同步处理&优化
	@Override
	public List<Tag> getHotTags(int limit) {
		if(hotTagList==null||hotTagList.size()<=0){
			List<Tag> calcTagList = calcHotTags(limit);
			hotTagList = calcTagList;
		}
		return hotTagList;
	}
	
	// TODO 同步处理&优化
	@Override
	public List<Tag> calcHotTags(int limit) {
		List<Tag> calcTagList = tagDao.calcHotTags(limit);
		if(calcTagList!=null){
			hotTagList = calcTagList;
		}
		return hotTagList;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public List<Album> fallLoadHotAlbums(int start, int limit) {
//		List<Integer> albumIdList = null;
//		try {
//			albumIdList = hotAlbumCache.getHotList(start, limit);
//			if(albumIdList!=null&&albumIdList.size()>0){
//				List<Album> albumList = albumService.queryAlbumByIds(albumIdList);
//				albumService.initAlbumsWithCount(albumList);
//				albumService.initAlbumsWithTags(albumList);
//				return albumList;
//			}
//		} catch (RedisKeyNotExistException e) {
//			logger.error("hot album key not found!", e);
//		}
//		return null;
//	}
	
    @Override
    public List<Album> fallLoadHotAlbums(short mode, int limit) {
    	
    	Long cachedTime = timeAlbumMap.get(mode);//取缓存的时间
    	List<Album> hotAlbumList = hotAlbumMap.get(mode);//取缓存中的hotAlbum
    	long currentTime = System.currentTimeMillis();//当前时间
		boolean cacheExpired = checkCacheExpired(mode, cachedTime, currentTime);
		//过期或空数据都要重新加载
    	if(cacheExpired || hotAlbumList==null||hotAlbumList.size()<=0){
    		List<CountCacheBean> countList = null;
            //先获取相应idList
            switch (mode) {
            	case WEEKLY_FLAG: {
                    countList = albumActionLogDao.realtimeWeeklyTopAlbums(limit);//HOT_ALBUM_WEEKLY_LIMIT);
                    break;
                }
                case MONTHLY_FLAG: {
                    countList = albumActionLogDao.realtimeMonthlyTopAlbums(limit);//HOT_ALBUM_MONTHLY_LIMIT);
                    break;
                }
                case YEARLY_FLAG: {
                    countList = albumActionLogDao.realtimeMonthlyTopAlbums(limit);//HOT_ALBUM_MONTHLY_LIMIT);
                    break;
                }
                default: {//default daily
                    countList = albumActionLogDao.realtimeDailyTopAlbums(limit);//HOT_ALBUM_DAILY_LIMIT);
                    break;
                }
            }
            List<Album> albumList = null;
            if(countList!=null&&countList.size()>0){
                List<Integer> albumIdList = new ArrayList<Integer>();
                for(CountCacheBean countBean: countList){
                    albumIdList.add(countBean.getMember());
                }
                albumList = albumService.queryAlbumByIds(albumIdList);
                //加载数据项
                albumService.initAlbumsWithCount(albumList);
                albumService.initAlbumsWithTags(albumList);
                timeAlbumMap.put(mode, currentTime);//缓存time
                hotAlbumMap.put(mode, albumList);//缓存list
                return albumList;
            }
    	}
    	//加载数据项
        albumService.initAlbumsWithCount(hotAlbumList);
        albumService.initAlbumsWithTags(hotAlbumList);
    	return hotAlbumList;
    }
    
    
	@Override
    public List<User> fallLoadHotDesigners(short mode, int limit) {
		Long cachedTime = timeDesignerMap.get(mode);//取缓存的时间
    	List<User> hotAlbumList = hotDesignerMap.get(mode);//取缓存中的hotAlbum
    	long currentTime = System.currentTimeMillis();//当前时间
		boolean cacheExpired = checkCacheExpired(mode, cachedTime, currentTime);
		//过期或空数据都要重新加载
    	if(cacheExpired || hotAlbumList==null||hotAlbumList.size()<=0){
			List<CountCacheBean> countList = null;
	        //先获取相应idList
	        switch (mode) {
	            case WEEKLY_FLAG: {
	                countList = albumActionLogDao.realtimeWeeklyTopDesigners(limit);//HOT_ALBUM_WEEKLY_LIMIT);
	                break;
	            }
	            case MONTHLY_FLAG: {
	                countList = albumActionLogDao.realtimeMonthlyTopDesigners(limit);//HOT_ALBUM_MONTHLY_LIMIT);
	                break;
	            }
	            case YEARLY_FLAG: {
	                countList = albumActionLogDao.realtimeYearlyTopDesigners(limit);//HOT_ALBUM_YEARLY_LIMIT);
	                break;
	            }
	            default: {//default daily
	                countList = albumActionLogDao.realtimeDailyTopDesigners(limit);//HOT_ALBUM_DAILY_LIMIT);
	                break;
	            }
	        }
	        List<User> designerList = null;
	        if(countList!=null&&countList.size()>0){
	            List<Integer> designerIdList = new ArrayList<Integer>();
	            for(CountCacheBean countBean: countList){
	                designerIdList.add(countBean.getMember());
	            }
	            designerList = userService.queryUsersByIds(designerIdList);
	            timeDesignerMap.put(mode, currentTime);//缓存time
                hotDesignerMap.put(mode, designerList);//缓存list
                return designerList;
	        }
    	}
        return hotAlbumList;
    }
    
//	@Override
//	public List<User> fallLoadHotDesigners(int start, int limit) {
//		List<Integer> designerIdList = null;
//		try {
//			designerIdList = hotDesignerCache.getHotList(start, limit);
//			if(designerIdList!=null&&designerIdList.size()>0){
//				return userService.queryUsersByIds(designerIdList);
//			}
//		} catch (RedisKeyNotExistException e) {
//			logger.error("hot user key not found!", e);
//		}
//		return null;
//	}
	
	
	
	
	/**
	 * 
	 * @param mode
	 * @param cachedTime
	 * @param currentTime
	 * @return
	 */
    private boolean checkCacheExpired(short mode, Long cachedTime, long currentTime) {
    	if(cachedTime!=null){
    		long interval = currentTime - cachedTime;
    		long comparedTime = 0;
    		switch (mode) {
	    		case DAILY_FLAG: {
					 comparedTime = TimeUtil.TIME_UNIT_HOUR*2;//日热门，2H重新刷一次缓存
				     break;
				 }	 
	    		case WEEKLY_FLAG: {
					 comparedTime = TimeUtil.TIME_UNIT_DAY;//周热门，1D重新刷一次缓存
				     break;
				 }
				 case MONTHLY_FLAG: {
					 comparedTime = TimeUtil.TIME_UNIT_WEEK;//月热门，1W重新刷一次缓存
				     break;
				 }
				 case YEARLY_FLAG: {
					 comparedTime = TimeUtil.TIME_UNIT_MONTH;//年热门，1Y重新刷一次缓存
				     break;
				 }
				 default: {
				     break;
				 }
    		}
    		if(interval < comparedTime){//在缓存时间以内
    			return false;
    		}
    	}
		return true;
	}

}
