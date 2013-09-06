package com.bruce.designer.dao;

import com.bruce.designer.bean.User;
import com.bruce.designer.bean.UserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int countByExample(UserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int deleteByExample(UserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    List<User> selectByExample(UserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int updateByExample(@Param("record") User record, @Param("example") UserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Fri Sep 06 12:08:36 CST 2013
     */
    int updateByPrimaryKey(User record);
}