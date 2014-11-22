package com.bruce.designer.util;


public class DesignerLinkUtil {
	
	/*专辑链接模板，pc*/
	private static final String ALBUM_LINK_WEB_PREFIX = ConfigUtil.getString("album_link_web_prefix");
	/*专辑链接模板，mobile*/
	private static final String ALBUM_LINK_MOBILE_PREFIX = ConfigUtil.getString("album_link_mobile_prefix");
	
	/*用户链接模板，pc*/
	private static final String USER_LINK_WEB_PREFIX = ConfigUtil.getString("user_link_web_prefix");
	/*用户链接模板，mobile*/
	private static final String USER_LINK_MOBILE_PREFIX = ConfigUtil.getString("user_link_mobile_prefix");
	
	
	/**
	 * 获取pc上的专辑链接
	 * @param albumId
	 * @return
	 */
	public static String getAlbumLink4Web(int albumId){
		String link = String.format(ALBUM_LINK_WEB_PREFIX, albumId);
		return link;
	}
	
	
	/**
	 * 获取mobile上的专辑链接
	 * @param albumId
	 * @return
	 */
	public static String getAlbumLink4Mobile(int albumId){
		String link = String.format(ALBUM_LINK_MOBILE_PREFIX, albumId);
		return link;
	}
	
	/**
	 * 获取pc上的用户链接
	 * @param userId
	 * @return
	 */
	public static String getUserLink4Web(int userId){
		String link = String.format(USER_LINK_WEB_PREFIX, userId);
		return link;
	}
	
	
	/**
	 * 获取mobile上的用户链接
	 * @param userId
	 * @return
	 */
	public static String getUserLink4Mobile(int userId){
		String link = String.format(USER_LINK_MOBILE_PREFIX, userId);
		return link;
	}
	
	
	
	
	
	
	
//	public static String getUserHomeWebUrl(int userId){
//		return "";
//	}
//	
//	public static String getUserHomeMobileUrl(int userId){
//		return "";
//	}
//	
//	public static String getUserFansWebUrl(int userId){
//		return "";
//	}
//	
//	public static String getUserFansMobileUrl(int userId){
//		return "";
//	}
//	
//	public static String getUserFollowsWebUrl(int userId){
//		return "";
//	}
//	
//	public static String getUserFollowsMobileUrl(int userId){
//		return "";
//	}
//
//	public static String getAlbumInfoWebUrl(int albumId){
//		return "";
//	}
//
//	public static String getAlbumInfoMobileUrl(int albumId){
//		return "";
//	}
}
