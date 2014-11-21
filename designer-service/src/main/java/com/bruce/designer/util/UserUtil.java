package com.bruce.designer.util;


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
	
	
}
