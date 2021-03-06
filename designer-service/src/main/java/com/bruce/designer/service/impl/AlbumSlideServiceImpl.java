package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.AlbumSlide;
import com.bruce.designer.bean.AlbumSlideCriteria;
import com.bruce.designer.dao.AlbumSlideMapper;
import com.bruce.designer.service.AlbumSlideService;

@Service
public class AlbumSlideServiceImpl implements AlbumSlideService {
	
	@Autowired
	private AlbumSlideMapper albumSlideMapper;

	public int save(AlbumSlide t) {
		return albumSlideMapper.insert(t);
	}

	public List<AlbumSlide> queryAll() {
		return albumSlideMapper.selectByExample(null);
	}

	public int updateById(AlbumSlide t) {
		return albumSlideMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return albumSlideMapper.deleteByPrimaryKey(id);
	}

	public AlbumSlide loadById(Integer id) {
		return albumSlideMapper.selectByPrimaryKey(id);
	}


	public List<AlbumSlide> querySlidesByAlbumId(int albumId) {
		AlbumSlideCriteria criteria = new AlbumSlideCriteria();
		criteria.setOrderByClause("id desc");
		criteria.createCriteria().andAlbumIdEqualTo(albumId);
		return albumSlideMapper.selectByExample(criteria);
	}

	

}
