package com.momori.wepic.model.request;

/**
 * Created by Hyeon on 2015-05-04.
 */
public class ReqAlbumListModel {

    private String user_id;

    public ReqAlbumListModel(String user_id){
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
