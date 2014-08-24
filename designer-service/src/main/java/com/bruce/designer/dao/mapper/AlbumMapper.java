package com.bruce.designer.dao.mapper;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlbumMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int countByExample(AlbumCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int deleteByExample(AlbumCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int insert(Album record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int insertSelective(Album record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	List<Album> selectByExample(AlbumCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	Album selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int updateByExampleSelective(@Param("record") Album record, @Param("example") AlbumCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int updateByExample(@Param("record") Album record, @Param("example") AlbumCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int updateByPrimaryKeySelective(Album record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album
	 * @mbggenerated  Sun Aug 24 10:21:14 CST 2014
	 */
	int updateByPrimaryKey(Album record);
}
