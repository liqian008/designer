package com.bruce.designer.bean;

/**
 * 
 * @author liqian
 * 
 */
public class UploadResult {
	
	/*文件名*/
	private String fileName;
	/*文件类型*/
	private String fileType;
	/*文件尺寸*/
	private long length;
	/*文件url*/
	private String url;
	
	public UploadResult(){
		super();
	}
	
	public UploadResult(String fileName, String fileType, long length, String url){
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
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
