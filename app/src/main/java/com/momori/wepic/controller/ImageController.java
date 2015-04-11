package com.momori.wepic.controller;

import com.google.gson.Gson;
import com.momori.wepic.common.Const;
import com.momori.wepic.common.helper.ImagePostHelper;
import com.momori.wepic.model.CommonResponseModel;

import java.io.File;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by sec on 2015-04-05.
 */
public class ImageController {

    private RestAdapter restAdapter;
    private ImagePostHelper imageHelper;

    private TypedFile   image;

    public ImageController(String filePath) {

        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URI_END_POINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();

        imageHelper = this.restAdapter.create(ImagePostHelper.class);

        this.image = new TypedFile("application/octet-stream", new File(filePath));
    }

    /** 회원등록 */
    public CommonResponseModel uploadImage(String imgCreateDatetime, int userId, int albumId){
        TypedString sendUserId = new TypedString(String.valueOf(userId));
        return imageHelper.uploadImage(this.image, new TypedString(imgCreateDatetime), new TypedString(String.valueOf(userId)), new TypedString(String.valueOf(albumId)));
    }
}
