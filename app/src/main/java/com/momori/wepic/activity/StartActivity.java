package com.momori.wepic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.momori.wepic.R;
import com.momori.wepic.common.callback.AsyncCallback;
import com.momori.wepic.presenter.inter.StartPresenter;
import com.momori.wepic.presenter.impl.StartPresenterImpl;

/**
 * Created by Hyeon on 2015-04-12.
 */
public class StartActivity extends Activity{
    static final String TAG = StartActivity.class.getName();

    StartPresenter startPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Log.i(TAG, "StartActivity 시작");
        this.startPresenter = new StartPresenterImpl(StartActivity.this);
        checkReadyAndLogin();
    }


    public void checkReadyAndLogin() {
        Log.d(TAG , "백그라운드로 로그인 준비 여부를 체크하고 준비완료되면, 위픽로그인 한다.");
        new AsyncTask<StartPresenter, Void, Boolean>() {

            StartPresenter startPresenter;

            @Override
            protected Boolean doInBackground(StartPresenter[] params) {
                Log.d(TAG, "로그인 준비가 됐는지 체크하여 반환한다.");
                this.startPresenter = params[0];
                return this.startPresenter.isReadyToLogin();
            }

            @Override
            protected void onPostExecute(Boolean result){
                if(result){
                    Log.i(TAG, "로그인 준비 완료, Wepic 로그인 합니다.");
                    wepicLogin();
                }
            }
        }.execute(this.startPresenter);
    }

    public void wepicLogin(){
        Log.d(TAG , "백그라운드로 Wepic 로그인");
        new AsyncTask<StartPresenter, Void, Void>() {

            @Override
            protected Void doInBackground(StartPresenter[] params) {
                params[0].startLogin();
                return null;
            }
        }.execute(this.startPresenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.startPresenter.onActivityResult(requestCode, resultCode, data);
    }
}
