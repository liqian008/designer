package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.UserFollow;
import com.bruce.designer.model.UserFollowCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface IUserFollowDao extends IFoundationDao<UserFollow, Long, UserFollowCriteria> {

	public List<UserFollow> getFollowList(int userId);

	public int deleteFollow(int uid, int unfollowId);

}
