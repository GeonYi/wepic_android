package com.momori.wepic.model.response;

/**
 * Created by sec on 2015-04-12.
 */
public class ResLogInModel extends ResCommonModel{

    private String user_id   ;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return super.toString() + " ResLogInModel{" +
                "user_id='" + user_id + '\'' +
                '}';
    }
}
