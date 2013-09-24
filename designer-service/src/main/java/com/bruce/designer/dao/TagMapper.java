package com.bruce.designer.dao;

import com.bruce.designer.bean.Tag;
import com.bruce.designer.bean.TagCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int countByExample(TagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int deleteByExample(TagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int insert(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int insertSelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    List<Tag> selectByExample(TagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    Tag selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int updateByExample(@Param("record") Tag record, @Param("example") TagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_tag
     *
     * @mbggenerated Tue Sep 24 14:37:57 CST 2013
     */
    int updateByPrimaryKey(Tag record);
}