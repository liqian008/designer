package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IAlbumSlideDao;
import com.bruce.designer.dao.mapper.AlbumSlideMapper;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.AlbumSlideCriteria;

@Repository
public class AlbumSlideDaoImpl implements IAlbumSlideDao , InitializingBean {
	
	@Autowired
	private AlbumSlideMapper albumSlideMapper;

	public int save(AlbumSlide t) {
		return albumSlideMapper.insertSelective(t);
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

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

	

}
