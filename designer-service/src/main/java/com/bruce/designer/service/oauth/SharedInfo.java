package com.bruce.designer.service.oauth;

public class SharedInfo {

	/* 微博标题 */
	private String title;
	/* 微博内容 */
	private String content;
	/* 内容图片 */
	private String imgUrl;
	/* 作品id */
	private Integer albumId;
	/* 作品url */
	private String albumUrl;
	/* accessToken */
	private String accessToken;
	/* 第三方类型 */
	private short thirdpartyType;
	/*图片对象*/
//	private byte[] imgBytes;
	
	public SharedInfo(){
		super();
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getAlbumUrl() {
		return albumUrl;
	}

	public void setAlbumUrl(String albumUrl) {
		this.albumUrl = albumUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public short getThirdpartyType() {
		return thirdpartyType;
	}

	public void setThirdpartyType(short thirdpartyType) {
		this.thirdpartyType = thirdpartyType;
	}


//	public byte[] getImgBytes() {
//		return imgBytes;
//	}
//
//
//	public void setImgBytes(byte[] imgBytes) {
//		this.imgBytes = imgBytes;
//	}
	
	
}
