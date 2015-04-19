package com.momori.wepic.start;

import com.momori.wepic.facebook.FbLoginModel;
import com.momori.wepic.gcm.GcmModel;

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
