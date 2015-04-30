package com.momori.wepic.model;

import com.momori.wepic.WepicApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyeon on 2015-04-26.
 */
public class InviteModel {

    private WepicApplication context;


    private List<UserModel> inviteList;
    private List<String> selectedList;

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

    public List<String> getSelectedList() {
        if(selectedList==null){
            this.selectedList = new ArrayList<>();
        }
        return selectedList;
    }

    public void setSelectedList(List<String> selectedList) {
        this.selectedList = selectedList;
    }

    public void addSelectedExternal_id(String external_id){
        getSelectedList().add(external_id);
    }

    public void removeSelectedExternal_id(String external_id){
        getSelectedList().remove(external_id);
    }

    public int getSelectedCount(){
        if(this.selectedList==null) return 0;
        return this.selectedList.size();
    }
}
