package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.cache.counter.AbstractHotCache;
import com.bruce.designer.cache.counter.HotAlbumCache;
import com.bruce.designer.cache.counter.HotDesignerCache;
import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.dao.ITagDao;
import com.bruce.designer.dao.impl.AlbumActionLogDaoImpl;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;

@Service
public class HotServiceImpl implements IHotService, InitializingBean {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AbstractHotCache.class);

	
    
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
	@Autowired
	private HotAlbumCache hotAlbumCache;
	@Autowired
	private HotDesignerCache hotDesignerCache;
	
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
    public List<Album> fallLoadHotAlbums(int mode) {
        List<CountCacheBean> countList = null;
        //先获取相应idList
        switch (mode) {
            case WEEKLY_FLAG: {
                countList = albumActionLogDao.realtimeWeeklyTopAlbums(HOT_ALBUM_WEEKLY_LIMIT);
                break;
            }
            case MONTHLY_FLAG: {
                countList = albumActionLogDao.realtimeMonthlyTopAlbums(HOT_ALBUM_MONTHLY_LIMIT);
                break;
            }
            default: {//default daily
                countList = albumActionLogDao.realtimeDailyTopAlbums(HOT_ALBUM_DAILY_LIMIT);
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
        }
        return albumList;
    }
    
    
    @Override
    public List<User> fallLoadHotDesigners(int mode) {
        List<CountCacheBean> countList = null;
        //先获取相应idList
        switch (mode) {
            case WEEKLY_FLAG: {
                countList = albumActionLogDao.realtimeWeeklyTopDesigners(HOT_ALBUM_WEEKLY_LIMIT);
                break;
            }
            case MONTHLY_FLAG: {
                countList = albumActionLogDao.realtimeMonthlyTopDesigners(HOT_ALBUM_MONTHLY_LIMIT);
                break;
            }
            default: {//default daily
                countList = albumActionLogDao.realtimeDailyTopDesigners(HOT_ALBUM_DAILY_LIMIT);
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
        }
        return designerList;
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
}
