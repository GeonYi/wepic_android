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
        Log.i(TAG, "페이스북 로그인 성공");
        fbLoginPresenter.finishActivity(Activity.RESULT_OK);
    }

    @Override
    public void onCancel() {
        Log.i(TAG, "페이스북 로그인 취소");
        fbLoginPresenter.finishActivity(Activity.RESULT_CANCELED);
    }

    @Override
    public void onError(FacebookException e) {
        Log.e(TAG, "페이스북 로그인 에러");
        fbLoginPresenter.finishActivity(Activity.RESULT_CANCELED);
    }
}
