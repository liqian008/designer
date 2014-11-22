package com.bruce.designer.util;

import org.apache.commons.lang3.StringUtils;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.User;


public class UserUtil {

	/**
	 * 游客身份的userId
	 */
	public static final int GUEST_ID = 100000;

	public static boolean isGuest(int userId) {
		return userId <= GUEST_ID;
	}
	
	public static boolean notGuest(int userId) {
		return !isGuest(userId);
	}
	
	
	/**
     * 获取头像链接
     * @param user
     * @param avatarType
     * @return
     */
    public static String getAvatarUrl(User user, String avatarType){
    	if(user!=null&&!StringUtils.isBlank(user.getHeadImg())){
    		return getAvatarUrl(user.getHeadImg(), avatarType);
    	}
    	return "";
    }
	
    
    /**
     * 获取头像链接
     * @param user
     * @param avatarType
     * @return
     */
    public static String getAvatarUrl(String avatarUrl, String avatarType){
    	int width = 200;
    	if(ConstService.UPLOAD_IMAGE_SPEC_MEDIUM.equals(avatarType)){//中头像
    		width = 100;
    	}else if(ConstService.UPLOAD_IMAGE_SPEC_SMALL.equals(avatarType)){//小头像
    		width = 50;
    	}
    	if(!StringUtils.isBlank(avatarUrl)){
    		return QiniuUtil.getScaleImage(avatarUrl, width);
    	}
    	return "";
    }
	
	
}
