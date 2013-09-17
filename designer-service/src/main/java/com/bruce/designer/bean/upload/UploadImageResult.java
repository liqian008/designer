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

}
