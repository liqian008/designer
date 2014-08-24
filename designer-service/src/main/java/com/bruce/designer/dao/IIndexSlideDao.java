package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.IndexSlideCriteria;
import com.bruce.foundation.dao.IFoundationDao;


public interface IIndexSlideDao extends IFoundationDao<IndexSlide, Integer, IndexSlideCriteria>{ 
    
	/**
	 * 获取首页轮播列表
	 * @param limit
	 * @return
	 */
	public List<IndexSlide> queryIndexSlideList(int start, int limit);
	
} 
