package com.momori.wepic.start;

import android.content.Intent;
import android.os.AsyncTask;

import com.facebook.FacebookSdk;
import com.momori.wepic.MainActivity;
import com.momori.wepic.facebook.FbLoginActivity;
import com.momori.wepic.facebook.FbLoginModel;
import com.momori.wepic.gcm.GcmActivity;
import com.momori.wepic.gcm.GcmModel;


/**
 * Created by Hyeon on 2015-04-18.
 */
public class StartPresenterImpl implements  StartPresenter {
    private static final int FBLOGINACTIVITY_REQUEST = 1000;
    private static final int GCMACTIVITY_REQUEST = 2000;

    private StartActivity activity;
    private StartModel startModel;

    public StartPresenterImpl(StartActivity startActivity) {
        this.activity = startActivity;
        this.startModel = new StartModel();
    }

    public void login() {
        final StartModel startModel = this.startModel;
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                FacebookSdk.sdkInitialize(activity.getApplicationContext());
                startFbLoginActivity();
                startGcmActivity();
                return null;
            }
        }.execute(null, null, null);
    }

    public void checkLoginAndStartMainActivity(){
        if(startModel.getFbLoginModel()!=null
                && startModel.getGcmModel()!=null){
            startMainActivity();
        }
    }

    public StartModel getStartModel() {
        return this.startModel;
    }

    public void startFbLoginActivity() {
        Intent intent = new Intent(this.activity, FbLoginActivity.class);
        this.activity.startActivityForResult(intent, FBLOGINACTIVITY_REQUEST);
    }

    public void startGcmActivity() {
        Intent intent = new Intent(this.activity, GcmActivity.class);
        this.activity.startActivityForResult(intent, GCMACTIVITY_REQUEST);
    }

    private void startMainActivity(){
        Intent intent = new Intent(this.activity, MainActivity.class);
        this.activity.startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == this.activity.RESULT_OK) {
            if (requestCode == FBLOGINACTIVITY_REQUEST) {
                FbLoginModel fbLoginModel = (FbLoginModel) data.getSerializableExtra(FbLoginModel.class.getSimpleName());
                startModel.setFbLoginModel(fbLoginModel);
            } else if (requestCode == GCMACTIVITY_REQUEST) {
                GcmModel gcmModel = (GcmModel) data.getSerializableExtra(GcmModel.class.getSimpleName());
                startModel.setGcmModel(gcmModel);
            }
        }
    }

}