package com.momori.wepic.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.momori.wepic.activity.FbLoginActivity;
import com.momori.wepic.activity.MainActivity;
import com.momori.wepic.activity.StartActivity;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.common.callback.AsyncCallback;
import com.momori.wepic.external.facebook.FbComponent;
import com.momori.wepic.external.gcm.GcmComponent;
import com.momori.wepic.presenter.inter.StartPresenter;


/**
 * Created by Hyeon on 2015-04-18.
 */
public class StartPresenterImpl implements StartPresenter {
    static final String TAG = StartPresenterImpl.class.getName();
    private final int FBLOGINACTIVITY_REQEST = 1000;

    private StartActivity activity;

    public StartPresenterImpl(StartActivity startActivity) {
        this.activity = startActivity;
    }

    // 최초 앱 실행시 초기화
    public void initApplication(){
        Log.v(TAG, "앱 초기화 셋팅합니다.");
        Context context = this.activity.getApplicationContext();
        SFValue.initInstance(context);
        FbComponent.initFbComponent(context);
        GcmComponent.initInstance(this.activity);
    }

    public boolean isReadyToLogin(){
        if(!FbComponent.getInstance().isAccessTokenValid()){
            facebookLogin();
            return false;
        }else if(getRegId().isEmpty()){
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

    public void wepicLogin(){
        if(isReadyToLogin()){

            //  FbComponent에서 정보가져오고 로그인 한다
            startMainActivity();
        }
    }

    private String getRegId(){
        GcmComponent gcmComponent = GcmComponent.getInstance();
        String reg_id = gcmComponent.getRegId();
        if(reg_id.isEmpty()){
            Log.i(TAG, "reg_id 를 신규 등록합니다.");
            registRegId(this.activity);
        }
        return reg_id;
    }

    private void registRegId(final StartActivity activity){
        GcmComponent.getInstance().registRegId(new AsyncCallback.AsyncResult<String>() {
            @Override
            public void onResult(String result) {
                Log.d(TAG, "reg_id : " + result);
                activity.checkReadyAndLogin();
            }

            @Override
            public void exceptionOccured(Exception e) {
                Log.e(TAG, "GCM 등록 실패 " + e.getMessage());
                //TODO : 알람 후 확인 누르면 종료
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode== Activity.RESULT_OK){
            if(requestCode == FBLOGINACTIVITY_REQEST){
                Log.i(TAG, "페이스북 계정 확인 완료, 다시 로그인 준비를 체크하고 로그인 합니다.");
                this.activity.checkReadyAndLogin();
            }
        }
    }

    private void startMainActivity(){
        Intent intent = new Intent(this.activity, MainActivity.class);
        this.activity.startActivity(intent);
    }
}