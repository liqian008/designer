package com.bruce.designer.service.oauth;

public class UnifiedOAuth {

    private String accessToken;
    private String refreshToken;
    private String expiresIn;
    private String type;

    public UnifiedOAuth() {

    }

    public UnifiedOAuth(String accessToken, String refreshToken, String expiresIn, String type) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.type = type;
    }

    // 微博转换
    public static UnifiedOAuth parse(weibo4j.http.AccessToken at) {
        return new UnifiedOAuth(at.getAccessToken(), at.getRefreshToken(),
                at.getExpireIn(), "SINA_WEIBO");
    }

    // toString
    public String toString() {
        return this.type + " AccessToken:accessToken = " + this.accessToken
                + " refreshToken = " + this.refreshToken + " expiresIn = "
                + this.expiresIn;
    }

    // 打印accessToken信息
    public void print() {
        System.out.println(this.toString());
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}