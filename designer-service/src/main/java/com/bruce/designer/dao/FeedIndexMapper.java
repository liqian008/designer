package com.bruce.designer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface FeedIndexMapper {
    public int insert(@Param("targetId") int targetId, @Param("feedId") long feedId);
    public List<Long> getIndex(@Param("targetId")int targetId,@Param("offset") int offset,@Param("limit") int limit);
}
