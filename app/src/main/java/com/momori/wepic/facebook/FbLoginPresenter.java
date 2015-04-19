package com.momori.wepic.facebook;

import android.content.Intent;

/**
 * Created by Hyeon on 2015-04-18.
 */
public interface FbLoginPresenter {

    public void login();

    public void logOut();

    public FbLoginModel getFbLoginModel();

    public void registCallback(Object target);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void finishActivity(FbLoginModel result);

    void setView(View view);

    public interface View{
        void showFbLoginButton();
    }
}
