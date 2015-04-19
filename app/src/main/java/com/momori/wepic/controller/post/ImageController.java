package com.momori.wepic.controller.post;

import com.google.gson.Gson;
import com.momori.wepic.common.Const;
import com.momori.wepic.controller.post.helper.ImagePostHelper;
import com.momori.wepic.model.response.ResCommonModel;
import com.momori.wepic.model.response.ResImageListModel;

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

    public ImageController() {
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URI_END_POINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();

        imageHelper = this.restAdapter.create(ImagePostHelper.class);
    }

    public ImageController(String filePath) {

        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URI_END_POINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();

        imageHelper = this.restAdapter.create(ImagePostHelper.class);

        this.image = new TypedFile("application/octet-stream", new File(filePath));
    }

    /** 이미지 upload */
    public ResCommonModel uploadImage(String imgCreateDatetime, int albumId, int userId){
        return imageHelper.uploadImage(this.image, new TypedString(imgCreateDatetime), new TypedString(String.valueOf(albumId)), new TypedString(String.valueOf(userId)));
    }

    /** 이미지 리스트 추출 */
    public ResImageListModel getImageList(int albumId){
        return imageHelper.getImageList(String.valueOf(albumId));
    }
}
