package com.momori.wepic.model;

/**
 * Created by sec on 2015-03-21.
 */
public class UserDeviceModel {

    private String dev_id;
    private String dev_number;
    private String dev_platform = "ANDROID";
    private String dev_reg_id;

    public UserDeviceModel(){

    }

    public UserDeviceModel(String dev_id, String dev_number,String dev_reg_id) {
        this.dev_id = dev_id;
        this.dev_number = dev_number;
        this.dev_reg_id = dev_reg_id;
    }

    public String getDev_id() {
        return dev_id;
    }

    public void setDev_id(String dev_id) {
        this.dev_id = dev_id;
    }

    public String getDev_reg_id() {
        return dev_reg_id;
    }

    public void setDev_reg_id(String dev_reg_id) {
        this.dev_reg_id = dev_reg_id;
    }

    public String getDev_number() {
        return dev_number;
    }

    public void setDev_number(String dev_number) {
        this.dev_number = dev_number;
    }

    public String getDev_platform() {
        return dev_platform;
    }

    public void setDev_platform(String dev_platform) {
        this.dev_platform = dev_platform;
    }
}
