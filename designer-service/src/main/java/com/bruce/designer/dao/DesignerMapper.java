package com.bruce.designer.dao;

import com.bruce.designer.bean.Designer;
import com.bruce.designer.bean.DesignerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DesignerMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int countByExample(DesignerCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int deleteByExample(DesignerCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int insert(Designer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int insertSelective(Designer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	List<Designer> selectByExample(DesignerCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	Designer selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int updateByExampleSelective(@Param("record") Designer record,
			@Param("example") DesignerCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int updateByExample(@Param("record") Designer record,
			@Param("example") DesignerCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int updateByPrimaryKeySelective(Designer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_designer
	 * @mbggenerated  Sat Jul 06 00:08:54 CST 2013
	 */
	int updateByPrimaryKey(Designer record);
}