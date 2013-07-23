package com.bruce.designer.admin.bean.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminMenu {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.id
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private Integer id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.parent_id
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private Integer parentId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.menu_name
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private String menuName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.menu_code
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private String menuCode;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.menu_url
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private String menuUrl;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.url_target
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private String urlTarget;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.nav_menu
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private Short navMenu;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.sort
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private Integer sort;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.remark
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private String remark;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.status
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private Short status;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.create_time
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private Date createTime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column admin_menu.update_time
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.id
     * @return  the value of admin_menu.id
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.id
     * @param id  the value for admin_menu.id
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.parent_id
     * @return  the value of admin_menu.parent_id
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.parent_id
     * @param parentId  the value for admin_menu.parent_id
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.menu_name
     * @return  the value of admin_menu.menu_name
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.menu_name
     * @param menuName  the value for admin_menu.menu_name
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.menu_code
     * @return  the value of admin_menu.menu_code
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.menu_code
     * @param menuCode  the value for admin_menu.menu_code
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.menu_url
     * @return  the value of admin_menu.menu_url
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.menu_url
     * @param menuUrl  the value for admin_menu.menu_url
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.url_target
     * @return  the value of admin_menu.url_target
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public String getUrlTarget() {
        return urlTarget;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.url_target
     * @param urlTarget  the value for admin_menu.url_target
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setUrlTarget(String urlTarget) {
        this.urlTarget = urlTarget;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.nav_menu
     * @return  the value of admin_menu.nav_menu
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public Short getNavMenu() {
        return navMenu;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.nav_menu
     * @param navMenu  the value for admin_menu.nav_menu
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setNavMenu(Short navMenu) {
        this.navMenu = navMenu;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.sort
     * @return  the value of admin_menu.sort
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.sort
     * @param sort  the value for admin_menu.sort
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.remark
     * @return  the value of admin_menu.remark
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.remark
     * @param remark  the value for admin_menu.remark
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.status
     * @return  the value of admin_menu.status
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.status
     * @param status  the value for admin_menu.status
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.create_time
     * @return  the value of admin_menu.create_time
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.create_time
     * @param createTime  the value for admin_menu.create_time
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column admin_menu.update_time
     * @return  the value of admin_menu.update_time
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column admin_menu.update_time
     * @param updateTime  the value for admin_menu.update_time
     * @mbggenerated  Tue Jul 23 20:07:42 CST 2013
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    List<AdminMenu> childMenus = new ArrayList<AdminMenu>();
	
    public List<AdminMenu> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<AdminMenu> childMenus) {
		this.childMenus = childMenus;
	}
    
	public void addChild(AdminMenu childMenu){
		this.childMenus.add(childMenu);
	}

	
}