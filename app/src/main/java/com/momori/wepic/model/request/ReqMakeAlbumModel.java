package com.momori.wepic.model.request;

import com.momori.wepic.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyeon on 2015-05-01.
 */
public class ReqMakeAlbumModel {

    private String user_id;
    private String user_name;
    private String album_id;
    private String group_id;
    private List<InviteUser> invite_users;

    public ReqMakeAlbumModel(UserModel sender, List<String> invite_users){
        this.user_id = sender.getUser_id();
        this.user_name = sender.getUser_name();
        this.album_id = "";
        this.group_id = "";
        this.invite_users = convertInviteUsers(invite_users);
    }


    private List<InviteUser> convertInviteUsers(List<String> external_keys){
        List<InviteUser> invite_users  = new ArrayList<>();
        for(String key : external_keys){
            InviteUser inviteUser = new InviteUser(key, InviteUser.INVITE_TYPE_FACEBOOK);
            invite_users.add(inviteUser);
        }
        return invite_users;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

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

    public List<InviteUser> getInvite_users() {
        return invite_users;
    }

    public void setInvite_users(List<InviteUser> invite_users) {
        this.invite_users = invite_users;
    }

    public class InviteUser{

        public static final String INVITE_TYPE_FACEBOOK = "facebook";

        private String type;
        private String key;

        public InviteUser(String key, String type){
            this.key = key;
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }


}
