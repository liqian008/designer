package com.bruce.designer.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.bruce.designer.bean.upload.UploadFileInfo;
import com.bruce.designer.bean.upload.UploadImageInfo;
import com.bruce.designer.bean.upload.UploadImageResult;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.util.FileUtil;
import com.bruce.designer.util.ImageUtil;

@Service
public class UploadServiceImpl implements IUploadService {
	
	public static Map<String, Integer> imageSizeMap = new HashMap<String, Integer>();
	public static Map<String, Integer> avatarSizeMap = new HashMap<String, Integer>();

	static{
		imageSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_LARGE, 1024);
		imageSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_MEDIUM, 400);
		imageSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_SMALL, 200);
		
		avatarSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_LARGE, 200);
		avatarSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_MEDIUM, 100);
		avatarSizeMap.put(ConstService.UPLOAD_IMAGE_SPEC_TINY, 50);
	}

	/**
	 * 保存文件
	 */
	@Override
	public String uploadFile(byte[] data, int userId, String fileName) throws IOException {
	    String basePath = FileUtil.getBasePath();
	    String newFileName = FileUtil.getFileName(userId, fileName); 
        String fileUrl = FileUtil.saveFile(data, basePath, FileUtil.getImagePath(), newFileName);
        return fileUrl; 
	}
	
	
	/**
	 * 上传图片，需按尺寸切割
	 * @throws IOException 
	 */
	@Override
	public UploadImageResult uploadImage(byte[] data, int userId, String filename) throws IOException {
		// 获取图片的保存路径
	    String basePath = FileUtil.getBasePath();
		long time = System.currentTimeMillis();
		//获取图片存储的绝对、相对路径及文件名
		String imageDirPath = FileUtil.getImagePath(time);
		String absoultImagePath = basePath + imageDirPath;
		String sourceImageName = FileUtil.getFileNameWithPlaceHolder(userId, filename, null, time);
		String baseUrl = "http://localhost:8080/designer-front/staticFile/";
		
		//保存原文件(自动创建目录)
		FileUtil.saveFile(data, basePath, imageDirPath, sourceImageName);
		
		//构造uploadResult
		UploadImageResult uploadResult = new UploadImageResult();
		
		Set<String> keys = imageSizeMap.keySet();
		//根据需要的尺寸进行zoom
		for (String imageSpec : keys) {
			int width = imageSizeMap.get(imageSpec);//获取指定的尺寸
			//zoom
			String targetImageName = FileUtil.getFileNameWithPlaceHolder(userId, filename, imageSpec, time);
	        ImageUtil.scaleByWidth(absoultImagePath + sourceImageName, absoultImagePath + targetImageName, width);
	        String imageUrl = baseUrl + imageDirPath + targetImageName;
	    	UploadImageInfo imageInfo = new UploadImageInfo(targetImageName, ConstService.UPLOAD_FILE_TYPE_IMAGE, imageSpec, imageUrl, -1);
	        
	    	//组装uploadResult
	    	if(ConstService.UPLOAD_IMAGE_SPEC_LARGE.equals(imageSpec)){
	        	uploadResult.setLargeImage(imageInfo);
	        }else if(ConstService.UPLOAD_IMAGE_SPEC_MEDIUM.equals(imageSpec)){
	        	uploadResult.setMediumImage(imageInfo);
	        }else if(ConstService.UPLOAD_IMAGE_SPEC_SMALL.equals(imageSpec)){
	        	uploadResult.setSmallImage(imageInfo);
	        }
		}
		return uploadResult;
	}
	
	/**
	 * 上传头像，无需缩放
	 */
	@Override
	public UploadImageResult uploadAvatar(byte[] bytes, int userId, String filename) throws IOException {
		// 获取头像保存路径
		String avatarPath = FileUtil.getAvatarPath();
		// 确定原始图片名
		String avatarFilename = String.valueOf(userId) + "_original.jpg";
		// 构造图片File
		File originAvatar = new File(avatarPath, avatarFilename);
		FileCopyUtils.copy(bytes, originAvatar);
		
		BufferedImage src = ImageIO.read(originAvatar); // 读入文件
        int imgWidth = src.getWidth(); // 得到源图宽
        int imgHeight = src.getHeight(); // 得到源图长
        String originAvatarUrl = "avatar/"+avatarFilename;
        
        HashMap<String, UploadFileInfo> uploadFileMap = new HashMap<String, UploadFileInfo>();
        UploadImageInfo imageInfo = new UploadImageInfo(avatarFilename, ConstService.UPLOAD_FILE_TYPE_IMAGE, ConstService.UPLOAD_IMAGE_SPEC_ORIGINAL, originAvatarUrl, -1, imgWidth, imgHeight);
  		uploadFileMap.put(ConstService.UPLOAD_IMAGE_SPEC_ORIGINAL, imageInfo);
  		UploadImageResult uploadResult = null;//new UploadImageResult(uploadFileMap); 
  		return uploadResult;
	}
	
	@Override
	public UploadImageResult updateAvatar(int userId, int x, int y, int w, int h) throws IOException {
		 String avatarPath = FileUtil.getAvatarPath();
        //确定原始文件名
        String avatarFilename = String.valueOf(userId)+"_original.jpg";
        //构造原始文件
        String destFilename = String.valueOf(userId)+".jpg";
        ImageUtil.abscut(avatarPath+avatarFilename, avatarPath+destFilename, x,y,w, h);

//      File originAvatar = new File(avatarPath, avatarFilename);
//      File destAvatar = new File(avatarPath, destFilename);
//      if(originAvatar.exists()&&destAvatar.delete()){
//      	originAvatar.renameTo(destAvatar);
//      }
      
      //定位临时头像
      //resize并保存成3套临时头像图片（50x50/100x100/200x200）并返回各自url
      //File[] resizedAvatars = batchSize(originAvatar);
        
        File descAvatar = new File(avatarPath+destFilename);
        BufferedImage src = ImageIO.read(descAvatar); // 读入文件
        int imgWidth = src.getWidth(); // 得到源图宽
        int imgHeight = src.getHeight(); // 得到源图长
        
        String descAvatarUrl = "http://localhost:8080/designer-front/staticFile/avatar/"+destFilename;
        
//        UploadImageResult imageResult = new UploadImageResult(avatarFilename, ConstService.UPLOAD_FILE_TYPE_AVATAR,  (short)0, descAvatar.length(), descAvatarUrl, imgWidth, imgHeight);
//		return imageResult;
        return null;
	}
	
}
