package com.momori.wepic.model;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class StartModel {

    private FbLoginModel fbLoginModel;
    private GcmModel gcmModel;

    public FbLoginModel getFbLoginModel() {
        return fbLoginModel;
    }

    public void setFbLoginModel(FbLoginModel fbLoginModel) {
        this.fbLoginModel = fbLoginModel;
    }

    public GcmModel getGcmModel() {
        return gcmModel;
    }

    public void setGcmModel(GcmModel gcmModel) {
        this.gcmModel = gcmModel;
    }
}
