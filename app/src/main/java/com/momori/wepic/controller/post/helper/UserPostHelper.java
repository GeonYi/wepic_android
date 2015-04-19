package com.momori.wepic.controller.post.helper;

import com.momori.wepic.common.Const;
import com.momori.wepic.model.response.ResCommonModel;
import com.momori.wepic.model.response.ResLogInModel;
import com.momori.wepic.model.response.ResShareAlbumModel;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sec on 2015-03-21.
 */
public interface UserPostHelper {

    @FormUrlEncoded
    @POST(Const.URI_USER_REG)
    ResCommonModel regUser(
            @Field("user_email") String userEmail,
            @Field("user_pw") String userPw,
            @Field("user_name") String userName,
            @Field("dev_number") String devNumber,
            @Field("dev_platform") String devPlatform
    );

    @FormUrlEncoded
    @POST(Const.URI_USER_LOGIN)
    ResLogInModel loginUser(
            @Field("user_email") String userEmail,
            @Field("user_pw") String userPw
    );

    @FormUrlEncoded
    @POST(Const.URI_SHARED_ALBUM)
    ResShareAlbumModel getShareAlbum(
            @Field("user_id") String userId
    );
}
