package com.bruce.designer.service.upload.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bruce.designer.constants.ConstConfig;
import com.bruce.designer.model.upload.UploadImageInfo;
import com.bruce.designer.model.upload.UploadImageResult;
import com.bruce.designer.util.ImageUtil;
import com.bruce.designer.util.UploadUtil;

/**
 * 本地文件的处理实现
 * 
 * @author liqian
 * 
 */
public class UploadLocalServiceImpl extends AbstractUploadService{


	private static final Logger logger = LoggerFactory.getLogger(UploadLocalServiceImpl.class);
	
	public String saveFile(File destFile, String fileDir) {
		return ConstConfig.UPLOAD_URL_FULL + fileDir+ UploadUtil.FILE_SEPARTOR + destFile.getName();
	}
	
	/**
	 * 图片保存到本地
	 * @throws IOException 
	 */
	public UploadImageResult saveImage(File originalImageFile, String imageDir, UploadImageInfo[] imageSpecs) throws IOException {
		short fileType= (short)1;//图片类型
		String newImageName = originalImageFile.getName();
		String originalImageSpec = "original";
		String originalUrl = ConstConfig.UPLOAD_URL_FULL + imageDir + UploadUtil.FILE_SEPARTOR + originalImageSpec + UploadUtil.FILE_SEPARTOR + originalImageFile.getName();
		
		// 构造uploadResult
		UploadImageResult uploadResult = new UploadImageResult();
		UploadImageInfo originalImage = new UploadImageInfo(newImageName, fileType, originalImageSpec, originalUrl, -1);
		//增加origin图片result
		uploadResult.put(originalImage);
		
		if(imageSpecs!=null&&imageSpecs.length>0){
			
			for (UploadImageInfo imageSpecBean : imageSpecs) {
				int width = imageSpecBean.getWidth();
				String imageSpecName = imageSpecBean.getImageSpec();
				String scaleImagePath = ConstConfig.UPLOAD_PATH_BASE + imageDir + UploadUtil.FILE_SEPARTOR +imageSpecName;
				File scaleImageFile = new File(scaleImagePath);
				scaleImageFile.mkdirs();
				
				//图片缩放
				ImageUtil.scaleByWidth(originalImageFile.getAbsolutePath(), scaleImagePath+ UploadUtil.FILE_SEPARTOR +newImageName, width);
				//TODO水印处理
				String specedImageUrl = ConstConfig.UPLOAD_URL_FULL + imageDir + UploadUtil.FILE_SEPARTOR + imageSpecName + UploadUtil.FILE_SEPARTOR + newImageName;
				UploadImageInfo imageInfo = new UploadImageInfo(newImageName, fileType, imageSpecName, specedImageUrl, -1);

				//增加不同规格图片result
				uploadResult.put(imageInfo);
			}
		}
		return uploadResult;
	}

	@Override
	protected UploadImageResult saveAvatarImage(File originalImageFile,
			String imageDirPath, UploadImageInfo[] imageSpecs) {
		//暂时不做处理
		return null;
	}
	
}
