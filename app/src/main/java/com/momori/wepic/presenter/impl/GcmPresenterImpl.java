package com.momori.wepic.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.momori.wepic.activity.GcmActivity;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.model.GcmModel;
import com.momori.wepic.presenter.inter.GcmPresenter;

import java.io.IOException;
/**
 * Created by Hyeon on 2015-04-18.
 */
public class GcmPresenterImpl implements GcmPresenter {
    static final String TAG = GcmPresenterImpl.class.getName();

    GcmActivity activity;
    GcmModel gcmModel;

    SFValue sfValue;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    String SENDER_ID = "1096401096792"; // Project-Number

    GoogleCloudMessaging gcm;

    public GcmPresenterImpl(GcmActivity gcmActivity){
        this.activity = gcmActivity;
        this.gcmModel = new GcmModel();
        this.gcm = GoogleCloudMessaging.getInstance(this.activity.getApplicationContext());
        this.sfValue = new SFValue(this.activity.getApplication());
    }

    public void registGcm(){
        String gcm_reg_id = getGcmRegIdFromPreferences(this.activity.getApplication());
        if(gcm_reg_id.isEmpty()){
            registerInBackground(activity, this.gcmModel);
        }else{
            this.gcmModel.setGcm_reg_id(gcm_reg_id);
            finishActivity(gcmModel);
        }
    }

    // GooglePlayService 사용 가능 체크
    private boolean checkPlayServices(Activity activity){
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if(resultCode != ConnectionResult.SUCCESS){
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity ,PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }else{
                Log.i(TAG, "This device is not supported.");
                // TODO: alert 띄워야함
            }
            return false;
        }
        return true;
    }


    private String getGcmRegIdFromPreferences(Context context){
        String gcm_reg_id =  sfValue.getValue(SFValue.GCM_REG_ID, "");
        if(gcm_reg_id.isEmpty()){
            Log.i(TAG, "Registration not found");
            return "";
        }

        if(isAppVersionChanged(context)){
            Log.i(TAG , "App version changed.");
            return "";
        }

        return gcm_reg_id;
    }

    private boolean isAppVersionChanged(Context context){
        int registeredAppVersion = sfValue.getValue(SFValue.APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if(registeredAppVersion != currentVersion){
            Log.i(TAG, "App version changed.");
            return true;
        }else{
            return false;
        }
    }

    private int getAppVersion(Context context){
        try{
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        }catch(PackageManager.NameNotFoundException e){
            throw new RuntimeException("Could not get package name : "+ e);
        }
    }

    private void registerInBackground(final Activity activity, final GcmModel gcmModel){
        if(checkPlayServices(activity)) {

            new AsyncTask(){

                @Override
                protected Object doInBackground(Object[] params) {
                    String msg ="";
                    try{
                        if(gcm==null){
                            gcm = GoogleCloudMessaging.getInstance(activity.getApplicationContext());
                        }

                        String gcm_reg_id = gcm.register(SENDER_ID);
                        gcmModel.setGcm_reg_id(gcm_reg_id);
                        msg = "Device registered, gcm_reg_id ID=" + gcm_reg_id;
                        storeGcmRegIdToPreferences(activity.getApplicationContext(), gcm_reg_id);

                        finishActivity(gcmModel);

                    } catch (IOException ex) {
                        msg = "Error : " + ex.getMessage();
                    }
                    return msg;
                }
            }.execute(null,null,null);

        }else{
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
    }

    private void storeGcmRegIdToPreferences(Context context, String gcm_reg_id){
        sfValue.put(SFValue.GCM_REG_ID, gcm_reg_id);
    }

    private void finishActivity(GcmModel gcmModel){

        int resultCode =  this.activity.RESULT_OK;
        if(gcmModel==null || gcmModel.getGcm_reg_id().isEmpty()){
            resultCode = this.activity.RESULT_CANCELED;
        }

        Intent intent = new Intent();
        intent.putExtra(GcmModel.class.getSimpleName(), gcmModel);
        this.activity.setResultAndFinish(resultCode, intent);
    }
}
