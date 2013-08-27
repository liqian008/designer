package com.bruce.designer.service.oauth;

public class UnifiedOAuthUser {
    
    
    private String thirdpartyUid;
    private String thirdpartyUname;
    private String thirdpartyType;

    public UnifiedOAuthUser() {

    }
    
    public UnifiedOAuthUser(String type) {
        this.thirdpartyType = type;
    }
    
    public UnifiedOAuthUser(String uid, String uname, String type) {
        this.thirdpartyUid = uid;
        this.thirdpartyUname = uname;
        this.thirdpartyType = type;
    }

    public String getThirdpartyUid() {
        return thirdpartyUid;
    }

    public void setThirdpartyUid(String thirdpartyUid) {
        this.thirdpartyUid = thirdpartyUid;
    }

    public String getThirdpartyUname() {
        return thirdpartyUname;
    }

    public void setThirdpartyUname(String thirdpartyUname) {
        this.thirdpartyUname = thirdpartyUname;
    }

    public String getThirdpartyType() {
        return thirdpartyType;
    }

    public void setThirdpartyType(String thirdpartyType) {
        this.thirdpartyType = thirdpartyType;
    }

}