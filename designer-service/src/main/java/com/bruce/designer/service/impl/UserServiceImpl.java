package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.bean.User;
import com.bruce.designer.bean.UserCriteria;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.UserMapper;
import com.bruce.designer.service.UserService;
import com.bruce.designer.service.oauth.IAccessTokenService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IAccessTokenService accessTokenService;
//	@Autowired
//	private DesignerMapper designer;

	public int save(User t) {
		return userMapper.insert(t);
	}

	public List<User> queryAll() {
		return userMapper.selectByExample(null);
	}

	public int updateById(User t) {
		return userMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public User loadById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 用户认证
	 */
	public User authUser(String username, String password) {
		UserCriteria criteria = new UserCriteria();
		criteria.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<User> userList = userMapper.selectByExample(criteria);
		if(userList!=null&&userList.size()==1){
		    User user = userList.get(0);
		    //加载并设置第三方绑定信息
		    List<AccessTokenInfo> accessTokenList = accessTokenService.queryByUserId(user.getId());
		    //user.setAccessTokenList(accessTokenList);
		    user.refreshTokenMap(accessTokenList);
		    //返回
		    return user;
		}
		return null;
	}
	
	/**
	 * 检查用户exists
	 * @param username
	 * @param nickname
	 * @param email
	 * @return
	 */
	@Override
    public boolean userExists(String username){
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(criteria);
        if(userList!=null&&userList.size()>0){
            return true;
        }
        return false;
    }
	
	@Override
    public int changePassword(int userId, String password) {
	    UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andIdEqualTo(userId);
        User user = new User();
        user.setPassword(password);
        return userMapper.updateByExampleSelective(user, criteria);
    }

	public List<User> queryUsersByStatus(short status) {
		UserCriteria criteria = new UserCriteria();
		criteria.createCriteria().andStatusEqualTo(status);
		return userMapper.selectByExample(criteria);
	}
	
	public List<User> queryDesignersByStatus(short status) {
		UserCriteria criteria = new UserCriteria();
		criteria.createCriteria().andStatusEqualTo(status).andDesignerStatusEqualTo(ConstService.DESIGNER_APPLY_PASSED);
		return userMapper.selectByExample(criteria);
	}
	
	/**
     * 提交审核
     */
	@Override
	public int apply4Designer(int userId) {
	    return designerApplyOp(userId, ConstService.DESIGNER_APPLY_SENT);
    }
	
	@Override
	public int designerDenied(int userId) {
        return designerApplyOp(userId, ConstService.DESIGNER_APPLY_DENIED);
    }
	
	/**
	 * 审核通过
	 */
	@Override
	public int designerApproval(int userId) {
        return designerApplyOp(userId, ConstService.DESIGNER_APPLY_PASSED);
    }
	
	/**
	 * 处理设计师申请操作
	 * @param userId
	 * @param operationType
	 * @return
	 */
	private int designerApplyOp(int userId, short operationType) {
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andIdEqualTo(userId);
        User user = new User();
        user.setDesignerStatus(operationType);
        return userMapper.updateByExampleSelective(user, criteria);
    }


}
