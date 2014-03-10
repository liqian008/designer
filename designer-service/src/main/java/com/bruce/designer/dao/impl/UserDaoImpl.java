package com.bruce.designer.dao.impl;

import java.util.Date;
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
	 * 检查username是否存在
	 * @param username
	 * @return
	 */
	@Override
    public boolean usernameExists(String username){
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(criteria);
        if(userList!=null&&userList.size()>0){
            return true;
        }
        return false;
    }
	
	/**
	 * 检查nickname是否存在
	 * @param nickname
	 * @return
	 */
	@Override
    public boolean nicknameExists(String nickname){
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andNicknameEqualTo(nickname);
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
	
	/**
	 * 加载用户数据列表，取去正常用户
	 */
	public List<User> queryUsersByIds(List<Integer> userIds) {
	    UserCriteria userCriteria = new UserCriteria();
	    userCriteria.createCriteria().andIdIn(userIds).andStatusEqualTo(ConstService.USER_STATUS_OPEN);
        return userMapper.selectByExample(userCriteria);
    }
	

	@Override
	public List<User> queryUsersByStatus(short status) {
		UserCriteria criteria = new UserCriteria();
		criteria.createCriteria().andStatusEqualTo(status);
		return userMapper.selectByExample(criteria);
	}
	
	@Override
	public List<User> queryAllDesigners() {
		UserCriteria criteria = new UserCriteria();
		criteria.createCriteria().andDesignerStatusNotEqualTo((short)0);
		return userMapper.selectByExample(criteria);
	}
	
	@Override
	public List<User> queryDesignersByStatus(short designerStatus) {
		UserCriteria criteria = new UserCriteria();
		criteria.createCriteria().andDesignerStatusEqualTo(designerStatus).andDesignerStatusNotEqualTo((short)0);
		return userMapper.selectByExample(criteria);
	}
	
	
	/**
	 * 申请设计师
	 * @param userId
	 * @param idNum
	 * @param realname
	 * @param mobile
	 * @param company
	 * @param taobaoHomepage
	 * @return
	 */
    public int applyDesigner(int userId, String idNum, String realname, String mobile, String company, String taobaoHomepage) {
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andIdEqualTo(userId);
        User user = new User();
        user.setDesignerIdentifer(idNum);
        user.setDesignerRealname(realname);
        user.setDesignerMobile(mobile);
        user.setDesignerCompany(company);
        user.setDesignerTaobaoHomepage(taobaoHomepage);
        user.setDesignerStatus(ConstService.DESIGNER_APPLY_SENT);
        user.setDesignerApplyTime(new Date());
        return userMapper.updateByExampleSelective(user, criteria);
    }
    
	/**
     * 处理设计师操作
     * @param userId
     * @param operationType
     * @return
     */
    public int operateDesigner(int userId, short operationType) {
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andIdEqualTo(userId);
        User user = new User();
        user.setDesignerStatus(operationType);
        if(ConstService.DESIGNER_APPLY_APPROVED == operationType){
        	user.setDesignerPassTime(new Date());
        }
        return userMapper.updateByExampleSelective(user, criteria);
    }
    

	@Override
	public List<User> fallLoadDesignerList(long approvelTailTime, int limit) {
		UserCriteria criteria = new UserCriteria();
		UserCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andStatusEqualTo(ConstService.USER_STATUS_OPEN).andDesignerStatusEqualTo(ConstService.DESIGNER_APPLY_APPROVED);
		if(approvelTailTime>0){
			subCriteria.andDesignerApplyTimeLessThan(new Date(approvelTailTime));
		}
		criteria.setLimit(limit);
	    criteria.setOrderByClause("designer_pass_time desc");
        List<User> designerList = userMapper.selectByExample(criteria);
        return designerList;
	}
	

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

	@Override
	public List<User> fallLoadList(Integer tailId, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
