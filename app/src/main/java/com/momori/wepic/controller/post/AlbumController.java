package com.momori.wepic.controller.post;

import com.google.gson.Gson;
import com.momori.wepic.common.Const;
import com.momori.wepic.controller.post.helper.AlbumPostHelper;
import com.momori.wepic.model.InviteModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.request.ReqMakeAlbumModel;
import com.momori.wepic.model.response.ResMakeAlbumModel;
import com.momori.wepic.model.response.ResShareAlbumModel;

import java.util.HashMap;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by sec on 2015-03-21.
 */
public class AlbumController {

    private UserModel       user ;
    private RestAdapter     restAdapter;
    private AlbumPostHelper albumHelper;

    public AlbumController(UserModel user) {
        this.user = user;
        this.init();
    }

    private void init(){
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URI_END_POINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();

        albumHelper = this.restAdapter.create(AlbumPostHelper.class);
    }

    /** 공유사진정보추출 */
    public ResShareAlbumModel getSharedAlbumInfo(){
        return albumHelper.getShareAlbum(user.getUser_id());
    }

    /** 앨범 만들기 */
    public ResMakeAlbumModel makeAlbum(UserModel maker, List<String> invite_users){
       ReqMakeAlbumModel req = new ReqMakeAlbumModel(maker, invite_users);
        return albumHelper.makeAlbum(req);
    }

}
