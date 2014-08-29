package com.bruce.designer.model;

import java.util.Date;

public class AccessTokenInfo{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.id
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.user_id
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private Integer userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.access_token
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private String accessToken;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.refresh_token
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private String refreshToken;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.token_type
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private String tokenType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.thirdparty_type
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private Short thirdpartyType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.thirdparty_uid
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private String thirdpartyUid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.thirdparty_uname
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private String thirdpartyUname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.thirdparty_avatar
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private String thirdpartyAvatar;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.expire_in
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private Long expireIn;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.sync_album
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private Short syncAlbum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.creae_time
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private Date creaeTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_access_token_info.upate_time
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	private Date upateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.id
	 * @return  the value of tb_access_token_info.id
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.id
	 * @param id  the value for tb_access_token_info.id
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.user_id
	 * @return  the value of tb_access_token_info.user_id
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.user_id
	 * @param userId  the value for tb_access_token_info.user_id
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.access_token
	 * @return  the value of tb_access_token_info.access_token
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.access_token
	 * @param accessToken  the value for tb_access_token_info.access_token
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.refresh_token
	 * @return  the value of tb_access_token_info.refresh_token
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.refresh_token
	 * @param refreshToken  the value for tb_access_token_info.refresh_token
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.token_type
	 * @return  the value of tb_access_token_info.token_type
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.token_type
	 * @param tokenType  the value for tb_access_token_info.token_type
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.thirdparty_type
	 * @return  the value of tb_access_token_info.thirdparty_type
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public Short getThirdpartyType() {
		return thirdpartyType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.thirdparty_type
	 * @param thirdpartyType  the value for tb_access_token_info.thirdparty_type
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setThirdpartyType(Short thirdpartyType) {
		this.thirdpartyType = thirdpartyType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.thirdparty_uid
	 * @return  the value of tb_access_token_info.thirdparty_uid
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public String getThirdpartyUid() {
		return thirdpartyUid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.thirdparty_uid
	 * @param thirdpartyUid  the value for tb_access_token_info.thirdparty_uid
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setThirdpartyUid(String thirdpartyUid) {
		this.thirdpartyUid = thirdpartyUid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.thirdparty_uname
	 * @return  the value of tb_access_token_info.thirdparty_uname
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public String getThirdpartyUname() {
		return thirdpartyUname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.thirdparty_uname
	 * @param thirdpartyUname  the value for tb_access_token_info.thirdparty_uname
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setThirdpartyUname(String thirdpartyUname) {
		this.thirdpartyUname = thirdpartyUname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.thirdparty_avatar
	 * @return  the value of tb_access_token_info.thirdparty_avatar
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public String getThirdpartyAvatar() {
		return thirdpartyAvatar;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.thirdparty_avatar
	 * @param thirdpartyAvatar  the value for tb_access_token_info.thirdparty_avatar
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setThirdpartyAvatar(String thirdpartyAvatar) {
		this.thirdpartyAvatar = thirdpartyAvatar;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.expire_in
	 * @return  the value of tb_access_token_info.expire_in
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public Long getExpireIn() {
		return expireIn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.expire_in
	 * @param expireIn  the value for tb_access_token_info.expire_in
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setExpireIn(Long expireIn) {
		this.expireIn = expireIn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.sync_album
	 * @return  the value of tb_access_token_info.sync_album
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public Short getSyncAlbum() {
		return syncAlbum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.sync_album
	 * @param syncAlbum  the value for tb_access_token_info.sync_album
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setSyncAlbum(Short syncAlbum) {
		this.syncAlbum = syncAlbum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.creae_time
	 * @return  the value of tb_access_token_info.creae_time
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public Date getCreaeTime() {
		return creaeTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.creae_time
	 * @param creaeTime  the value for tb_access_token_info.creae_time
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setCreaeTime(Date creaeTime) {
		this.creaeTime = creaeTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_access_token_info.upate_time
	 * @return  the value of tb_access_token_info.upate_time
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public Date getUpateTime() {
		return upateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_access_token_info.upate_time
	 * @param upateTime  the value for tb_access_token_info.upate_time
	 * @mbggenerated  Fri Aug 29 10:06:45 CST 2014
	 */
	public void setUpateTime(Date upateTime) {
		this.upateTime = upateTime;
	}
}