package com.momori.wepic.model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.momori.wepic.external.facebook.FbLoginCallback;
import com.momori.wepic.presenter.inter.FbLoginPresenter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Hyeon on 2015-04-26.
 */
public class FbLoginModel {
    static final String TAG = FbLoginModel.class.getName();

    private static String[] PERMISSIONS = {"public_profile", "user_friends", "email"};

    private FbLoginPresenter presenter;
    private CallbackManager callbackManager;

    public FbLoginModel(FbLoginPresenter fbLoginPresenter){
        this.presenter = fbLoginPresenter;
        this.callbackManager = CallbackManager.Factory.create();
    }

    public boolean isRequireReLogin(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken.isExpired()){
            Log.i(TAG, "Facebook AccessToken 만료 : " + accessToken.getExpires().toString());
            return true;
        }else if(!hasRequirePermissions(accessToken)){
            Log.i(TAG, "Facebook AccessToken Permission 필요");
            return true;
        }else{
            return false;
        }
    }

    private boolean hasRequirePermissions(AccessToken accessToken){
        Set<String> accessPermissions = accessToken.getPermissions();
        List<String> requirePermissions = Arrays.asList(PERMISSIONS);
        if(accessPermissions.containsAll(requirePermissions)){
            return true;
        }else{
            return false;
        }
    }

    public void reLogin(Activity activity){
        LoginManager loginManager = LoginManager.getInstance();
        registCallback(loginManager);
        loginManager.logInWithReadPermissions(activity, Arrays.asList(PERMISSIONS));
    }

    public void registCallback(Object target){
        if(target instanceof LoginManager){
            LoginManager loginMananger = (LoginManager)target;
            loginMananger.registerCallback(this.callbackManager, new FbLoginCallback(presenter));
            Log.i(TAG, "Facebook LoginManager Callback 등록 완료");
        }else if(target instanceof LoginButton){
            LoginButton loginButton = (LoginButton)target;
            loginButton.setReadPermissions(PERMISSIONS);
            loginButton.registerCallback(this.callbackManager, new FbLoginCallback(presenter));
            Log.i(TAG, "Facebook 로그인 버튼 Callback 등록 완료");
        }
    }

    public void registCallbackManagerOnActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
