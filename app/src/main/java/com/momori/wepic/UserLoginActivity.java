package com.momori.wepic;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;

import com.momori.wepic.common.Const;
import com.momori.wepic.common.Func;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.controller.UserController;
import com.momori.wepic.model.CommonResponseModel;
import com.momori.wepic.model.UserModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */

// TODO : FACE BOOK 연동!! 개발 후에 하자!!
public class UserLoginActivity extends Activity{

    @InjectView(R.id.login_text_email)      EditText    textEmail       ;
    @InjectView(R.id.login_text_password)   EditText    textPassword    ;

    UserModel user ;
    CommonResponseModel res ;

    SFValue pref = new SFValue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new
        StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        ButterKnife.inject(this);

        textEmail.setText("geon@gmal.com");
        textPassword.setText("11223344");

        boolean 공유중 = true; //서버에서 확인

        // 공유정보 추출
        if(pref.getValue(SFValue.PREF_IS_SHARE, false) == false && 공유중){    //공유중이라면,
            //TODO : 서버에서 공유 정보 추출
            pref.put(SFValue.PREF_USER_ID , 1);
            pref.put(SFValue.PREF_GROUP_ID, 1);
            pref.put(SFValue.PREF_ALBUM_ID, 1);
        }else{
            pref.put(SFValue.PREF_USER_ID , Const.SF_NULL_INT);
            pref.put(SFValue.PREF_GROUP_ID, Const.SF_NULL_INT);
            pref.put(SFValue.PREF_ALBUM_ID, Const.SF_NULL_INT);
        }

        // 자동 로그인
        if(pref.getValue(SFValue.PREF_AUTO_LOGIN, false)         == true  &&
           pref.getValue(SFValue.PREF_USER_EMAIL, "").equals("") == false &&
           pref.getValue(SFValue.PREF_USER_PASSWORD, "").equals("")    == false ){

            this.user = new UserModel(pref.getValue(SFValue.PREF_USER_EMAIL, ""), pref.getValue(SFValue.PREF_USER_PASSWORD, ""));
            UserController usr = new UserController(this.user);
            this.res = usr.loginUser();

            Log.i(this.getClass().toString(), "log in success");

            finish();
            Intent intentSubActivity = new Intent(UserLoginActivity.this, MainActivity.class);
            startActivity(intentSubActivity);
        }
    }

    @OnClick(R.id.login_button_login)
    public void loginButtonOnclick() {

        if(Func.checkEmailFormat(textEmail.getText().toString()) == false){
            Log.i(this.getClass().toString(), "email format check error");
            return;
        }

        //TODO : 자동 로그인 기능 추가 필요
        this.user = new UserModel(textEmail.getText().toString(), textPassword.getText().toString());
        UserController usr = new UserController(this.user);
        this.res = usr.loginUser();

        if(Func.isPostSucc(this.res.getResult()) == true ){
            // TODO : 다음화면으로 넘어감 => 공유중이면 공유중인 화면 앨범으로.. 공유중이 아니면 그냥 앨범으로?? 결정필요

            if(pref.getValue(SFValue.PREF_AUTO_LOGIN, false) == false){
                pref.put(SFValue.PREF_USER_EMAIL, this.user.getUserEmail());
                pref.put(SFValue.PREF_USER_PASSWORD   , this.user.getUserPw   ());
                pref.put(SFValue.PREF_AUTO_LOGIN, true                    );
            }

            Log.i(this.getClass().toString(), "log in success");

            finish();
            Intent intentSubActivity = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intentSubActivity);
        }
        else {
            // TODO : 오류 출력
            Log.i(this.getClass().toString(), res.getMsg());
        }
    }

    /** 등록 버튼 클릭시 */
    @OnClick(R.id.login_button_reg)
    public void regButtonOnclick() {
        finish();
        Intent intentSubActivity = new Intent(UserLoginActivity.this, UserRegActivity.class);
        startActivity(intentSubActivity);
    }
}



