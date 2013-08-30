package com.bruce.designer.service.oauth;

public class SharedContent {
    
    /*微博标题*/
    private String title;
    /*微博内容*/
    private String content;
    /*内容图片*/
    private String[] imgs;
    /*发布内容同时at的好友*/
    private String[] targetFriends;
    /*原内容id*/
    private Integer originId;
    /*外链地址*/
    private String originLink;
    /*外链地址，通常设定为taobao地址*/
    private String outerLink;
    /*accessToken*/
    private String accessToken;
    /*第三方类型*/
    private String thirdpartyType;
    
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String[] getImgs() {
        return imgs;
    }
    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }
    public String getOriginLink() {
        return originLink;
    }
    public void setOriginLink(String originLink) {
        this.originLink = originLink;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String[] getTargetFriends() {
        return targetFriends;
    }
    public void setTargetFriends(String[] targetFriends) {
        this.targetFriends = targetFriends;
    }
    public Integer getOriginId() {
        return originId;
    }
    public void setOriginId(Integer originId) {
        this.originId = originId;
    }
    public String getThirdpartyType() {
        return thirdpartyType;
    }
    public void setThirdpartyType(String thirdpartyType) {
        this.thirdpartyType = thirdpartyType;
    }
    public String getOuterLink() {
        return outerLink;
    }
    public void setOuterLink(String outerLink) {
        this.outerLink = outerLink;
    }
    
}
