package com.momori.wepic.common.helper;

import com.momori.wepic.common.Const;
import com.momori.wepic.model.response.ResShareAlbumModel;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sec on 2015-04-12.
 */
public interface AlbumPostHelper {

    @FormUrlEncoded
    @POST(Const.URI_SHARED_ALBUM)
    ResShareAlbumModel getShareAlbum(
            @Field("user_id") String userId
    );
}
