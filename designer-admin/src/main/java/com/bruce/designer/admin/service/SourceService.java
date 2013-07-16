package com.bruce.designer.admin.service;

import java.util.HashMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


public class SourceService implements InitializingBean{

	private static Logger logger = LoggerFactory.getLogger(SourceService.class);
	
	private HashMap<Integer, String> sourceMap;

	public HashMap<Integer, String> getSourceMap() {
		return sourceMap;
	}

	public void setSourceMap(HashMap<Integer, String> sourceMap) {
		this.sourceMap = sourceMap;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notEmpty(sourceMap);
	}
}
