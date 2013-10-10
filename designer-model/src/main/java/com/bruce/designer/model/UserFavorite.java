package com.bruce.designer.model;

import java.util.Date;

public class UserFavorite {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_favorite.id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_favorite.user_id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_favorite.favorite_album_id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    private Integer favoriteAlbumId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_favorite.create_time
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_favorite.update_time
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_favorite.id
     *
     * @return the value of tb_user_favorite.id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_favorite.id
     *
     * @param id the value for tb_user_favorite.id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_favorite.user_id
     *
     * @return the value of tb_user_favorite.user_id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_favorite.user_id
     *
     * @param userId the value for tb_user_favorite.user_id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_favorite.favorite_album_id
     *
     * @return the value of tb_user_favorite.favorite_album_id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public Integer getFavoriteAlbumId() {
        return favoriteAlbumId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_favorite.favorite_album_id
     *
     * @param favoriteAlbumId the value for tb_user_favorite.favorite_album_id
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public void setFavoriteAlbumId(Integer favoriteAlbumId) {
        this.favoriteAlbumId = favoriteAlbumId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_favorite.create_time
     *
     * @return the value of tb_user_favorite.create_time
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_favorite.create_time
     *
     * @param createTime the value for tb_user_favorite.create_time
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_favorite.update_time
     *
     * @return the value of tb_user_favorite.update_time
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_favorite.update_time
     *
     * @param updateTime the value for tb_user_favorite.update_time
     *
     * @mbggenerated Thu Oct 10 11:25:53 CST 2013
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}