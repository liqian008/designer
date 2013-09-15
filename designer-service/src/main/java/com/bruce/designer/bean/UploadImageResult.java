package com.bruce.designer.bean;

/**
 * 
 * @author liqian
 * 
 */
public class UploadImageResult extends UploadResult{
	
	/*图片宽度*/
	private int width;
	/*图片高度*/
	private int height;
	
	public UploadImageResult(){
		super();
	}
	
	public UploadImageResult(String fileName, String fileType, long length, String url, int width, int height){
		super( fileName, fileType, length, url);
		this.width=width;
		this.height=height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
