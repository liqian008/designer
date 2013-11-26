/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.service;

import java.util.List;
import java.util.Map;

/**
 * 计数服务
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:07:34
 */
public interface ICounterService {

	/**
	 * 获取计数
	 * 
	 * @param key
	 * @return
	 */
	public long getCount(String key);

	/**
	 * 批量获取计数
	 * 
	 * @param keyList
	 * @return
	 */
	public Map<String, Long> mGetCounter(List<String> keyList);

	/**
	 * 计数加一
	 * 
	 * @param key
	 * @return
	 */
	public long increase(String key);

	/**
	 * 计数加num
	 * 
	 * @param key
	 * @param num
	 * @return
	 */
	public long increaseNum(String key, int num);

	/**
	 * 计数减一
	 * 
	 * @param key
	 * @return
	 */
	public long reduce(String key);

	/**
	 * 计数减num
	 * 
	 * @param namespace
	 * @param key
	 * @param num
	 * @return
	 */
	public long reduceNum(String key, int num);

	public long incrBrowser(int designerId, int albumId);

	public long incrBrowser(int designerId, int albumId, int number);

	public long incrComment(int designerId, int albumId);

	public long incrComment(int designerId, int albumId, int number);

	public long incrLike(int designerId, int albumId);

	public long incrLike(int designerId, int albumId, int number);

	/**
	 * 收藏计数
	 * 
	 * @param designerId
	 * @param albumId
	 */
	// public long favorite(int designerId, int albumId);
}
