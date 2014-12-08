package com.bruce.designer.dao.mapper;

import com.bruce.designer.model.VoteOption;
import com.bruce.designer.model.VoteOptionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteOptionMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int countByExample(VoteOptionCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int deleteByExample(VoteOptionCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int insert(VoteOption record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int insertSelective(VoteOption record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	List<VoteOption> selectByExample(VoteOptionCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	VoteOption selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int updateByExampleSelective(@Param("record") VoteOption record,
			@Param("example") VoteOptionCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int updateByExample(@Param("record") VoteOption record,
			@Param("example") VoteOptionCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int updateByPrimaryKeySelective(VoteOption record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote_option
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	int updateByPrimaryKey(VoteOption record);
}