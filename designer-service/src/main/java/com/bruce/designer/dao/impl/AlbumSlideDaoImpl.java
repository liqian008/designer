package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstService;
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
		criteria.setOrderByClause("is_cover desc, id asc");
		criteria.createCriteria().andAlbumIdEqualTo(albumId);
		return albumSlideMapper.selectByExample(criteria);
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

	@Override
	public int clearCover(int userId, int albumId) {
		AlbumSlideCriteria criteria = new AlbumSlideCriteria();
		criteria.createCriteria().andAlbumIdEqualTo(albumId).andUserIdEqualTo(userId);
		AlbumSlide albumSlide = new AlbumSlide();
		albumSlide.setIsCover(ConstService.ALBUM_SLIDE_ISNOT_COVER);
		return albumSlideMapper.updateByExampleSelective(albumSlide, criteria);
	}

	@Override
	public int setCover(int userId, int albumId, int albumSlideId) {
		AlbumSlideCriteria criteria = new AlbumSlideCriteria();
		criteria.createCriteria().andIdEqualTo(albumSlideId).andUserIdEqualTo(userId).andAlbumIdEqualTo(albumId);
		AlbumSlide albumSlide = new AlbumSlide();
		albumSlide.setIsCover(ConstService.ALBUM_SLIDE_IS_COVER);
		return albumSlideMapper.updateByExampleSelective(albumSlide, criteria);
	}

	

	@Override
	public int updateByCriteria(AlbumSlide t, AlbumSlideCriteria criteria) {
		return albumSlideMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteByCriteria(AlbumSlideCriteria criteria) {
		return albumSlideMapper.deleteByExample(criteria);
	}

	@Override
	public List<AlbumSlide> queryAll(String orderByClause) {
		AlbumSlideCriteria criteria = new AlbumSlideCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<AlbumSlide> queryByCriteria(AlbumSlideCriteria criteria) {
		return albumSlideMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(AlbumSlideCriteria criteria) {
		return albumSlideMapper.countByExample(criteria);
	}

	@Override
	public int deleteByAlbumId(int albumId) {
		AlbumSlideCriteria criteria = new AlbumSlideCriteria();
		criteria.createCriteria().andAlbumIdEqualTo(albumId);
		return albumSlideMapper.deleteByExample(criteria);
	}

}
