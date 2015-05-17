package com.momori.wepic.controller.post;

import com.google.gson.Gson;
import com.momori.wepic.common.Const;
import com.momori.wepic.controller.post.helper.AlbumPostHelper;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.request.ReqAlbumListModel;
import com.momori.wepic.model.request.ReqAlbumMakeModel;
import com.momori.wepic.model.response.ResAlbumListModel;
import com.momori.wepic.model.response.ResAlbumMakeModel;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by sec on 2015-03-21.
 */
public class AlbumController {

    private RestAdapter     restAdapter;
    private AlbumPostHelper albumHelper;

    public AlbumController() {
        this.init();
    }

    private void init(){
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URI_END_POINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();

        albumHelper = this.restAdapter.create(AlbumPostHelper.class);
    }

    /** 앨범 만들기 */
    public ResAlbumMakeModel makeAlbum(UserModel maker, List<String> invite_users){
       ReqAlbumMakeModel req = new ReqAlbumMakeModel(maker, invite_users);
        return albumHelper.makeAlbum(req);
    }

    /** 앨범 조회 */
    public ResAlbumListModel getAlbum_list(String user_id){
        ReqAlbumListModel req = new ReqAlbumListModel(user_id);
        return albumHelper.getAlbum_list(req);
    }

}
