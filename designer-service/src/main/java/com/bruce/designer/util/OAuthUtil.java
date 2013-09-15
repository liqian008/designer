package com.bruce.designer.util;

public class OAuthUtil {

	public static String getSourceNameByType(short thirdpartyType){
		String thirdpartyName;
		switch(thirdpartyType){
			case 1: {
				thirdpartyName = "微博用户";
				break;
			}
			case 2: {
				thirdpartyName = "QQ用户";
				break;
			}
			case 3: {
				thirdpartyName = "人人用户";
				break;
			}
			default:{
				thirdpartyName = "未知";
				break;
			}
		}
		return thirdpartyName;
	}
	
}