package com.momori.wepic.facebook;

import android.content.Intent;

/**
 * Created by Hyeon on 2015-04-18.
 */
public interface FbLoginPresenter {

    public void login();

    public void registCallback(Object target);

    public void onActivityResult(int requestCode, int resultCode, Intent data);

    public void finishActivity(int resultCode);

    public void setView(View view);

    public interface View{
        void showFbLoginButton();
    }
}
