package com.momori.wepic.facebook;

import android.util.Log;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

/**
 * Created by Hyeon on 2015-04-12.
 */
public class FbLoginCallback implements FacebookCallback<LoginResult> {
    static final String TAG = FbLoginCallback.class.getName();

    private FbLoginPresenter fbLoginPresenter;

    public FbLoginCallback(FbLoginPresenter fbLoginPresenter){
        this.fbLoginPresenter = fbLoginPresenter;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.i(TAG, "facebook Login faceSuccess");
        String fb_user_id = loginResult.getAccessToken().getUserId();
        FbLoginModel fbLoginModel = this.fbLoginPresenter.getFbLoginModel();
        fbLoginModel.setFb_user_id(fb_user_id);
        fbLoginPresenter.finishActivity(fbLoginModel);
    }

    @Override
    public void onCancel() {
        Log.e(TAG, "facebook login canceled");
    }

    @Override
    public void onError(FacebookException e) {
        Log.e(TAG, "facebook login failed error");
    }
}
