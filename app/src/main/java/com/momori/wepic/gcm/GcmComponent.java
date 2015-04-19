package com.momori.wepic.gcm;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
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

    private static GcmComponent gcmComponent;

    public static void initInstance(Context context){
        gcmComponent = new GcmComponent(context);
    }
    public static GcmComponent getInstance(){
        return gcmComponent;
    }

    private Context context;
    private GoogleCloudMessaging gcm;

    private GcmComponent(Context context){
        this.context = context;
        this.gcm = GoogleCloudMessaging.getInstance(context);
    }

    public String getGcmRegId(){
        return getGcmRegIdFromPreferences();
    }

    public void registGcmRegId(final Activity activity,  final AsyncCallback<String> callback){
        new AsyncTask<Object, Void, AsyncCallback<String>>(){

            String gcm_reg_id = "";
            Exception e;

            @Override
            protected AsyncCallback<String> doInBackground(Object... params) {
                if(checkPlayServices(activity)) {
                    try{
                        gcm_reg_id = gcm.register(SENDER_ID);
                        storeGcmRegIdToPreferences(context, gcm_reg_id);
                    } catch (IOException ex) {
                        Log.e(TAG, ex.getMessage());
                        e = ex;
                    }
                }else{
                    Log.i(TAG, "No valid Google Play Services APK found.");
                    e = new Exception("No valid Google Play Services APK found.");
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
        }.execute(null, null, null);
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

    private boolean isAppVersionChanged(){
        int registeredAppVersion = SFValue.getInstance().getValue(SFValue.APP_VERSION, Integer.MIN_VALUE);
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

    private String getGcmRegIdFromPreferences(){
        String gcm_reg_id =  SFValue.getInstance().getValue(SFValue.GCM_REG_ID, "");
        if(gcm_reg_id.isEmpty()){
            Log.i(TAG, "Registration not found");
            return "";
        }

        if(isAppVersionChanged()){
            Log.i(TAG , "App version changed.");
            return "";
        }
        return gcm_reg_id;
    }

    private void storeGcmRegIdToPreferences(Context context, String gcm_reg_id){
        SFValue.getInstance().put(SFValue.GCM_REG_ID, gcm_reg_id);
    }
}
