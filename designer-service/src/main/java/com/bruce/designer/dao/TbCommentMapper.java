package com.bruce.designer.dao;

import com.bruce.designer.bean.TbComment;
import com.bruce.designer.bean.TbCommentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int countByExample(TbCommentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int deleteByExample(TbCommentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int insert(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int insertSelective(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    List<TbComment> selectByExample(TbCommentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    TbComment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int updateByExampleSelective(@Param("record") TbComment record, @Param("example") TbCommentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int updateByExample(@Param("record") TbComment record, @Param("example") TbCommentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int updateByPrimaryKeySelective(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbggenerated Wed Jul 03 01:10:34 CST 2013
     */
    int updateByPrimaryKey(TbComment record);
}