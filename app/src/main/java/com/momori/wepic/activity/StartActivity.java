package com.momori.wepic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.momori.wepic.R;
import com.momori.wepic.presenter.inter.StartPresenter;
import com.momori.wepic.presenter.impl.StartPresenterImpl;

/**
 * Created by Hyeon on 2015-04-12.
 */
public class StartActivity extends Activity{

    StartPresenter startPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.startPresenter = new StartPresenterImpl(StartActivity.this);
        this.startPresenter.initApplication();
    }


    @Override
    protected void onStart(){
        super.onStart();
        checkReadyAndLogin();
    }

    public void checkReadyAndLogin(){
        if(this.startPresenter.isReadyToLogin()){
            this.startPresenter.wepicLogin();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkReadyAndLogin();
    }
}
