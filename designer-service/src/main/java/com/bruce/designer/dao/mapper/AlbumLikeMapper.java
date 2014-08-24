package com.bruce.designer.dao.mapper;

import com.bruce.designer.model.AlbumLike;
import com.bruce.designer.model.AlbumLikeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlbumLikeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int countByExample(AlbumLikeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int deleteByExample(AlbumLikeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int insert(AlbumLike record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int insertSelective(AlbumLike record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	List<AlbumLike> selectByExample(AlbumLikeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	AlbumLike selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int updateByExampleSelective(@Param("record") AlbumLike record, @Param("example") AlbumLikeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int updateByExample(@Param("record") AlbumLike record, @Param("example") AlbumLikeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int updateByPrimaryKeySelective(AlbumLike record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_like
	 * @mbggenerated  Sun Aug 24 10:24:48 CST 2014
	 */
	int updateByPrimaryKey(AlbumLike record);
}