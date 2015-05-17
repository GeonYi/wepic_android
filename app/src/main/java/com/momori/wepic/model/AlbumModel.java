package com.momori.wepic.model;

import java.util.Date;
import java.util.List;

/**
 * Created by sec on 2015-04-13.
 */
public class AlbumModel {
    public static final String ALBUM_ID = "ALBUM_ID";

    private String album_id;

    private String album_name;

    private String album_img_url;

    private String group_id;

    private long total_photo_cnt;

    private String init_create_datetime;

    private String end_datetime;

    private List<AlbumUserModel> user_info;

    private String users_name;


    public String getUsers_name(){
        if(users_name==null) {
            StringBuffer sb = new StringBuffer();
            for (AlbumUserModel albumUser : user_info) {
                if (albumUser.getUser_state().equals("join")) {
                    if (sb.length() > 0) sb.append(",");
                    sb.append(albumUser.getUser_name());
                }
            }
            this.users_name = sb.toString();
        }
        return this.users_name;
    }


    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_img_url() {
        return album_img_url;
    }

    public void setAlbum_img_url(String album_img_url) {
        this.album_img_url = album_img_url;
    }

    public long getTotal_photo_cnt() {
        return total_photo_cnt;
    }

    public void setTotal_photo_cnt(long total_photo_cnt) {
        this.total_photo_cnt = total_photo_cnt;
    }

    public String getInit_create_datetime() {
        return init_create_datetime;
    }

    public void setInit_create_datetime(String init_create_datetime) {
        this.init_create_datetime = init_create_datetime;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public List<AlbumUserModel> getUser_info() {
        return user_info;
    }

    public void setUser_info(List<AlbumUserModel> user_info) {
        this.user_info = user_info;
    }
}
