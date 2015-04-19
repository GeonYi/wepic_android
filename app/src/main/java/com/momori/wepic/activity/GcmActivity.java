package com.momori.wepic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.momori.wepic.presenter.inter.GcmPresenter;
import com.momori.wepic.presenter.impl.GcmPresenterImpl;

/**
 * Created by Hyeon on 2015-04-18.
 */
public class GcmActivity extends Activity{
    static final String TAG = GcmActivity.class.getName();
    public static final String GCM_REG_ID = "gcm_reg_id";

    GcmPresenter gcmPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.gcmPresenter = new GcmPresenterImpl(this);

        this.gcmPresenter.registGcm();
    }

    public void setResultAndFinish(int resultCode , Intent intent){
        setResult(resultCode , intent);
        finish();
    }

    @Override
    public void onDestroy(){
        Log.d(TAG, "GcmActivity destroy");
        super.onDestroy();
    }
}
