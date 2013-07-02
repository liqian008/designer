package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.TbAlbumSlide;
import com.bruce.designer.bean.TbAlbumSlideCriteria;
import com.bruce.designer.dao.TbAlbumSlideMapper;
import com.bruce.designer.service.AlbumSlideService;

@Service
public class AlbumSlideServiceImpl implements AlbumSlideService {

	@Autowired
	private TbAlbumSlideMapper albumSlideMapper;

	public int save(TbAlbumSlide t) {
		return albumSlideMapper.insert(t);
	}

	public List<TbAlbumSlide> queryAll() {
		return albumSlideMapper.selectByExample(null);
	}

	public int updateById(TbAlbumSlide t) {
		return albumSlideMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return albumSlideMapper.deleteByPrimaryKey(id);
	}

	public TbAlbumSlide loadById(Integer id) {
		return albumSlideMapper.selectByPrimaryKey(id);
	}


	public List<TbAlbumSlide> querySlidesByAlbumId(int albumId) {
		TbAlbumSlideCriteria criteria = new TbAlbumSlideCriteria();
		criteria.setOrderByClause("id desc");
		criteria.createCriteria().andAlbumIdEqualTo(albumId);
		return albumSlideMapper.selectByExample(criteria);
	}

	

}
