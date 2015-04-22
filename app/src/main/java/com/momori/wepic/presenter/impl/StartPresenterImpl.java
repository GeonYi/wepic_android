package com.momori.wepic.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.telephony.TelephonyManager;
import android.util.Log;


import com.momori.wepic.WepicApplication;
import com.momori.wepic.activity.FbLoginActivity;
import com.momori.wepic.activity.MainActivity;
import com.momori.wepic.activity.StartActivity;
import com.momori.wepic.common.Func;
import com.momori.wepic.common.callback.AsyncCallback;
import com.momori.wepic.controller.post.UserController;
import com.momori.wepic.external.gcm.GcmComponent;
import com.momori.wepic.model.UserDeviceModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.response.ResLogInModel;
import com.momori.wepic.presenter.inter.StartPresenter;


/**
 * Created by Hyeon on 2015-04-18.
 */
public class StartPresenterImpl implements StartPresenter {
    static final String TAG = StartPresenterImpl.class.getName();
    private final int FBLOGINACTIVITY_REQEST = 1000;

    private StartActivity activity;
    private WepicApplication context;

    public StartPresenterImpl(StartActivity startActivity) {
        this.activity = startActivity;
        this.context= (WepicApplication)this.activity.getApplicationContext();
    }


    public boolean isReadyToLogin(){
        this.context.getGcmComponent().checkPlayServices(this.activity);
        if(getRegId().isEmpty()){
            return false;
        }else if(!this.context.getFbComponent().isAccessTokenValid()){
            facebookLogin();
            return false;
        }else{
            return true;
        }
    }

    private void facebookLogin() {
        Log.d(TAG, "Facebook 로그인 Activity 시작");
        Intent intent = new Intent(activity, FbLoginActivity.class);
        activity.startActivityForResult(intent, FBLOGINACTIVITY_REQEST);
    }

    private String getRegId(){
        GcmComponent gcmComponent = this.context.getGcmComponent();
        String reg_id = gcmComponent.getRegId();
        if(reg_id.isEmpty()){
            Log.i(TAG, "reg_id 를 신규 등록합니다.");
            registRegId(this.activity);
        }
        return reg_id;
    }

    private void registRegId(final StartActivity activity){
        this.context.getGcmComponent().registRegId(new AsyncCallback.AsyncResult<String>() {
            @Override
            public void onResult(String result) {
                Log.i(TAG, "GCM 등록 완료 : reg_id : " + result + "  다시 로그인 준비를 체크하고 로그인 합니다.");
                activity.checkReadyAndLogin();
            }

            @Override
            public void exceptionOccured(Exception e) {
                Log.e(TAG, "GCM 등록 실패 " + e.getMessage());
                //TODO : 알람 후 확인 누르면 종료
            }
        });
    }

    public void startRegistOrLogin(){
        if(isReadyToLogin()){
            String user_id = "";
            if(!this.context.isLoggedin()){
                Log.i(TAG, "Wepic 로그인 시작");
                user_id = loginWepic();
            }else{
                user_id = this.context.getLoginUser().getUser_id();
            }

            if(!user_id.isEmpty()){
                startMainActivity(user_id);
            }else{
                //TODO : 로그인 실패 알람띄우고 종료
            }
        }
    }

    private String loginWepic(){
        UserModel loginUser = checkAndGetLoginUser();
        UserController userController = new UserController(loginUser);
        ResLogInModel resLogin = userController.loginFbUser();
        if(Func.isPostSucc(resLogin.getResult())){
            Log.i(TAG, "Wepic 로그인 성공 : " + resLogin.getUser_id());
            return resLogin.getUser_id();
        }else{
            Log.i(TAG, "Wepic 로그인 실패 : " + resLogin.getMsg());
            return "";
        }
    }

    private UserModel checkAndGetLoginUser(){
        UserModel loginUser = this.context.getLoginUser();
        if(loginUser.getExternal_id()==null || loginUser.getExternal_id().isEmpty()){
            loginUser = this.context.getFbComponent().callFbLoginUser();
        }
        if(loginUser.getUserDevice()==null){
            loginUser.setUserDevice(createUserDeviceModel());
        }
        return loginUser;
    }

    private UserDeviceModel createUserDeviceModel(){
        TelephonyManager telephonyManager = (TelephonyManager)this.activity.getSystemService(Context.TELEPHONY_SERVICE);
        String dev_id = telephonyManager.getDeviceId();
        String dev_number = telephonyManager.getLine1Number();
        return new UserDeviceModel(dev_id, dev_number, this.context.getGcmComponent().getRegId());
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode== Activity.RESULT_OK){
            if(requestCode == FBLOGINACTIVITY_REQEST){
                Log.i(TAG, "페이스북 계정 확인 완료, 다시 로그인 준비를 체크하고 로그인 합니다.");
                this.activity.checkReadyAndLogin();
            }
        }
    }

    private void startMainActivity(String user_id){
        Log.i(TAG, "MainActivity로 이동");
        Intent intent = new Intent(this.activity, MainActivity.class);
        intent.putExtra(UserModel.USER_ID, user_id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.activity.startActivity(intent);
    }
}