package com.momori.wepic.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.momori.wepic.activity.FbLoginActivity;
import com.momori.wepic.external.facebook.FbLoginCallback;
import com.momori.wepic.model.FbLoginModel;
import com.momori.wepic.presenter.inter.FbLoginPresenter;

import java.util.Arrays;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class FbLoginPresenterImpl implements FbLoginPresenter {
    static final String TAG = FbLoginPresenterImpl.class.getName();

    private FbLoginActivity activity;
    private FbLoginPresenter.View view;
    private FbLoginModel model;

    public FbLoginPresenterImpl(FbLoginActivity activity){
        if(!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(activity.getApplicationContext());
            Log.i(TAG, "FacebookSdk 초기화");
        }
        this.activity = activity;
        this.model = new FbLoginModel(FbLoginPresenterImpl.this);
    }

    @Override
    public void setView(View view){
        this.view = view;
    }

    public void login(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken==null){
            Log.i(TAG, "Facebook AccessToken 없음. 페이스북 로그인 버튼 생성");
            view.showFbLoginButton();
        }else if(model.isRequireReLogin()){
            Log.i(TAG, "Facebook 재로그인");
            model.reLogin(this.activity);
        }else{
            Log.i(TAG, "Facebook AccessToken 확인 완료");
            finishActivity(Activity.RESULT_OK);
        }
    }

    public void registCallback(Object target){
        model.registCallback(target);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK)
            model.registCallbackManagerOnActivityResult(requestCode, resultCode, data);
    }

    public void finishActivity(int resultCode){
        this.activity.setResultAndFinish(resultCode);
    }
}
