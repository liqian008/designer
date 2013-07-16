package com.renren.x2.feed.beans;

public class AlbumDTO extends AbstractUgcDTO {
    
    private boolean currentPhoto;

    
    public AlbumDTO() {
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
     * @param currentPhoto
     */
    public AlbumDTO(long id, int userId, long createTime, long updateTime, long ugcId, long feedId, String contentInfo,
            int status, boolean currentPhoto) {
        super(id, userId, createTime, updateTime, ugcId, feedId, contentInfo, status);
        this.currentPhoto = currentPhoto;
    }



    /**
     * @return the currentPhoto
     */
    public boolean isCurrentPhoto() {
        return currentPhoto;
    }


    
    /**
     * @param currentPhoto the currentPhoto to set
     */
    public void setCurrentPhoto(boolean currentPhoto) {
        this.currentPhoto = currentPhoto;
    }


    
}
