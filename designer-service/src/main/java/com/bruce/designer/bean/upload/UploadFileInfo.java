package com.bruce.designer.bean.upload;

/**
 * 
 * @author liqian
 * 
 */
public class UploadFileInfo {
	
	/*文件名*/
	private String fileName;
	/* 文件类型，normal, image, avatar */
	private short fileType;
	/*文件尺寸*/
	private long length;
	/*文件url*/
	private String url;
	
	public UploadFileInfo(){
		super();
	}
	
	public UploadFileInfo(String fileName, short fileType, String url, long length){
		this.fileName=fileName;
		this.fileType=fileType;
		this.length=length;
		this.url=url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public short getFileType() {
		return fileType;
	}

	public void setFileType(short fileType) {
		this.fileType = fileType;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
