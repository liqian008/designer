package com.bruce.designer.admin.bean.security;

import java.util.Date;
import java.util.List;

public class AdminUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_user.id
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_user.username
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_user.password
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_user.status
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_user.create_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_user.update_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_user.last_login_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    private Date lastLoginTime;
    
    
    private List<AdminRole> adminRoles;  
    

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_user.id
     *
     * @return the value of admin_user.id
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_user.id
     *
     * @param id the value for admin_user.id
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_user.username
     *
     * @return the value of admin_user.username
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_user.username
     *
     * @param username the value for admin_user.username
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_user.password
     *
     * @return the value of admin_user.password
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_user.password
     *
     * @param password the value for admin_user.password
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_user.status
     *
     * @return the value of admin_user.status
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_user.status
     *
     * @param status the value for admin_user.status
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_user.create_time
     *
     * @return the value of admin_user.create_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_user.create_time
     *
     * @param createTime the value for admin_user.create_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_user.update_time
     *
     * @return the value of admin_user.update_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_user.update_time
     *
     * @param updateTime the value for admin_user.update_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_user.last_login_time
     *
     * @return the value of admin_user.last_login_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_user.last_login_time
     *
     * @param lastLoginTime the value for admin_user.last_login_time
     *
     * @mbggenerated Wed Jul 17 10:25:15 CST 2013
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

	public List<AdminRole> getAdminRoles() {
		return adminRoles;
	}

	public void setAdminRoles(List<AdminRole> adminRoles) {
		this.adminRoles = adminRoles;
	}
    
}