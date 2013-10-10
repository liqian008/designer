package com.bruce.designer.model;

import java.util.Date;

public class Message {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.message
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private String message;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.message_type
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Short messageType;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.source_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Long sourceId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.dialog_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Integer dialogId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.from_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Integer fromId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.to_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Integer toId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.unread
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Short unread;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.status
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Short status;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.create_time
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Date createTime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column tb_message.update_time
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.id
     * @return  the value of tb_message.id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.id
     * @param id  the value for tb_message.id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.message
     * @return  the value of tb_message.message
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.message
     * @param message  the value for tb_message.message
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.message_type
     * @return  the value of tb_message.message_type
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Short getMessageType() {
        return messageType;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.message_type
     * @param messageType  the value for tb_message.message_type
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setMessageType(Short messageType) {
        this.messageType = messageType;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.source_id
     * @return  the value of tb_message.source_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.source_id
     * @param sourceId  the value for tb_message.source_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.dialog_id
     * @return  the value of tb_message.dialog_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Integer getDialogId() {
        return dialogId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.dialog_id
     * @param dialogId  the value for tb_message.dialog_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setDialogId(Integer dialogId) {
        this.dialogId = dialogId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.from_id
     * @return  the value of tb_message.from_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.from_id
     * @param fromId  the value for tb_message.from_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.to_id
     * @return  the value of tb_message.to_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Integer getToId() {
        return toId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.to_id
     * @param toId  the value for tb_message.to_id
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setToId(Integer toId) {
        this.toId = toId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.unread
     * @return  the value of tb_message.unread
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Short getUnread() {
        return unread;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.unread
     * @param unread  the value for tb_message.unread
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setUnread(Short unread) {
        this.unread = unread;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.status
     * @return  the value of tb_message.status
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.status
     * @param status  the value for tb_message.status
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.create_time
     * @return  the value of tb_message.create_time
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.create_time
     * @param createTime  the value for tb_message.create_time
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column tb_message.update_time
     * @return  the value of tb_message.update_time
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column tb_message.update_time
     * @param updateTime  the value for tb_message.update_time
     * @mbggenerated  Tue Oct 08 10:38:07 CST 2013
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}