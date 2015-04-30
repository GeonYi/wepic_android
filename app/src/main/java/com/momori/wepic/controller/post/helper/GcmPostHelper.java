package com.momori.wepic.controller.post.helper;

import com.momori.wepic.common.Const;
import com.momori.wepic.model.response.ResCommonModel;
import com.momori.wepic.model.response.ResGcmInviteModel;

import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Hyeon on 2015-04-09.
 */
public interface GcmPostHelper {

    @FormUrlEncoded
    @POST(Const.URI_ALBUM_INVITE)
    ResGcmInviteModel inviteAlbum(
            @Field("user_id") String user_id,
            @Field("user_name") String user_name,
            @Field("invite_external_ids") List<String> invite_external_ids,
            @Field("album_id") String album_id
    );
}
