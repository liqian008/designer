package com.bruce.designer.dao;

import com.bruce.designer.bean.UserFans;
import com.bruce.designer.bean.UserFansCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFansMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int countByExample(UserFansCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int deleteByExample(UserFansCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int insert(UserFans record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int insertSelective(UserFans record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    List<UserFans> selectByExample(UserFansCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    UserFans selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int updateByExampleSelective(@Param("record") UserFans record,
            @Param("example") UserFansCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int updateByExample(@Param("record") UserFans record,
            @Param("example") UserFansCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int updateByPrimaryKeySelective(UserFans record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_user_fans
     * @mbggenerated  Tue Oct 08 16:10:32 CST 2013
     */
    int updateByPrimaryKey(UserFans record);
}