package com.bruce.designer.service.upload;

import java.io.IOException;

import com.bruce.designer.model.upload.UploadImageInfo;
import com.bruce.designer.model.upload.UploadImageResult;

public interface IUploadService{
	
	/*常用的图片规格*/
	public final static UploadImageInfo IMAGE_SPEC_LARGE = new UploadImageInfo("large", 1000);
	public final static UploadImageInfo IMAGE_SPEC_MEDIUM = new UploadImageInfo("medium", 400);
	public final static UploadImageInfo IMAGE_SPEC_SMALL = new UploadImageInfo("small", 200);
	/*常用的头像规格*/
	public final static UploadImageInfo AVATAR_SPEC_LARGE = new UploadImageInfo("large", 200);
	public final static UploadImageInfo AVATAR_SPEC_MEDIUM = new UploadImageInfo("medium", 100);
	public final static UploadImageInfo AVATAR_SPEC_SMALL = new UploadImageInfo("small", 50);
    
	/**
	 * 保存普通文件
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public String uploadFile(byte[] bytes, String userId, String filename) throws Exception;
	
	/**
	 * 保存图片文件，需切割图片
	 * @param bytes
	 * @return 
	 */
	public UploadImageResult uploadImage(byte[] bytes, String userId, String filename, UploadImageInfo... imageSpecs) throws Exception;
	
	
	/**
	 * 上传&保存原始头像文件
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public UploadImageResult uploadAvatar(byte[] bytes, String userId, UploadImageInfo... imageSpecs) throws Exception;
	
	
	/**
	 * 使用网络图片来更新头像（保存原始头像文件）
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public UploadImageResult uploadAvatarByUrl(String avatarUrl, String userId, UploadImageInfo... imageSpecs) throws Exception;
	
	
}
