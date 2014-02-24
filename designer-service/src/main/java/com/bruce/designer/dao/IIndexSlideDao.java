package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.IndexSlide;


public interface IIndexSlideDao extends IBaseDao<IndexSlide, Integer>{ 
    
	/**
	 * 获取首页轮播列表
	 * @param limit
	 * @return
	 */
	public List<IndexSlide> queryIndexSlideList(int start, int limit);
	
} 
