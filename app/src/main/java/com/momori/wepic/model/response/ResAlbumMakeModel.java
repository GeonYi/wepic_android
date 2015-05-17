package com.momori.wepic.model.response;

/**
 * Created by Hyeon on 2015-05-01.
 */
public class ResAlbumMakeModel extends ResCommonModel{

    private String album_id;

    private String group_id;


    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
