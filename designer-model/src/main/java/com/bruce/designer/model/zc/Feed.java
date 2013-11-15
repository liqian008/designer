package com.bruce.designer.model.zc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bruce.designer.model.upload.UploadFileInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Feed extends FeedDTO {

    private static final Gson gson = new GsonBuilder().create();

    private List<UploadFileInfo> photos;

    private String text;
    
    private List<Tag> tags;
    
    public Feed() {
        super();
    }
    
    public Feed(FeedDTO dto){
        super();
        if(dto != null){
            setId(dto.getId());
            setUpdateTime(dto.getUpdateTime());
            setCreateTime(dto.getCreateTime());
            setContent(dto.getContent());
            setUserId(dto.getUserId());
        }
    }

    public List<UploadFileInfo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UploadFileInfo> photos) {
        this.photos = photos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

    @Override
    public String getContent() {
        return gson.toJson(this);
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
        Feed f = gson.fromJson(super.getContent(), Feed.class);
        this.photos = f.getPhotos();
        this.text = f.getText();
        this.tags = f.getTags();
    }

    public static void main(String[] args) {
        List<UploadFileInfo> photos = new ArrayList<UploadFileInfo>();
        UploadFileInfo ui = new UploadFileInfo("abc.jpg", (short) 1, "http://www.baidu.com", 1231243);
        photos.add(ui);
        Feed f = new Feed();
        f.setId(123456789L);
        f.setCreateTime(System.currentTimeMillis());
        f.setUpdateTime(new Date());
        f.setText("test...");
        f.setPhotos(photos);
        System.out.println(f.getContent());
    }
    
    @Override
    public String toString() {
        return "Feed [getId()=" + getId() + ", getUserId()=" + getUserId() + ", getCreateTime()=" + getCreateTime()
                + ", getUpdateTime()=" + getUpdateTime() + "]";
    }

    
    public List<Tag> getTags() {
        return tags;
    }

    
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
