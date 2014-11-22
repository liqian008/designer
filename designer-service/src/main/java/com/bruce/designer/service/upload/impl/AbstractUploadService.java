package com.bruce.designer.service.upload.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bruce.designer.constants.ConstConfig;
import com.bruce.designer.model.upload.UploadImageInfo;
import com.bruce.designer.model.upload.UploadImageResult;
import com.bruce.designer.service.upload.IUploadService;
import com.bruce.designer.util.HttpClientUtil;
import com.bruce.designer.util.UploadUtil;


public abstract class AbstractUploadService implements IUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadImageInfo.class);
	
//	/* 普通文件类型 */
//	public static final short UPLOAD_FILE_TYPE_NORMAL = 0;
//	/* 图片文件类型 */
//	public static final short UPLOAD_FILE_TYPE_IMAGE = 1;
	
//	// 文件存储的绝对路径
//	public static final String BASE_PATH = UploadUtil.getBasePath();
//	// 文件的baseUrl
//	public static final String BASE_URL = UploadUtil.getBaseUrl();
	

	/**
	 * 保存文件
	 */
	@Override
	public String uploadFile(byte[] data, String userId, String fileName) throws Exception {
		//先保存本地文件
		String newFileName = UploadUtil.getFileName(userId, fileName);
		String filePath = UploadUtil.getFilePath();//相对路径
		String absoultDirPath = ConstConfig.UPLOAD_PATH_BASE + filePath;//完整路径
		File destFile =  UploadUtil.saveFile(data, absoultDirPath, newFileName);
		
		return saveFile(destFile, filePath);
	}

	
	/**
	 * 上传图片，需按尺寸切割
	 * 
	 * @throws IOException
	 */
	@Override
	public UploadImageResult uploadImage(byte[] data, String userId, String filename, UploadImageInfo... imageSpecs) throws Exception {
		long time = System.currentTimeMillis();
		// 获取图片存储的绝对、相对路径及文件名
		String newImageName = UploadUtil.getFileNameWithPlaceHolder(userId, filename, null, time);
		String imageDirPath = UploadUtil.getImagePath(time);//相对路径
		String absoultImageDirPath = ConstConfig.UPLOAD_PATH_BASE + imageDirPath;//完整路径
		logger.debug("upload absoultImageDirPath: "+absoultImageDirPath);
		logger.debug("upload image rename to: "+newImageName);

		String originalImageSpec = "original";
		File originalImageFile = UploadUtil.saveFile(data, absoultImageDirPath + UploadUtil.FILE_SEPARTOR + originalImageSpec,  newImageName);
		
		return saveImage(originalImageFile, imageDirPath, imageSpecs);
	}
	
	/**
	 * 上传头像文件
	 */
	public UploadImageResult uploadAvatar(byte[] data, String userId, UploadImageInfo... imageSpecs) throws Exception {
		// 获取头像存储的绝对、相对路径及文件名
//		String avatarImageName = userId +".jpg";
//		String imageDirPath = UploadUtil.getImagePath(time);//相对路径
		String newAvatarImageName = UploadUtil.getFileNameWithPlaceHolder(userId, "avatar.jpg", null, System.currentTimeMillis());
		
		String avatarDirPath = UploadUtil.getAvatarPath();
		
		String absoultImageDirPath = ConstConfig.UPLOAD_PATH_BASE + avatarDirPath;//完整路径
		String originalImageSpec = "original";
		File originalImageFile = UploadUtil.saveFile(data, absoultImageDirPath + UploadUtil.FILE_SEPARTOR + originalImageSpec,  newAvatarImageName);
		return saveAvatarImage(originalImageFile, avatarDirPath, imageSpecs);
	}
	
	
	/**
	 * 处理网络头像图片
	 * @param avatarUrl
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	@Override
	public UploadImageResult uploadAvatarByUrl(String avatarUrl, String userId, UploadImageInfo... imageSpecs) throws Exception {
		if(!StringUtils.isBlank(avatarUrl)&&!StringUtils.isBlank(userId)){
			byte[] bytes = HttpClientUtil.getBytesFromRemoteResource(avatarUrl);
			if(bytes!=null&&bytes.length>0){
				return uploadAvatar(bytes, userId, imageSpecs);
			}
		}
		return new UploadImageResult();
	}
	
	

	protected abstract String saveFile(File destFile, String filePath) throws Exception;

	protected abstract UploadImageResult saveImage(File originalImageFile, String imageDirPath, UploadImageInfo[] imageSpecs) throws Exception;

	protected abstract UploadImageResult saveAvatarImage(File originalImageFile, String imageDirPath, UploadImageInfo[] imageSpecs) throws Exception;

}
