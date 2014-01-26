package com.bruce.designer.model.upload;

import java.util.Map;

/**
 * 
 * @author liqian
 * 
 */
public class UploadImageResult{
	
//	private Map<String, UploadFileInfo> uploadFileMap;
//	
//	public UploadImageResult(){
//	}
//	
//	public UploadImageResult(Map<String, UploadFileInfo> uploadFileMap){
//		this.uploadFileMap = uploadFileMap;
//	}
//
//	public Map<String, UploadFileInfo> getUploadFileMap() {
//		return uploadFileMap;
//	}
//
//	public void setUploadFileMap(Map<String, UploadFileInfo> uploadFileMap) {
//		this.uploadFileMap = uploadFileMap;
//	}
	
	private UploadImageInfo originalImage;;
	
	private UploadImageInfo largeImage;
	
	private UploadImageInfo mediumImage;
	
	private UploadImageInfo smallImage;
	
//	private UploadImageInfo tinyImage;
	
	public UploadImageInfo getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(UploadImageInfo largeImage) {
		this.largeImage = largeImage;
	}

	public UploadImageInfo getMediumImage() {
		return mediumImage;
	}

	public void setMediumImage(UploadImageInfo mediumImage) {
		this.mediumImage = mediumImage;
	}
//
//	public UploadImageInfo getTinyImage() {
//		return tinyImage;
//	}
//
//	public void setTinyImage(UploadImageInfo tinyImage) {
//		this.tinyImage = tinyImage;
//	}

	public UploadImageInfo getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(UploadImageInfo smallImage) {
		this.smallImage = smallImage;
	}

	public UploadImageInfo getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(UploadImageInfo originalImage) {
		this.originalImage = originalImage;
	}
	
	
}
