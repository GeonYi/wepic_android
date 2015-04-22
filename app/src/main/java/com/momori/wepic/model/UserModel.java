package com.momori.wepic.model;

import android.graphics.Bitmap;

/**
 * Created by sec on 2015-03-21.
 */
public class UserModel {
    public static final String USER_ID = "USER_ID";

    private String user_id;
    private String user_email;
    private String external_id;
    private String user_name;
    private Bitmap user_picture;

    private UserDeviceModel userDevice;

    public UserModel(){}

    public UserDeviceModel getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(UserDeviceModel userDevice) {
        if(this.userDevice==null) this.userDevice = new UserDeviceModel();
        this.userDevice = userDevice;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Bitmap getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(Bitmap user_picture) {
        this.user_picture = user_picture;
    }
}
