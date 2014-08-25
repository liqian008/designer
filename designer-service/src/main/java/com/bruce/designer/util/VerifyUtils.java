package com.bruce.designer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author liqian
 *
 */
public class VerifyUtils {
	
    //电子邮件  
    private static final String emailFormatter = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
    
    
    /**
     * 必须
     * @param username
     * @return
     */
	public static boolean verifyUsername(String username) {
	   return verifyEmail(username);
	}
	
	private static boolean verifyEmail(String input){
	    Pattern regex = Pattern.compile(emailFormatter);  
        Matcher matcher = regex.matcher(input);  
        return matcher.matches();
	}
	
	
	public static void main(String[] args) {
        System.out.println(verifyUsername("liqian008_1981@163.com"));
    }
}
