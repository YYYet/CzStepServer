package com.chengzzz.stepservice.entity;

public class Data {
    private   String exist;
    private  String hasMobile;
    private  String userId;
    private  String accessToken;
    private  String expireAt;
    private  String userType;
    private  String needInfo;

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public String getHasMobile() {
        return hasMobile;
    }

    public void setHasMobile(String hasMobile) {
        this.hasMobile = hasMobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getNeedInfo() {
        return needInfo;
    }

    public void setNeedInfo(String needInfo) {
        this.needInfo = needInfo;
    }
}
