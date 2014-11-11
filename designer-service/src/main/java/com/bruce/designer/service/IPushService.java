package com.bruce.designer.service;

import java.util.Set;

public interface IPushService {
	
	/**
	 * push消息
	 * @param messageType 消息类型
	 * @param content 消息内容
	 * @param sourceId 源id
	 * @param toIdSet
	 * @return
	 */
	public int pushMessage(int messageType, String content, long sourceId, Set<Integer> toIdSet);
	
	/**
	 * push消息
	 * @param messageType 消息类型
	 * @param content 消息内容
	 * @param sourceId 源id
	 * @param toId
	 * @return
	 */
	public int pushMessage(int messageType, String content, long sourceId, int toId);
	
	
	
}
