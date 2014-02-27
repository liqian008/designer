package com.bruce.designer.front.util;

import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;


/**
 * 
 * @author liqian
 *
 */
public class VerifyUtils {
	
	public static boolean verifyUsername(String username) {
		//验证登录名格式为email
		boolean usernameVerify = true;
		if(!usernameVerify){
			//格式错误
			throw new DesignerException(ErrorCode.USER_USERNAME_FORMAT_ERROR); 
		}
		return true;
	}
}
