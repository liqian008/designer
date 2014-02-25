package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.IndexSlide;


public interface IIndexSlideService extends IBaseService<IndexSlide, Integer> {

	public List<IndexSlide> queryIndexSlideList(int start, int limit);
	
}
