package com.momori.wepic.model;

import java.io.Serializable;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class FbLoginModel implements Serializable{

    private static String[] PERMISSIONS = {"public_profile", "user_friends", "email"};

    private String fb_user_id = "";

    public FbLoginModel(){

    }

    public String getFb_user_id(){
        return fb_user_id;
    }

    public void setFb_user_id(String fb_user_id){
        this.fb_user_id = fb_user_id;
    }

    public String[] getPERMISSIONS(){
        return PERMISSIONS;
    }
}
