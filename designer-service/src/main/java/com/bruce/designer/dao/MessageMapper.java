package com.bruce.designer.dao;

import com.bruce.designer.bean.Message;
import com.bruce.designer.bean.MessageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int countByExample(MessageCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int deleteByExample(MessageCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int insert(Message record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int insertSelective(Message record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    List<Message> selectByExample(MessageCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    Message selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int updateByExampleSelective(@Param("record") Message record,
            @Param("example") MessageCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int updateByExample(@Param("record") Message record,
            @Param("example") MessageCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
     * @mbggenerated  Thu Sep 26 19:07:12 CST 2013
     */
    int updateByPrimaryKey(Message record);
}