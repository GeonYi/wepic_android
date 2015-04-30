package com.momori.wepic.controller.post;

import com.google.gson.Gson;
import com.momori.wepic.common.Const;
import com.momori.wepic.controller.post.helper.GcmPostHelper;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.response.ResCommonModel;
import com.momori.wepic.model.response.ResGcmInviteModel;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Hyeon on 2015-04-09.
 */
public class GcmController {

    private RestAdapter restAdapter;
    private GcmPostHelper gcmPostHelper;

    public GcmController(){
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URI_END_POINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();

        gcmPostHelper = this.restAdapter.create(GcmPostHelper.class);
    }


    /** GCM 초대 */
    public ResGcmInviteModel inviteAlbum(UserModel loginUser, List<String> invite_external_ids, String album_id){
        return gcmPostHelper.inviteAlbum(loginUser.getUser_id(), loginUser.getUser_name(), invite_external_ids, album_id);
    }
}
