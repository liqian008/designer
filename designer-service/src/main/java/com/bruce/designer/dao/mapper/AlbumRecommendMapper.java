package com.bruce.designer.dao.mapper;

import com.bruce.designer.model.AlbumRecommend;
import com.bruce.designer.model.AlbumRecommendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlbumRecommendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int countByExample(AlbumRecommendCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int deleteByExample(AlbumRecommendCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int insert(AlbumRecommend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int insertSelective(AlbumRecommend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    List<AlbumRecommend> selectByExample(AlbumRecommendCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    AlbumRecommend selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int updateByExampleSelective(@Param("record") AlbumRecommend record, @Param("example") AlbumRecommendCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int updateByExample(@Param("record") AlbumRecommend record, @Param("example") AlbumRecommendCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int updateByPrimaryKeySelective(AlbumRecommend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_album_recommend
     *
     * @mbggenerated Sun Feb 16 22:28:15 CST 2014
     */
    int updateByPrimaryKey(AlbumRecommend record);
}