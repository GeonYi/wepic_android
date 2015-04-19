package com.momori.wepic.controller.post.helper;

import com.momori.wepic.common.Const;
import com.momori.wepic.model.response.ResCommonModel;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Hyeon on 2015-04-09.
 */
public interface GcmPostHelper {

    @FormUrlEncoded
    @POST(Const.URI_ALBUM_INVITE)
    ResCommonModel pushMessage(
            @Field("userId") String userId,
            @Field("targetUserId") String targetUserId,
            @Field("message") String message
    );
}
