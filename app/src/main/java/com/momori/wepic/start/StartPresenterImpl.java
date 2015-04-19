package com.momori.wepic.start;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.momori.wepic.MainActivity;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.common.callback.AsyncCallback;
import com.momori.wepic.facebook.FbComponent;
import com.momori.wepic.facebook.FbLoginActivity;
import com.momori.wepic.gcm.GcmComponent;


/**
 * Created by Hyeon on 2015-04-18.
 */
public class StartPresenterImpl implements  StartPresenter {
    static final String TAG = StartPresenterImpl.class.getName();
    private final int FBLOGINACTIVITY_REQEST = 1000;

    private StartActivity activity;
    private StartModel startModel;

    public StartPresenterImpl(StartActivity startActivity) {
        this.activity = startActivity;
        this.startModel = new StartModel();
    }

    // 최초 앱 실행시 초기화
    public void initApplication(){
        Context context = this.activity.getApplicationContext();
        SFValue.initInstance(context);
        GcmComponent.initInstance(context);
        FbComponent.initFbComponent(context);
    }

    public boolean isReadyToLogin(){
        if(!FbComponent.getInstance().isAccessTokenValid()){
            facebookLogin();
            return false;
        }else if(getGcmRegId().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    private void facebookLogin() {
        final StartModel startModel = this.startModel;
        final StartActivity activity = this.activity;
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                Intent intent = new Intent(activity, FbLoginActivity.class);
                activity.startActivityForResult(intent, FBLOGINACTIVITY_REQEST);
                return null;
            }
        }.execute(null, null, null);
    }

    public void wepicLogin(){
        if(isReadyToLogin()){

            //  FbComponent에서 정보가져오고 로그인 한다
            startMainActivity();
        }
    }

    private String getGcmRegId(){
        String gcm_reg_id =this.startModel.getGcm_reg_id();
        if(gcm_reg_id.isEmpty()){
            GcmComponent gcmComponent = GcmComponent.getInstance();
            gcm_reg_id = gcmComponent.getGcmRegId();
            if(gcm_reg_id.isEmpty()){
                registGcmRegId(this.activity, this.startModel);
            }else{
                this.startModel.setGcm_reg_id(gcm_reg_id);
            }
        }
        return gcm_reg_id;
    }

    private void registGcmRegId(final StartActivity activity, final StartModel startModel){
        GcmComponent.getInstance().registGcmRegId(activity, new AsyncCallback.AsyncResult<String>() {
            @Override
            public void onResult(String result) {
                startModel.setGcm_reg_id(result);
                activity.checkReadyAndLogin();
            }
            @Override
            public void exceptionOccured(Exception e) {
                Log.e(TAG, e.getMessage());
                //TODO : 알람 후 앱종료
            }
        });
    }

    private void startMainActivity(){
        Intent intent = new Intent(this.activity, MainActivity.class);
        this.activity.startActivity(intent);
    }
}