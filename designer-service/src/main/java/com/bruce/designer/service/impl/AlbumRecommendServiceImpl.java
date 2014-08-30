package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.IAlbumRecommendDao;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumRecommend;
import com.bruce.designer.model.AlbumRecommendCriteria;
import com.bruce.designer.service.IAlbumRecommendService;
import com.bruce.designer.service.IAlbumService;


@Service
public class AlbumRecommendServiceImpl implements IAlbumRecommendService{
	
	@Autowired
	private IAlbumRecommendDao albumRecommendDao;
	@Autowired
	private IAlbumService albumService;

	@Override
	public int save(AlbumRecommend t) {
		return albumRecommendDao.save(t);
	}

	@Override
	public int updateById(AlbumRecommend t) {
		return albumRecommendDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return albumRecommendDao.deleteById(id);
	}

	@Override
	public AlbumRecommend loadById(Integer id) {
		return albumRecommendDao.loadById(id);
	}

	@Override
	public List<AlbumRecommend> queryAll() {
		return albumRecommendDao.queryAll();
	}


	@Override
	public List<Album> queryRecommendAlbums(int limit) {
		List<Integer> albumIdList = null;
		List<AlbumRecommend> dataList = albumRecommendDao.queryAll(limit);
		if(dataList!=null&&dataList.size()>0){
			albumIdList = new ArrayList<Integer>();
			for(AlbumRecommend albumRecommend: dataList){
				albumIdList.add(albumRecommend.getAlbumId());
			}
		}
		if(albumIdList!=null){
			List<Album> albumList = albumService.queryAlbumByIds(albumIdList);
			albumService.initAlbumsWithCount(albumList);
			return albumList;
		}
		return null;
	}
	
	
	@Override
	public int updateByCriteria(AlbumRecommend t, AlbumRecommendCriteria criteria) {
		return albumRecommendDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(AlbumRecommendCriteria criteria) {
		return albumRecommendDao.deleteByCriteria(criteria);
	}

	@Override
	public List<AlbumRecommend> queryAll(String orderByClause) {
		return albumRecommendDao.queryAll(orderByClause);
	}

	@Override
	public List<AlbumRecommend> queryByCriteria(AlbumRecommendCriteria criteria) {
		return albumRecommendDao.queryByCriteria(criteria);
	}
	
}
