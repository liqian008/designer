package com.bruce.designer.model;

import java.util.Date;

public class User extends UserBase{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.id
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.username
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String username;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.nickname
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String nickname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.password
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.gender
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Short gender;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.email
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.head_img
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String headImg;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.push_mask
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Long pushMask;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.status
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Short status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.create_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.update_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_status
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Short designerStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_identifer
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String designerIdentifer;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_realname
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String designerRealname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_mobile
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String designerMobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_introduction
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String designerIntroduction;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_company
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String designerCompany;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_taobao_homepage
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private String designerTaobaoHomepage;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_apply_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Date designerApplyTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_user.designer_pass_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	private Date designerPassTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.id
	 * @return  the value of tb_user.id
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.id
	 * @param id  the value for tb_user.id
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.username
	 * @return  the value of tb_user.username
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.username
	 * @param username  the value for tb_user.username
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.nickname
	 * @return  the value of tb_user.nickname
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.nickname
	 * @param nickname  the value for tb_user.nickname
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.password
	 * @return  the value of tb_user.password
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.password
	 * @param password  the value for tb_user.password
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.gender
	 * @return  the value of tb_user.gender
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Short getGender() {
		return gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.gender
	 * @param gender  the value for tb_user.gender
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setGender(Short gender) {
		this.gender = gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.email
	 * @return  the value of tb_user.email
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.email
	 * @param email  the value for tb_user.email
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.head_img
	 * @return  the value of tb_user.head_img
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.head_img
	 * @param headImg  the value for tb_user.head_img
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.push_mask
	 * @return  the value of tb_user.push_mask
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Long getPushMask() {
		return pushMask;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.push_mask
	 * @param pushMask  the value for tb_user.push_mask
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setPushMask(Long pushMask) {
		this.pushMask = pushMask;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.status
	 * @return  the value of tb_user.status
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.status
	 * @param status  the value for tb_user.status
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.create_time
	 * @return  the value of tb_user.create_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.create_time
	 * @param createTime  the value for tb_user.create_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.update_time
	 * @return  the value of tb_user.update_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.update_time
	 * @param updateTime  the value for tb_user.update_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_status
	 * @return  the value of tb_user.designer_status
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Short getDesignerStatus() {
		return designerStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_status
	 * @param designerStatus  the value for tb_user.designer_status
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerStatus(Short designerStatus) {
		this.designerStatus = designerStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_identifer
	 * @return  the value of tb_user.designer_identifer
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getDesignerIdentifer() {
		return designerIdentifer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_identifer
	 * @param designerIdentifer  the value for tb_user.designer_identifer
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerIdentifer(String designerIdentifer) {
		this.designerIdentifer = designerIdentifer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_realname
	 * @return  the value of tb_user.designer_realname
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getDesignerRealname() {
		return designerRealname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_realname
	 * @param designerRealname  the value for tb_user.designer_realname
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerRealname(String designerRealname) {
		this.designerRealname = designerRealname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_mobile
	 * @return  the value of tb_user.designer_mobile
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getDesignerMobile() {
		return designerMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_mobile
	 * @param designerMobile  the value for tb_user.designer_mobile
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerMobile(String designerMobile) {
		this.designerMobile = designerMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_introduction
	 * @return  the value of tb_user.designer_introduction
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getDesignerIntroduction() {
		return designerIntroduction;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_introduction
	 * @param designerIntroduction  the value for tb_user.designer_introduction
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerIntroduction(String designerIntroduction) {
		this.designerIntroduction = designerIntroduction;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_company
	 * @return  the value of tb_user.designer_company
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getDesignerCompany() {
		return designerCompany;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_company
	 * @param designerCompany  the value for tb_user.designer_company
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerCompany(String designerCompany) {
		this.designerCompany = designerCompany;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_taobao_homepage
	 * @return  the value of tb_user.designer_taobao_homepage
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public String getDesignerTaobaoHomepage() {
		return designerTaobaoHomepage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_taobao_homepage
	 * @param designerTaobaoHomepage  the value for tb_user.designer_taobao_homepage
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerTaobaoHomepage(String designerTaobaoHomepage) {
		this.designerTaobaoHomepage = designerTaobaoHomepage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_apply_time
	 * @return  the value of tb_user.designer_apply_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Date getDesignerApplyTime() {
		return designerApplyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_apply_time
	 * @param designerApplyTime  the value for tb_user.designer_apply_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerApplyTime(Date designerApplyTime) {
		this.designerApplyTime = designerApplyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_user.designer_pass_time
	 * @return  the value of tb_user.designer_pass_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public Date getDesignerPassTime() {
		return designerPassTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_user.designer_pass_time
	 * @param designerPassTime  the value for tb_user.designer_pass_time
	 * @mbggenerated  Wed Nov 12 10:22:01 CST 2014
	 */
	public void setDesignerPassTime(Date designerPassTime) {
		this.designerPassTime = designerPassTime;
	}
}