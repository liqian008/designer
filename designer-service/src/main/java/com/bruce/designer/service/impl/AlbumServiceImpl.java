package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.Album;
import com.bruce.designer.bean.AlbumCriteria;
import com.bruce.designer.cache.album.AlbumCache;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.AlbumMapper;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ICounterService;

@Service
public class AlbumServiceImpl implements IAlbumService {

	@Autowired
	private AlbumMapper albumMapper;
	@Autowired
    private ICounterService counterService;
//	@Autowired
//    private AlbumCache albumCache;
	
	
	public int save(Album t) {
//		int result = albumMapper.insert(t);
//		if(result>0){
//		    albumCache.setAlbum(t);
//		}
//		return result;
	    return albumMapper.insert(t);
	}

	public int updateById(Album t) {
		return albumMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return albumMapper.deleteByPrimaryKey(id);
	}

	public Album loadById(Integer id) {
	    Album album = albumMapper.selectByPrimaryKey(id);
	    if(album!=null){
	        counterService.increase(ConstRedis.COUNTER_KEY_ALBUM_BROWSE+id);
	    }
	    return album;
	}

	public List<Album> queryAll() {
		return albumMapper.selectByExample(null);
	}
	
	public PagingData<Album> pagingQuery(short status, int pageNo, int pageSize){
		if(pageNo<0) pageNo = 1;
		int offset = (pageNo-1) * pageSize;
		AlbumCriteria criteria = new AlbumCriteria();
		criteria.createCriteria().andStatusEqualTo(status);
		criteria.setOffset(offset);
		criteria.setLimit(pageSize);
		criteria.setOrderByClause("id desc");
		List<Album> albumList = albumMapper.selectByExample(criteria);
		int totalCount = albumMapper.countByExample(criteria);//总条数
		PagingData<Album> pagingData = new PagingData<Album>(albumList, totalCount, pageNo, pageSize);
		return pagingData;
	}
	
	public List<Album> queryAlbumByStatus(short status) {
		AlbumCriteria criteria = new AlbumCriteria();
		criteria.createCriteria().andStatusEqualTo(status);
		criteria.setOrderByClause("id desc");
		return albumMapper.selectByExample(criteria);
	}

	public List<Album> queryAlbumByUserId(int userId) {
		AlbumCriteria criteria = new AlbumCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(ConstService.ALBUM_OPEN_STATUS);
		criteria.setOrderByClause("id desc");
		return albumMapper.selectByExample(criteria);
	}

}
