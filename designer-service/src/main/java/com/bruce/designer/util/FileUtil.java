/**
 * $Id: FileUtil.java 115105 2012-11-12 06:06:41Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * @author liqian
 *
 */
public class FileUtil {

    public static final String FILE_SEPARTOR = System.getProperty("file.separator");
    
    private static final String prefixUrl = "http://localhost:8080/designer-front/staticFile/";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    
    /**
     * 返回系统生成的文件名
     * @param userId
     * @param fileName
     * @param placeHolder
     * @param time
     * @return
     */
    public static String getFileNameWithPlaceHolder(int userId, String fileName, String placeHolder, long time) {
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);

        StringBuffer sb = new StringBuffer();
        sb.append(userId);
        sb.append("_");
        sb.append(Md5Utils.md5Encode(userId + "_" + time));
        if (StringUtils.isNotEmpty(placeHolder)) {
            sb.append("_");
            sb.append(placeHolder);
        }
        sb.append(".");
        sb.append(extName);

        return sb.toString();
    }

    public static String getFileName(int userId, String fileName) {
        return getFileNameWithPlaceHolder(userId, fileName, null, System.currentTimeMillis());
    }
    
    /**
   	 * 获取文件保存的base路径
   	 * @param userId
   	 * @return
   	 */
   	public static String getBasePath() {
   		String basePath = PropertiesUtil.getString("upload_path_base");
   		return basePath;
   	}
   	
   	
    public static String getBaseUrl() {
        String basePath = PropertiesUtil.getString("upload_url_base");
        return basePath;
    }
   	
   	private static String getDictionary() {
        return simpleDateFormat.format(new Date());
    }
   	
   	private static String getDictionary(long time) {
        return simpleDateFormat.format(new Date(time));
    }

    /**
     * 获取文件保存的相对路径
     * @param userId
     * @return
     */
    public static String getFilePath() {
        String filePath = PropertiesUtil.getString("upload_path_file") + FileUtil.FILE_SEPARTOR + getDictionary() + FileUtil.FILE_SEPARTOR;;
        return filePath;
    }
    
    /**
	 * 获取用户头像保存的相对路径
	 * @param userId
	 * @return
	 */
	public static String getAvatarPath() {
		String avatarPath = PropertiesUtil.getString("upload_path_avatar") + FileUtil.FILE_SEPARTOR;
		return avatarPath;
	}
	
	/**
	 * 获取用户图片保存的相对路径
	 * @param userId
	 * @return
	 */
	public static String getImagePath(long time) {
		String imagePath = PropertiesUtil.getString("upload_path_image") + FileUtil.FILE_SEPARTOR + getDictionary(time) + FileUtil.FILE_SEPARTOR;
		return imagePath;
	}
	
	/**
     * 获取用户图片保存的相对路径
     * @param userId
     * @return
     */
    public static String getImagePath() {
        return getImagePath(System.currentTimeMillis());
    }
    
	
	/**
	 * 保存文件，返回url
	 * @param data
	 * @param basePath
	 * @param dictionary
	 * @param filename
	 * @return
	 * @throws IOException
	 */
    public static String saveFile(byte[] data, String basePath, String dictionary, String filename) throws IOException{
    	
        File dir = new File(basePath + FileUtil.FILE_SEPARTOR + dictionary);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir, filename);
    	FileCopyUtils.copy(data, file);
		return prefixUrl  + FileUtil.FILE_SEPARTOR + dictionary + FileUtil.FILE_SEPARTOR + filename;
    }
    
}
