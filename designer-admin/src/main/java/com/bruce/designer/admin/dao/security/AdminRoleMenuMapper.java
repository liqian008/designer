package com.bruce.designer.admin.dao.security;

import com.bruce.designer.admin.bean.security.AdminRoleMenu;
import com.bruce.designer.admin.bean.security.AdminRoleMenuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminRoleMenuMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int countByExample(AdminRoleMenuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int deleteByExample(AdminRoleMenuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int insert(AdminRoleMenu record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int insertSelective(AdminRoleMenu record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	List<AdminRoleMenu> selectByExample(AdminRoleMenuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	AdminRoleMenu selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int updateByExampleSelective(@Param("record") AdminRoleMenu record,
			@Param("example") AdminRoleMenuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int updateByExample(@Param("record") AdminRoleMenu record,
			@Param("example") AdminRoleMenuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int updateByPrimaryKeySelective(AdminRoleMenu record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_menu
	 * @mbggenerated  Wed Jul 17 10:25:15 CST 2013
	 */
	int updateByPrimaryKey(AdminRoleMenu record);
}