package com.momori.wepic.controller.user;

import com.google.gson.Gson;

import com.momori.wepic.common.Const;
import com.momori.wepic.common.helper.UserPostHelper;
import com.momori.wepic.model.CommonResponseModel;
import com.momori.wepic.model.UserModel;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by sec on 2015-03-21.
 */
public class UserController {

    private UserModel user ;
    private RestAdapter restAdapter;
    private UserPostHelper regUserHelper;

    public UserController(UserModel user) {
        this.user = user;

        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URI_END_POINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();

        regUserHelper = this.restAdapter.create(UserPostHelper.class);
    }

    public CommonResponseModel registUser(){
        return regUserHelper.regUser(user.getUserEmail(), user.getUserPw(), user.getUserName(), user.getDevNumber(), user.getDevPlatform());
    }

    public CommonResponseModel loginUser(){
        return regUserHelper.loginUser(user.getUserEmail(), user.getUserPw());
    }
}
