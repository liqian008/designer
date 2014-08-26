package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.IndexSlideCriteria;
import com.bruce.foundation.service.IFoundationService;


public interface IIndexSlideService extends IFoundationService<IndexSlide, Integer, IndexSlideCriteria> {

	public List<IndexSlide> queryIndexSlideList(int start, int limit);
	
}
