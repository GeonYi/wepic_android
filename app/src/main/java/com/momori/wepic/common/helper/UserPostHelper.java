package com.momori.wepic.common.helper;

import com.momori.wepic.common.Const;
import com.momori.wepic.model.CommonResponseModel;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sec on 2015-03-21.
 */
public interface UserPostHelper {

    @FormUrlEncoded
    @POST(Const.URI_USER_REG)
    CommonResponseModel regUser(
            @Field("user_email") String userEmail,
            @Field("user_pw") String userPw,
            @Field("user_name") String userName,
            @Field("dev_number") String devNumber,
            @Field("dev_platform") String devPlatform
    );

    @FormUrlEncoded
    @POST(Const.URI_USER_LOGIN)
    CommonResponseModel loginUser(
            @Field("user_email") String userEmail,
            @Field("user_pw") String userPw
    );
}
