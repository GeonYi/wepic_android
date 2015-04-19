package com.momori.wepic.presenter.impl;

import android.app.Activity;
import android.content.Intent;

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

    private static String[] PERMISSIONS = {"public_profile", "user_friends", "email"};

    private FbLoginActivity activity;
    private View view;

    CallbackManager callbackManager;

    public FbLoginPresenterImpl(FbLoginActivity activity){
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
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
            view.showFbLoginButton();
        }else if(accessToken.isExpired()){
            LoginManager loginManager = LoginManager.getInstance();
            registCallback(loginManager);
            loginManager.logInWithReadPermissions(this.activity, Arrays.asList(PERMISSIONS));
        }else{
            finishActivity(Activity.RESULT_OK);
        }
    }

    public void registCallback(Object target){
        if(target instanceof LoginManager){
            LoginManager loginMananger = (LoginManager)target;
            loginMananger.registerCallback(getCallbackManager(), new FbLoginCallback(this));
        }else if(target instanceof LoginButton){
            LoginButton loginButton = (LoginButton)target;
            loginButton.setReadPermissions(PERMISSIONS);
            loginButton.registerCallback(getCallbackManager(), new FbLoginCallback(this));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private CallbackManager getCallbackManager(){
        if(this.callbackManager==null)
            this.callbackManager = CallbackManager.Factory.create();
        return this.callbackManager;
    }

    public void finishActivity(int resultCode){
        this.activity.setResultAndFinish(resultCode);
    }
}
