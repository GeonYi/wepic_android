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
import com.momori.wepic.presenter.inter.FbLoginPresenter;

import java.util.Arrays;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class FbLoginPresenterImpl implements FbLoginPresenter {
    static final String TAG = FbLoginPresenterImpl.class.getName();

    private static String[] PERMISSIONS = {"public_profile", "user_friends", "email"};

    private FbLoginActivity activity;
    private View view;

    CallbackManager callbackManager;

    public FbLoginPresenterImpl(FbLoginActivity activity){
        if(!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(activity.getApplicationContext());
            Log.i(TAG, "FacebookSdk 초기화");
        }
        this.activity = activity;
        this.callbackManager = CallbackManager.Factory.create();
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
        }else if(accessToken.isExpired()){
            Log.w(TAG, "Facebook AccessToken 기간 만료 : " + accessToken.getExpires().toString());
            LoginManager loginManager = LoginManager.getInstance();
            registCallback(loginManager);
            loginManager.logInWithReadPermissions(this.activity, Arrays.asList(PERMISSIONS));
        }else{
            Log.i(TAG, "Facebook AccessToken 확인 완료");
            finishActivity(Activity.RESULT_OK);
        }
    }

    public void registCallback(Object target){
        if(target instanceof LoginManager){
            LoginManager loginMananger = (LoginManager)target;
            loginMananger.registerCallback(this.callbackManager, new FbLoginCallback(this));
            Log.i(TAG, "Facebook LoginManager Callback 등록 완료");
        }else if(target instanceof LoginButton){
            LoginButton loginButton = (LoginButton)target;
            loginButton.setReadPermissions(PERMISSIONS);
            loginButton.registerCallback(this.callbackManager, new FbLoginCallback(this));
            Log.i(TAG, "Facebook 로그인 버튼 Callback 등록 완료");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void finishActivity(int resultCode){
        this.activity.setResultAndFinish(resultCode);
    }
}
