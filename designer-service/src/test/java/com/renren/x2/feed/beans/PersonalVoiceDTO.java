package com.renren.x2.feed.beans;

public class PersonalVoiceDTO extends AbstractUgcDTO {

    /**
     * 
     */
    public PersonalVoiceDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     * @param userId
     * @param createTime
     * @param updateTime
     * @param ugcId
     * @param feedId
     * @param contentInfo
     * @param status
     */
    public PersonalVoiceDTO(long id, int userId, long createTime, long updateTime, long ugcId, long feedId, String contentInfo,
            int status) {
        super(id, userId, createTime, updateTime, ugcId, feedId, contentInfo, status);
    }

}
