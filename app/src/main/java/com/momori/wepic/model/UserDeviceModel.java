package com.momori.wepic.model;

/**
 * Created by sec on 2015-03-21.
 */
public class UserDeviceModel {

    private String devNumber    ;
    private String devPlatform  ;

    public UserDeviceModel(String devNumber, String devPlatform) {
        this.devNumber = devNumber;
        this.devPlatform = devPlatform;
    }

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
}
