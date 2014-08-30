package com.bruce.designer.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.upload.UploadImageInfo;
import com.bruce.designer.model.upload.UploadImageResult;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.util.HttpClientUtil;
import com.bruce.designer.util.ImageUtil;
import com.bruce.designer.util.UploadUtil;

@Service
public class UploadServiceImpl implements IUploadService {
	
    //Logger for this class
    private static final Logger logger = Logger.getLogger(UploadServiceImpl.class);
    
	// 文件存储的绝对路径
	public static final String basePath = UploadUtil.getBasePath();
	// 头像保存的相对路径
	public static final String avatarPath = UploadUtil.getAvatarPath();
	// 文件的baseUrl
	public static final String baseUrl = UploadUtil.getBaseUrl();
	// 头像保存的相对Url

	public static Map<String, Integer> imageSizeMap = new HashMap<String, Integer>();
	public static Map<String, Integer> avatarSizeMap = new HashMap<String, Integer>();

	static {
		imageSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_LARGE, 1024);
		imageSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_MEDIUM, 400);
		imageSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_SMALL, 200);

		avatarSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_LARGE, 200);
		avatarSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_MEDIUM, 100);
		avatarSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_SMALL, 50);
	}

	/**
	 * 保存文件
	 */
	@Override
	public String uploadFile(byte[] data, int userId, String fileName) throws IOException {
		String basePath = UploadUtil.getBasePath();
		String newFileName = UploadUtil.getFileName(userId, fileName);
		String fileUrl = UploadUtil.saveFile(data, basePath, UploadUtil.getImagePath(), newFileName);
		return fileUrl;
	}

	/**
	 * 上传图片，需按尺寸切割
	 * 
	 * @throws IOException
	 */
	@Override
	public UploadImageResult uploadImage(byte[] data, int userId, String filename) throws IOException {
		long time = System.currentTimeMillis();
		// 获取图片存储的绝对、相对路径及文件名
		String imageDirPath = UploadUtil.getImagePath(time);
		String absoultImagePath = basePath + imageDirPath;
		String imageName = UploadUtil.getFileNameWithPlaceHolder(userId, filename, null, time);

		// 保存原文件(自动创建目录)
//		UploadUtil.saveFile(data, basePath, imageDirPath, sourceImageName);
		
		String originalImageSpec = "original";
		String originalUrl = UploadUtil.saveFile(data, basePath, imageDirPath+ "/"+originalImageSpec,  imageName);

		// 构造uploadResult
		UploadImageResult uploadResult = new UploadImageResult();
		UploadImageInfo originalImage = new UploadImageInfo(imageName, ConstService.UPLOAD_FILE_TYPE_IMAGE, originalImageSpec, originalUrl, -1);
		uploadResult.setOriginalImage(originalImage);
		
		Set<String> keys = imageSizeMap.keySet();
		// 根据需要的尺寸进行zoom
		for (String imageSpec : keys) {
			int width = imageSizeMap.get(imageSpec);// 获取指定的尺寸
			String scaleImagePath = absoultImagePath+"/"+imageSpec;
			File scaleImageFile = new File(scaleImagePath);
			scaleImageFile.mkdirs();
			
			//图片缩放
//			String targetImageName = UploadUtil.getFileNameWithPlaceHolder(userId, filename, null, time);
			ImageUtil.scaleByWidth(absoultImagePath +"/"+originalImageSpec+"/"+ imageName, scaleImagePath+"/"+imageName, width);
			//水印处理
			String imageUrl = baseUrl + imageDirPath +"/"+imageSpec+"/"+ imageName;
			UploadImageInfo imageInfo = new UploadImageInfo(imageName, ConstService.UPLOAD_FILE_TYPE_IMAGE, imageSpec, imageUrl, -1);

			// 组装uploadResult
			if (ConstService.UPLOAD_IMAGE_SPEC_LARGE.equals(imageSpec)) {
				uploadResult.setLargeImage(imageInfo);
			} else if (ConstService.UPLOAD_IMAGE_SPEC_MEDIUM.equals(imageSpec)) {
				uploadResult.setMediumImage(imageInfo);
			} else if (ConstService.UPLOAD_IMAGE_SPEC_SMALL.equals(imageSpec)) {
				uploadResult.setSmallImage(imageInfo);
			}
		}
		return uploadResult;
	}

	public UploadImageResult uploadAvatar(byte[] data, int userId) throws IOException {
		// 获取头像存储的绝对、相对路径及文件名
		String avatarPath = UploadUtil.getAvatarPath();
		// 构造uploadResult
		UploadImageResult uploadResult = new UploadImageResult();
		
		Set<String> avatarSpecKeys = avatarSizeMap.keySet();
		// 根据需要的尺寸进行zoom
		for (String avatarSpec : avatarSpecKeys) {
			int widthSpec = avatarSizeMap.get(avatarSpec);
			byte[] resizeData = ImageUtil.zoom(data, widthSpec, widthSpec);
			String avatarFileName = userId +".jpg";
			//保存文件
			String avatarUrl = UploadUtil.saveFile(resizeData, basePath, avatarPath +"/"+ widthSpec , avatarFileName);
			UploadImageInfo imageInfo = new UploadImageInfo(avatarFileName, ConstService.UPLOAD_FILE_TYPE_AVATAR, avatarSpec, avatarUrl, -1, widthSpec, widthSpec);
			// 组装uploadResult
			if (ConstService.UPLOAD_IMAGE_SPEC_LARGE.equals(avatarSpec)) {
				uploadResult.setLargeImage(imageInfo);
			} else if (ConstService.UPLOAD_IMAGE_SPEC_MEDIUM.equals(avatarSpec)) {
				uploadResult.setMediumImage(imageInfo);
			} else if (ConstService.UPLOAD_IMAGE_SPEC_SMALL.equals(avatarSpec)) {
				uploadResult.setSmallImage(imageInfo);
			}
		}
		return uploadResult;
	}
	
	
	/**
	 * 
	 */
	public UploadImageResult uploadAvatarByUrl(String avatarUrl, int userId) throws IOException {
		if(!StringUtils.isBlank(avatarUrl)&&userId>0){
			byte[] bytes = HttpClientUtil.getBytesFromRemoteResource(avatarUrl);
			if(bytes!=null&&bytes.length>0){
				return uploadAvatar(bytes, userId);
			}
		}
		return new UploadImageResult();
	}

}
