package com.momori.wepic.model;

/**
 * Created by sec on 2015-03-21.
 */
public class UserModel {
    public static final String USER_ID = "USER_ID";

    private String user_id;
    private String user_email;
    private String external_id;
    private String user_name;
    private String picture_url;

    private UserDeviceModel user_device;

    public UserModel(){}

    public UserDeviceModel getUser_Device() {
        return user_device;
    }

    public void setUser_Device(UserDeviceModel user_device) {
        if(this.user_device==null) this.user_device = new UserDeviceModel();
        this.user_device = user_device;
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

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public UserDeviceModel getUser_device() {
        return user_device;
    }

    public void setUser_device(UserDeviceModel user_device) {
        this.user_device = user_device;
    }
}
