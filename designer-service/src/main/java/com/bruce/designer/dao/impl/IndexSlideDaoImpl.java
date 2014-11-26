package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IIndexSlideDao;
import com.bruce.designer.dao.mapper.IndexSlideMapper;
import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.IndexSlideCriteria;

@Repository
public class IndexSlideDaoImpl implements IIndexSlideDao {

	@Autowired
	private IndexSlideMapper indexSlideMapper;

	public int save(IndexSlide t) {
		return indexSlideMapper.insertSelective(t);
	}

	public int updateById(IndexSlide t) {
		return indexSlideMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return indexSlideMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByCriteria(IndexSlide t, IndexSlideCriteria criteria) {
		return indexSlideMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteByCriteria(IndexSlideCriteria criteria) {
		return indexSlideMapper.deleteByExample(criteria);
	}

	public IndexSlide loadById(Integer id) {
		return indexSlideMapper.selectByPrimaryKey(id);
	}

	public List<IndexSlide> queryAll() {
		return indexSlideMapper.selectByExample(null);
	}

	@Override
	public List<IndexSlide> queryAll(String orderByClause) {
		IndexSlideCriteria criteria = new IndexSlideCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<IndexSlide> queryByCriteria(IndexSlideCriteria criteria) {
		return indexSlideMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(IndexSlideCriteria criteria) {
		return indexSlideMapper.countByExample(criteria);
	}

	@Override
	public List<IndexSlide> queryIndexSlideList(int start, int limit) {
		IndexSlideCriteria criteria = new IndexSlideCriteria();
		criteria.createCriteria();
		criteria.setLimitOffset(limit);
		criteria.setOrderByClause("sort desc");
		List<IndexSlide> indexSlideList = indexSlideMapper.selectByExample(criteria);
		return indexSlideList;
	}

}
