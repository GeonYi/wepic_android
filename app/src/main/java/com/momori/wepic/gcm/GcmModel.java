package com.momori.wepic.gcm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class GcmModel implements Serializable{

    private String gcm_reg_id="";

    public GcmModel(){
    }

    public String getGcm_reg_id(){
        return this.gcm_reg_id;
    }

    public void setGcm_reg_id(String gcm_reg_id){
        this.gcm_reg_id = gcm_reg_id;
    }
}
