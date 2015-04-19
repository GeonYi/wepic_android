package com.momori.wepic.controller;

import com.google.gson.Gson;
import com.momori.wepic.common.Const;
import com.momori.wepic.common.helper.GcmPostHelper;
import com.momori.wepic.model.response.ResCommonModel;

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

    /** GCM PUSH */
    public ResCommonModel pushMessage(){
        return gcmPostHelper.pushMessage("mh", "mh2", "Push Message");
    }
}
