package com.momori.wepic.external.facebook;

import android.app.Activity;
import android.util.Log;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.momori.wepic.presenter.inter.FbLoginPresenter;

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
        fbLoginPresenter.finishActivity(Activity.RESULT_OK);
    }

    @Override
    public void onCancel() {
        Log.e(TAG, "facebook login canceled");
        fbLoginPresenter.finishActivity(Activity.RESULT_CANCELED);
    }

    @Override
    public void onError(FacebookException e) {
        Log.e(TAG, "facebook login failed error");
        fbLoginPresenter.finishActivity(Activity.RESULT_CANCELED);
    }
}
