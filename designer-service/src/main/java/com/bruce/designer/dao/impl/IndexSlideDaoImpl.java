package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IIndexSlideDao;
import com.bruce.designer.dao.mapper.IndexSlideMapper;
import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.IndexSlideCriteria;

@Repository
public class IndexSlideDaoImpl implements IIndexSlideDao{

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

	public IndexSlide loadById(Integer id) {
	    return indexSlideMapper.selectByPrimaryKey(id);
	}

	public List<IndexSlide> queryAll() {
		return indexSlideMapper.selectByExample(null);
	}

	@Override
	public List<IndexSlide> queryIndexSlideList(int start, int limit) {
		IndexSlideCriteria criteria = new IndexSlideCriteria();
		criteria.createCriteria();
		criteria.setLimit(limit);
		criteria.setOrderByClause("sort desc");
        List<IndexSlide> indexSlideList = indexSlideMapper.selectByExample(criteria);
        return indexSlideList;
	}
	
	@Override
	public List<IndexSlide> fallLoadList(Integer tailId, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
