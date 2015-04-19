package com.momori.wepic.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.momori.wepic.R;


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
    }

    @Override
    public void onStart(){
        super.onStart();
        this.startPresenter.login();
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.startPresenter.checkLoginAndStartMainActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.startPresenter.onActivityResult(requestCode, resultCode, data);
    }
}