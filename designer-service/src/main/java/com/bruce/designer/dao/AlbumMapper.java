package com.bruce.designer.dao;

import com.bruce.designer.bean.Album;
import com.bruce.designer.bean.AlbumCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlbumMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int countByExample(AlbumCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int deleteByExample(AlbumCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int insert(Album record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int insertSelective(Album record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    List<Album> selectByExample(AlbumCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    Album selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int updateByExampleSelective(@Param("record") Album record,
            @Param("example") AlbumCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int updateByExample(@Param("record") Album record,
            @Param("example") AlbumCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int updateByPrimaryKeySelective(Album record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
     * @mbggenerated  Tue Oct 08 19:47:44 CST 2013
     */
    int updateByPrimaryKey(Album record);
}