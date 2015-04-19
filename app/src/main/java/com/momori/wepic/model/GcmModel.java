package com.momori.wepic.model;

import java.io.Serializable;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class GcmModel implements Serializable{

    private String gcm_reg_id="";

    public GcmModel(){
    }

    public String getGcm_reg_id(){
        return this.gcm_reg_id;
    }

    public void setGcm_reg_id(String gcm_reg_id){
        this.gcm_reg_id = gcm_reg_id;
    }
}
