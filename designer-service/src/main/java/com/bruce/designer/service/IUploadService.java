package com.bruce.designer.service;

import java.io.IOException;

import com.bruce.designer.bean.UploadImageResult;

public interface IUploadService{
    
	/**
	 * 保存普通文件
	 * @param bytes
	 * @return
	 */
	public String uploadFile(byte[] bytes);
	
	/**
	 * 保存图片文件，需切割图片
	 * @param bytes
	 * @return
	 */
	public String uploadImage(byte[] bytes);
	
	/**
	 * 保存原始头像文件
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public UploadImageResult uploadAvatar(byte[] bytes, int userId) throws IOException;

	public UploadImageResult updateAvatar(int userId, int x, int y, int w, int h) throws IOException;
	
	
}
