package com.bruce.designer.dao.mapper;

import com.bruce.designer.model.Feedback;
import com.bruce.designer.model.FeedbackCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedbackMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int countByExample(FeedbackCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int deleteByExample(FeedbackCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int insert(Feedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int insertSelective(Feedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    List<Feedback> selectByExample(FeedbackCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    Feedback selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int updateByPrimaryKeySelective(Feedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_feedback
     *
     * @mbggenerated Sun Feb 16 09:48:47 CST 2014
     */
    int updateByPrimaryKey(Feedback record);
}