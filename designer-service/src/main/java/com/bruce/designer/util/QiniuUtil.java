package com.bruce.designer.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 七牛图片处理工具类
 * @author liqian
 *
 */
public class QiniuUtil {

	
//	/**
//	 * 缩放图片
//	 * @param imageUrl
//	 * @param width
//	 * @return
//	 */
//	public static String getScaleImage(String imageUrl, int width){
//    	return getMode2ScaleImage(imageUrl, width, 0);
//    }
	
	/**
	 * 头像缩放
	 * @param imageUrl
	 * @param width
	 * @return
	 */
	public static String getAvatarScaleImage(String imageUrl, int width){
		return getMode1ScaleImage(imageUrl, width, width);
    }
	
	
	/**
	 * 七牛mode1图片剪裁（进行等比缩放，居中裁剪）
	 * 文档链接：http://developer.qiniu.com/docs/v6/api/reference/fop/image/imageview2.html
	 * @param imageUrl
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getMode1ScaleImage(String imageUrl, int width, int height){
		return getScaleImageByMode(1, imageUrl, width, height);
    }
	 
	
	/**
	 * 七牛mode1图片剪裁（进行等比缩放，不裁剪）
	 * 文档链接：http://developer.qiniu.com/docs/v6/api/reference/fop/image/imageview2.html
	 * @param imageUrl
	 * @param width
	 * @param height
	 * @return
	 */
    public static String getMode2ScaleImage(String imageUrl, int width, int height){
    	return getScaleImageByMode(2, imageUrl, width, height);
    }
    
    private static String getScaleImageByMode(int mode, String imageUrl, int width, int height){
    	if(!StringUtils.isBlank(imageUrl)&&!imageUrl.contains("?")){
    		StringBuilder sb = new StringBuilder(imageUrl);
    		if(width>0){
    			sb.append("?imageView/"+mode+"/w/"+width);
    			if(height>0){
    				sb.append("/h/"+height);	
    			}
    		}
    		return sb.toString();
    	}
    	return imageUrl;
    }
    
    

}
