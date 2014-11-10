package com.bruce.designer.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 七牛图片处理工具类
 * @author liqian
 *
 */
public class QiniuUtil {

	
	/**
	 * 缩放图片
	 * @param imageUrl
	 * @param width
	 * @return
	 */
	public static String getScaleImage(String imageUrl, int width){
    	return getScaleImage(imageUrl, width, 0);
    }

	 
    public static String getScaleImage(String imageUrl, int width, int height){
    	if(!StringUtils.isBlank(imageUrl)&&!imageUrl.contains("?")){
    		StringBuilder sb = new StringBuilder(imageUrl);
    		if(width>0){
    			sb.append("?imageView/2/w/"+width);
    			if(height>0){
    				sb.append("/h/"+height);	
    			}
    		}
    		return sb.toString();
    	}
    	return imageUrl;
    }

}
