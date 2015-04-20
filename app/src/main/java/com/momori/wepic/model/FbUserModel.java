package com.momori.wepic.model;

/**
 * Created by Hyeon on 2015-04-21.
 */
public class FbUserModel {

    private String user_id;
    private String email;
    private String name;

    public FbUserModel(String user_id, String email, String name){
        this.user_id = user_id;
        this.email = email;
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
