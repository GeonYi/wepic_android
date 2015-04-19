package com.momori.wepic.presenter.inter;

import android.content.Intent;

import com.momori.wepic.model.FbLoginModel;

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
