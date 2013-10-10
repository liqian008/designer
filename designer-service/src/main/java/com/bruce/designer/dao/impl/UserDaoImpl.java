package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IUserDao;
import com.bruce.designer.dao.mapper.UserMapper;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserCriteria;

@Repository
public class UserDaoImpl implements IUserDao , InitializingBean {

	@Autowired
	private UserMapper userMapper;

	public int save(User t) {
		return userMapper.insertSelective(t);
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
	@Override
    public User loadByNamePassword(String username, String password) {
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(criteria);
        if(userList!=null&&userList.size()==1){
            User user = userList.get(0);
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
	
	
	public List<User> queryUsers(List<Integer> userIds) {
	    UserCriteria userCriteria = new UserCriteria();
	    userCriteria.createCriteria().andIdIn(userIds);
        return userMapper.selectByExample(userCriteria);
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
     * 处理设计师申请操作
     * @param userId
     * @param operationType
     * @return
     */
    public int designerApplyOp(int userId, short operationType) {
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andIdEqualTo(userId);
        User user = new User();
        user.setDesignerStatus(operationType);
        return userMapper.updateByExampleSelective(user, criteria);
    }
	

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }
}
