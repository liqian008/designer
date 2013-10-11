package com.bruce.designer.dao.mapper;

import com.bruce.designer.model.UserFavorite;
import com.bruce.designer.model.UserFavoriteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFavoriteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int countByExample(UserFavoriteCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int deleteByExample(UserFavoriteCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int insert(UserFavorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int insertSelective(UserFavorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    List<UserFavorite> selectByExample(UserFavoriteCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    UserFavorite selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int updateByExampleSelective(@Param("record") UserFavorite record, @Param("example") UserFavoriteCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int updateByExample(@Param("record") UserFavorite record, @Param("example") UserFavoriteCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int updateByPrimaryKeySelective(UserFavorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_favorite
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    int updateByPrimaryKey(UserFavorite record);
}