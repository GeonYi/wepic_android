package com.momori.wepic.controller.post.helper;

import com.momori.wepic.common.Const;
import com.momori.wepic.model.request.ReqAlbumListModel;
import com.momori.wepic.model.request.ReqAlbumMakeModel;
import com.momori.wepic.model.response.ResAlbumListModel;
import com.momori.wepic.model.response.ResAlbumMakeModel;
import com.momori.wepic.model.response.ResShareAlbumModel;

import retrofit.http.Body;
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

    @POST(Const.URI_ALBUM_MAKE)
    ResAlbumMakeModel makeAlbum(@Body ReqAlbumMakeModel req);

    @POST(Const.URI_ALBUM_LIST)
    ResAlbumListModel getAlbum_list(@Body ReqAlbumListModel req);
}
