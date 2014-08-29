package com.bruce.designer.service;

import java.io.IOException;

import com.bruce.designer.model.upload.UploadImageResult;

public interface IUploadService{ 
    
	/**
	 * 保存普通文件
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public String uploadFile(byte[] bytes, int userId, String filename) throws IOException;
	
	/**
	 * 保存图片文件，需切割图片
	 * @param bytes
	 * @return
	 */
	public UploadImageResult uploadImage(byte[] bytes, int userId, String filename) throws IOException;
	
	/**
	 * 上传&保存原始头像文件
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public UploadImageResult uploadAvatar(byte[] bytes, int userId) throws IOException;
	
	
	/**
	 * 使用网络图片来更新头像（保存原始头像文件）
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public UploadImageResult uploadAvatarByUrl(String avatarUrl, int userId) throws IOException;

//	public UploadImageResult updateAvatar(int userId, int x, int y, int w, int h) throws IOException;
	
	
}
