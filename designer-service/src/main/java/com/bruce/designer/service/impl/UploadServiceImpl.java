package com.bruce.designer.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.bruce.designer.bean.UploadImageResult;
import com.bruce.designer.service.IUploadService;
import com.bruce.designer.util.ImageCut;
import com.bruce.designer.util.PropertiesUtil;

@Service
public class UploadServiceImpl implements IUploadService {

	@Override
	public String uploadFile(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadImage(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadImageResult uploadAvatar(byte[] bytes, int userId) throws IOException {
		// 获取头像保存路径
		String avatarPath = genAvatarPath(userId);
		// 确定原始文件名
		String avatarFilename = String.valueOf(userId) + "_original.jpg";
		// 构造&保存头像File
		File originAvatar = new File(avatarPath, avatarFilename); 
		FileCopyUtils.copy(bytes, originAvatar);
		
		BufferedImage src = ImageIO.read(originAvatar); // 读入文件
        int imgWidth = src.getWidth(); // 得到源图宽
        int imgHeight = src.getHeight(); // 得到源图长
        
        String originAvatarUrl = "http://localhost:8080/designer-front/staticFile/avatar/"+avatarFilename;
        
        UploadImageResult imageResult = new UploadImageResult(avatarFilename, "original", originAvatar.length(), originAvatarUrl, imgWidth, imgHeight);
		return imageResult;
	}
	
	@Override
	public UploadImageResult updateAvatar(int userId, int x, int y, int w, int h) throws IOException {
		 String avatarPath = genAvatarPath(userId);
        //确定原始文件名
        String avatarFilename = String.valueOf(userId)+"_original.jpg";
        //构造原始文件
        String destFilename = String.valueOf(userId)+".jpg";
        ImageCut.abscut(avatarPath+avatarFilename, avatarPath+destFilename, x,y,w, h);
        
        

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
        
        UploadImageResult imageResult = new UploadImageResult(avatarFilename, "avatar", descAvatar.length(), descAvatarUrl, imgWidth, imgHeight);
		return imageResult;
	}

	/**
	 * 获取用户头像的保存路径
	 * @param userId
	 * @return
	 */
	private static String genAvatarPath(int userId) {
		return PropertiesUtil.getString("avatar_upload_base_path");
	}

}
