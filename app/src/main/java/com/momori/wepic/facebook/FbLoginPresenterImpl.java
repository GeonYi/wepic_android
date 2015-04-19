package com.momori.wepic.facebook;

import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class FbLoginPresenterImpl implements  FbLoginPresenter{

    private FbLoginActivity activity;
    private View view;

    CallbackManager callbackManager;
    FbLoginModel fbLoginModel;

    public FbLoginPresenterImpl(FbLoginActivity activity){
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        this.activity = activity;
        this.fbLoginModel = new FbLoginModel();
        this.callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void setView(View view){
        this.view = view;
    }

    public FbLoginModel getFbLoginModel(){
        return this.fbLoginModel;
    }

    public void login(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken==null){
            view.showFbLoginButton();
        }else if(accessToken.isExpired()){
            LoginManager loginManager = LoginManager.getInstance();
            registCallback(loginManager);
            loginManager.logInWithReadPermissions(this.activity, Arrays.asList(fbLoginModel.getPERMISSIONS()));
        }else{
            String fb_user_id = accessToken.getUserId();
            this.fbLoginModel.setFb_user_id(fb_user_id);
            finishActivity(this.fbLoginModel);
        }
    }

    public void logOut(){
        LoginManager.getInstance().logOut();
    }

    public void registCallback(Object target){
        if(target instanceof LoginManager){
            LoginManager loginMananger = (LoginManager)target;
            loginMananger.registerCallback(getCallbackManager(), new FbLoginCallback(this));
        }else if(target instanceof LoginButton){
            LoginButton loginButton = (LoginButton)target;
            loginButton.setReadPermissions(fbLoginModel.getPERMISSIONS());
            loginButton.registerCallback(getCallbackManager(), new FbLoginCallback(this));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode ==this.activity.RESULT_OK)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isAccessTokenValid(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken!=null && !accessToken.isExpired() ?  true : false;
    }

    private CallbackManager getCallbackManager(){
        if(this.callbackManager==null)
            this.callbackManager = CallbackManager.Factory.create();
        return this.callbackManager;
    }

    public void finishActivity(FbLoginModel fbLoginModel){

        int resultCode =  this.activity.RESULT_OK;
        if(fbLoginModel==null || fbLoginModel.getFb_user_id().isEmpty()){
            resultCode = this.activity.RESULT_CANCELED;
        }
        Intent intent = new Intent();
        intent.putExtra(FbLoginModel.class.getSimpleName(), fbLoginModel);

        this.activity.setResultAndFinish(resultCode, intent);
    }
}
