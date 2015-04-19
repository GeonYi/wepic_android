package com.momori.wepic.controller.post.helper;

import com.momori.wepic.common.Const;
import com.momori.wepic.model.response.ResCommonModel;
import com.momori.wepic.model.response.ResImageListModel;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by sec on 2015-04-10.
 */
public interface ImagePostHelper {
    @Multipart
    @POST(Const.URI_IMAGE_ADD)
    ResCommonModel uploadImage(
            @Part("img_file")   TypedFile   image,
            @Part("img_create_datetime")    TypedString imgCreateDatetime,
            @Part("album_id")   TypedString albumId,
            @Part("user_id")    TypedString userId
    );

    @FormUrlEncoded
    @POST(Const.URI_IMAGE_LIST)
    ResImageListModel getImageList(
            @Field("album_id") String albumId
    );


}
