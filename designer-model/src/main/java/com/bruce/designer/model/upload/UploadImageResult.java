package com.bruce.designer.model.upload;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liqian
 * 
 */
public class UploadImageResult {

	// private List<UploadImageInfo> uploadImageList = new
	// ArrayList<UploadImageInfo>();
	private Map<String, UploadImageInfo> uploadImageMap = new HashMap<String, UploadImageInfo>();

	public void put(UploadImageInfo imageInfo) {
		if (imageInfo != null && imageInfo.getImageSpec() != null) {
			uploadImageMap.put(imageInfo.getImageSpec(), imageInfo);
		}
	}

	// public List<UploadImageInfo> getUploadImageList() {
	// return uploadImageList;
	// }
	//
	// public void setUploadImageList(List<UploadImageInfo> uploadImageList) {
	// this.uploadImageList = uploadImageList;
	// }

	public Map<String, UploadImageInfo> getUploadImageMap() {
		return uploadImageMap;
	}

	public void setUploadImageMap(Map<String, UploadImageInfo> uploadImageMap) {
		this.uploadImageMap = uploadImageMap;
	}


	// private UploadImageInfo originalImage;;
	//
	// private UploadImageInfo largeImage;
	//
	// private UploadImageInfo mediumImage;
	//
	// private UploadImageInfo smallImage;
	//

	//
	//
	// public UploadImageInfo getLargeImage() {
	// return largeImage;
	// }
	//
	// public void setLargeImage(UploadImageInfo largeImage) {
	// this.largeImage = largeImage;
	// }
	//
	// public UploadImageInfo getMediumImage() {
	// return mediumImage;
	// }
	//
	// public void setMediumImage(UploadImageInfo mediumImage) {
	// this.mediumImage = mediumImage;
	// }
	//
	// public UploadImageInfo getSmallImage() {
	// return smallImage;
	// }
	//
	// public void setSmallImage(UploadImageInfo smallImage) {
	// this.smallImage = smallImage;
	// }
	//
	// public UploadImageInfo getOriginalImage() {
	// return originalImage;
	// }
	//
	// public void setOriginalImage(UploadImageInfo originalImage) {
	// this.originalImage = originalImage;
	// }
	//
	// public WxMediaUploadResult getWxMediaResult() {
	// return wxMediaResult;
	// }
	//
	// public void setWxMediaResult(WxMediaUploadResult wxMediaResult) {
	// this.wxMediaResult = wxMediaResult;
	// }

}
