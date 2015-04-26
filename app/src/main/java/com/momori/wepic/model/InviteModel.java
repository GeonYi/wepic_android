package com.momori.wepic.model;

import com.momori.wepic.WepicApplication;

import java.util.List;

/**
 * Created by Hyeon on 2015-04-26.
 */
public class InviteModel {

    private List<UserModel> inviteList;

    private WepicApplication context;

    public InviteModel(){
        this.context = WepicApplication.getInstance();
    }

    public List<UserModel> getInviteList() {
        if(inviteList==null){
            inviteList=  this.context.getFbComponent().getFbFriendsList();
        }
        return inviteList;
    }

    public void setInviteList(List<UserModel> inviteList) {
        this.inviteList = inviteList;
    }
}
