package com.bruce.designer.bean.upload;

import java.util.Map;

/**
 * 
 * @author liqian
 * 
 */
public class UploadImageResult{
	
	private Map<String, UploadFileInfo> uploadFileMap;
	
	public UploadImageResult(){
	}
	
	public UploadImageResult(Map<String, UploadFileInfo> uploadFileMap){
		this.uploadFileMap = uploadFileMap;
	}

	public Map<String, UploadFileInfo> getUploadFileMap() {
		return uploadFileMap;
	}

	public void setUploadFileMap(Map<String, UploadFileInfo> uploadFileMap) {
		this.uploadFileMap = uploadFileMap;
	}
	
	
//	private String largeImgUrl;
//	private String mediumImgUrl;
//	private String tinyImgUrl;
//
//    public String getLargeImgUrl() {
//        return largeImgUrl;
//    }
//
//    public void setLargeImgUrl(String largeImgUrl) {
//        this.largeImgUrl = largeImgUrl;
//    }
//
//    public String getMediumImgUrl() {
//        return mediumImgUrl;
//    }
//
//    public void setMediumImgUrl(String mediumImgUrl) {
//        this.mediumImgUrl = mediumImgUrl;
//    }
//
//    public String getTinyImgUrl() {
//        return tinyImgUrl;
//    }
//
//    public void setTinyImgUrl(String tinyImgUrl) {
//        this.tinyImgUrl = tinyImgUrl;
//    }
	
	
	
}
