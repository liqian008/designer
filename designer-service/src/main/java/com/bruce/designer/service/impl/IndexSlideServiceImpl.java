package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.IIndexSlideDao;
import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.IndexSlideCriteria;
import com.bruce.designer.service.IIndexSlideService;


@Service
public class IndexSlideServiceImpl implements IIndexSlideService{

	@Autowired
	private IIndexSlideDao indexSlideDao;

	@Override
	public int save(IndexSlide t) {
		return indexSlideDao.save(t);
	}

	@Override
	public int updateById(IndexSlide t) {
		return indexSlideDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return indexSlideDao.deleteById(id);
	}

	@Override
	public IndexSlide loadById(Integer id) {
		return indexSlideDao.loadById(id);
	}

	@Override
	public List<IndexSlide> queryAll() {
		return indexSlideDao.queryAll();
	}

	@Override
	public List<IndexSlide> queryIndexSlideList(int start, int limit) {
		return indexSlideDao.queryIndexSlideList(start, limit);
	}
	
	
	
	
	@Override
	public int updateByCriteria(IndexSlide t, IndexSlideCriteria criteria) {
		return indexSlideDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(IndexSlideCriteria criteria) {
		return indexSlideDao.deleteByCriteria(criteria);
	}

	@Override
	public List<IndexSlide> queryAll(String orderByClause) {
		return indexSlideDao.queryAll(orderByClause);
	}

	@Override
	public List<IndexSlide> queryByCriteria(IndexSlideCriteria criteria) {
		return indexSlideDao.queryByCriteria(criteria);
	}
	
	

}
