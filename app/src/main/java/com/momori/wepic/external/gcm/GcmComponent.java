package com.momori.wepic.external.gcm;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.momori.wepic.WepicApplication;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.common.callback.AsyncCallback;

import java.io.IOException;

/**
 * Created by Hyeon on 2015-04-19.
 */
public class GcmComponent{
    static final String TAG = GcmComponent.class.getName();

    private final String SENDER_ID = "1096401096792"; // Project-Number
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private String reg_id = "";

    private WepicApplication context;
    private GoogleCloudMessaging gcm;



    public GcmComponent(){
        this.context = WepicApplication.getInstance();
        this.gcm = GoogleCloudMessaging.getInstance(context);
        Log.d(TAG, "GcmComponent 객체 생성");
    }

    public String getRegId(){
        if(this.reg_id.isEmpty()){
            this.reg_id = getRegIdFromPreferences();
        }
        return this.reg_id;
    }

    public void registRegId(final AsyncCallback<String> callback){
        new AsyncTask<Object, Void, AsyncCallback<String>>(){

            String gcm_reg_id = "";
            Exception e;

            @Override
            protected AsyncCallback<String> doInBackground(Object... params) {
                    try{
                        gcm_reg_id = gcm.register(SENDER_ID);
                        reg_id = gcm_reg_id;
                        storeRegIdToPreferences(gcm_reg_id);
                        Log.i(TAG, "GCM 서버 등록 완료 - reg_id : " +  gcm_reg_id);
                    } catch (IOException ex) {
                        Log.e(TAG, "GCM 서버 등록 실패 : " + ex.getMessage());
                        e = ex;
                    }

                return callback;
            }

            @Override
            protected void onPostExecute(AsyncCallback<String> result){
                if(e!=null){
                    callback.exceptionOccured(e);
                }else{
                    callback.onResult(gcm_reg_id);
                }
            }
        }.execute();
    }


    // GooglePlayService 사용 가능 체크
    public void checkPlayServices(Activity activity){
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if(resultCode != ConnectionResult.SUCCESS){
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                Log.e(TAG, "No valid Google Play Services APK found.");
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity ,PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }else{
                Log.e(TAG, "This device is not supported.");
                // TODO: alert 띄우고 확인 누르면 종료
            }
        }
    }

    private boolean isAppVersionChanged(){
        int registeredAppVersion = this.context.getSfValue().getValue(SFValue.PREF_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion();
        if(registeredAppVersion != currentVersion){
            Log.i(TAG, "App version changed.");
            return true;
        }else{
            return false;
        }
    }

    private int getAppVersion(){
        try{
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        }catch(PackageManager.NameNotFoundException e){
            throw new RuntimeException("Could not get package name : "+ e);
        }
    }

    private String getRegIdFromPreferences(){
        String gcm_reg_id =  this.context.getSfValue().getValue(SFValue.PREF_REG_ID, "");
        if(gcm_reg_id.isEmpty()){
            Log.i(TAG, "REG_ID not found");
            return "";
        }

        if(isAppVersionChanged()){
            Log.i(TAG , "App version changed.");
            return "";
        }
        return gcm_reg_id;
    }

    private void storeRegIdToPreferences(String gcm_reg_id){
        this.context.getSfValue().put(SFValue.PREF_REG_ID, gcm_reg_id);
        this.context.getSfValue().put(SFValue.PREF_APP_VERSION, getAppVersion());
    }
}
