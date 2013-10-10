package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.dao.IAlbumDao;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.model.Album;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ICounterService;

@Service
public class AlbumServiceImpl implements IAlbumService {

	@Autowired
	private IAlbumDao albumDao;
	@Autowired
    private ICounterService counterService;
	
	
	public int save(Album t) {
	    return albumDao.save(t);
	}

	public int updateById(Album t) {
		return albumDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return albumDao.deleteById(id);
	}

	public Album loadById(Integer id) {
	    Album album = albumDao.loadById(id);
	    if(album!=null){
	        counterService.increase(ConstRedis.COUNTER_KEY_ALBUM_BROWSE+id);
	    }
	    return album;
	}

	public List<Album> queryAll() {
		return albumDao.queryAll();
	}
	
	public PagingData<Album> pagingQuery(short status, int pageNo, int pageSize){
//		if(pageNo<0) pageNo = 1;
//		int offset = (pageNo-1) * pageSize;
//		AlbumCriteria criteria = new AlbumCriteria();
//		criteria.createCriteria().andStatusEqualTo(status);
//		criteria.setOffset(offset);
//		criteria.setLimit(pageSize);
//		criteria.setOrderByClause("id desc");
//		List<Album> albumList = albumDao.selectByExample(criteria);
//		int totalCount = albumDao.countByExample(criteria);//总条数
//		PagingData<Album> pagingData = new PagingData<Album>(albumList, totalCount, pageNo, pageSize);
//		return pagingData;
	    return null;
	}
	
	public List<Album> queryAlbumByStatus(short status) {
		return albumDao.queryAlbumByStatus(status);
	}

	public List<Album> queryAlbumByUserId(int userId) {
	    return albumDao.queryAlbumByUserId(userId);
	}

}
