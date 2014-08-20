package com.bruce.designer.model;

import java.util.Date;

public class AlbumFavorite extends AlbumFavoriteBase{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album_favorite.id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album_favorite.album_id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    private Integer albumId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album_favorite.user_id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album_favorite.create_time
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_album_favorite.update_time
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album_favorite.id
     *
     * @return the value of tb_album_favorite.id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album_favorite.id
     *
     * @param id the value for tb_album_favorite.id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album_favorite.album_id
     *
     * @return the value of tb_album_favorite.album_id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public Integer getAlbumId() {
        return albumId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album_favorite.album_id
     *
     * @param albumId the value for tb_album_favorite.album_id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album_favorite.user_id
     *
     * @return the value of tb_album_favorite.user_id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album_favorite.user_id
     *
     * @param userId the value for tb_album_favorite.user_id
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album_favorite.create_time
     *
     * @return the value of tb_album_favorite.create_time
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album_favorite.create_time
     *
     * @param createTime the value for tb_album_favorite.create_time
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_album_favorite.update_time
     *
     * @return the value of tb_album_favorite.update_time
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_album_favorite.update_time
     *
     * @param updateTime the value for tb_album_favorite.update_time
     *
     * @mbggenerated Fri Feb 21 22:07:19 CST 2014
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}