package com.momori.wepic.external.facebook;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.login.widget.LoginButton;
import com.momori.wepic.R;
import com.momori.wepic.activity.FbLoginActivity;

/**
 * Created by Hyeon on 2015-04-11.
 */
public class FbLoginButton extends Fragment {
    static final String TAG = FbLoginButton.class.getName();

    private FbLoginActivity activity;
    private LoginButton loginButton;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof FbLoginActivity){
            this.activity = (FbLoginActivity)activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook_login, container, false);
        loginButton = (LoginButton)view.findViewById(R.id.login_button);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        this.activity.fbLoginPresenter.registCallback(this.loginButton);
        this.loginButton.setFragment(this);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.activity.fbLoginPresenter.onActivityResult(requestCode, resultCode, data);
    }
}
