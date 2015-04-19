package com.momori.wepic.facebook;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.momori.wepic.R;
import com.momori.wepic.start.StartPresenterImpl;

import java.util.Arrays;


/**
 * Created by Hyeon on 2015-04-11.
 */
public class FbLoginActivity extends FragmentActivity implements FbLoginPresenter.View{
    static final String TAG = FbLoginActivity.class.getName();

    FbLoginPresenter fbLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_facebook_login);

        this.fbLoginPresenter = new FbLoginPresenterImpl(FbLoginActivity.this);
        this.fbLoginPresenter.setView(this);

        this.fbLoginPresenter.login();
    }

    public void setResultAndFinish(int resultCode, Intent intent){
        setResult(resultCode , intent);
        finish();
    }

    @Override
    public void showFbLoginButton(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fbLogin_container);
        if(fragment==null){
            fragment = new FbLoginButton();
            fm.beginTransaction().add(R.id.fbLogin_container, fragment).commit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.fbLoginPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(keyCode){
            // 뒤로 가기 버튼을 누르면 홈 키를 누른거 처럼 내려간다
            case KeyEvent.KEYCODE_BACK:
                moveTaskToBack(true);
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy(){
        Log.d(TAG, "FbLoginActivity destroy");
        super.onDestroy();
    }

    /*
                GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject user, GraphResponse response) {
                                if (user != null) {
                                    //profilePictureView.setProfileId(user.optString("id"));
                                    String fb_user_id = user.optString("id");
                                    moveToMainActivity();
                                } else {
                                    Log.e(TAG, response.getError().getErrorMessage());
                                }
                            }
                        }).executeAsync();
     */

}