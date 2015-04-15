package com.momori.wepic.model;

/**
 * Created by sec on 2015-03-21.
 */
public class UserModel {

    private String userId       ;
    private String userEmail    ;
    private String userPw       ;
    private String userName     ;
    private String devNumber    ;
    private String devPlatform  ;

    public UserModel(String userEmail, String userPw, String userName, String devNumber, String devPlatform) {

        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userName = userName;
        this.devNumber = devNumber;
        this.devPlatform = devPlatform;
    }

    public UserModel(String userEmail, String userPw, String userName) {

        this.userEmail  = userEmail;
        this.userPw     = userPw;
        this.userName   = userName;
    }

    public UserModel(String userEmail, String userPw) {
        this.userEmail  = userEmail;
        this.userPw     = userPw;
    }

    public UserModel() {
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getDevNumber() {
        return devNumber;
    }

    public void setDevNumber(String devNumber) {
        this.devNumber = devNumber;
    }

    public String getDevPlatform() {
        return devPlatform;
    }

    public void setDevPlatform(String devPlatform) {
        this.devPlatform = devPlatform;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
