package com.bruce.designer.dao;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.bean.AccessTokenInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessTokenInfoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int countByExample(AccessTokenInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int deleteByExample(AccessTokenInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int insert(AccessTokenInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int insertSelective(AccessTokenInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	List<AccessTokenInfo> selectByExample(AccessTokenInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	AccessTokenInfo selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int updateByExampleSelective(@Param("record") AccessTokenInfo record,
			@Param("example") AccessTokenInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int updateByExample(@Param("record") AccessTokenInfo record,
			@Param("example") AccessTokenInfoCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int updateByPrimaryKeySelective(AccessTokenInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_access_token_info
	 * @mbggenerated  Sun Sep 15 18:37:30 CST 2013
	 */
	int updateByPrimaryKey(AccessTokenInfo record);
}