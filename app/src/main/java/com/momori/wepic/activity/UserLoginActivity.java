package com.momori.wepic.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.momori.wepic.R;
import com.momori.wepic.activity.material.ContactEditText;
import com.momori.wepic.common.Const;
import com.momori.wepic.common.Func;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.controller.post.AlbumController;
import com.momori.wepic.controller.post.UserController;
import com.momori.wepic.model.response.ResLogInModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.response.ResShareAlbumModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */

// TODO : FACE BOOK 연동!! 개발 후에 하자!!
public class UserLoginActivity extends Activity{

    @InjectView(R.id.login_text_email)      ContactEditText textEmail       ;
    @InjectView(R.id.login_text_password)   ContactEditText textPassword    ;

    UserModel           userVo      ;
    ResLogInModel       resLogIn    ;
    ResShareAlbumModel  resShareAlbum   ;

    SFValue pref = SFValue.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new
        StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        ButterKnife.inject(this);

        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(config);
/*
        //todo : will delete
        textEmail.setText("geon@gmal.com");
        textPassword.setText("11223344");

        // 자동 로그인
        if(pref.getValue(SFValue.PREF_AUTO_LOGIN, false)         == true  &&
           pref.getValue(SFValue.PREF_USER_EMAIL, "").equals("") == false &&
           pref.getValue(SFValue.PREF_USER_PASSWORD, "").equals("")    == false
                && false //todo : will delete
                ){

            this.userVo = new UserModel(pref.getValue(SFValue.PREF_USER_EMAIL, ""), pref.getValue(SFValue.PREF_USER_PASSWORD, ""));
            UserController usr = new UserController(this.userVo);
            this.resLogIn = usr.loginUser();

            this.userVo.setUserId(this.resLogIn.getUserId());

            pref.put(SFValue.PREF_USER_ID , Integer.parseInt(this.resLogIn.getUserId()));

            Log.i(this.getClass().toString(), "auto log in success");
            Log.i(this.getClass().toString(), "re : " + this.resLogIn.getResult());
            Log.i(this.getClass().toString(), "ms : " + this.resLogIn.getMsg());
            Log.i(this.getClass().toString(), "id : " + this.resLogIn.getUserId());

            if(Func.isPostSucc(this.resLogIn.getResult()) == true ){

                this.getUserAlbumInfo();

                finish();
                Intent intentSubActivity = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intentSubActivity);
            }
        }
        */
    }

    @OnClick(R.id.login_button_login)
    public void loginButtonOnclick() {
/*
        // email validation check
        if(Func.checkEmailFormat(textEmail.getText().toString()) == false){
            //todo : email error일때 에러처리
            Log.i(this.getClass().toString(), "email format check error");
            return;
        }

        //todo : password validaion check add

        this.userVo = new UserModel(textEmail.getText().toString(), textPassword.getText().toString());
        UserController usr = new UserController(this.userVo);
        this.resLogIn = usr.loginUser();
        this.userVo.setUserId(this.resLogIn.getUserId());
        pref.put(SFValue.PREF_USER_ID , Integer.parseInt(this.resLogIn.getUserId()));

        if(Func.isPostSucc(this.resLogIn.getResult()) == true ){

            pref.put(SFValue.PREF_USER_EMAIL    , this.userVo.getUserEmail());
            pref.put(SFValue.PREF_USER_PASSWORD , this.userVo.getUserPw   ());
            pref.put(SFValue.PREF_AUTO_LOGIN    , true                      );

            this.getUserAlbumInfo();

            Log.i(this.getClass().toString(), this.resLogIn.getMsg());
            Log.i(this.getClass().toString(), this.resLogIn.getUserId());

            finish();
            Intent intentSubActivity = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intentSubActivity);
        }
        else {
            Log.i(this.getClass().toString(), resLogIn.getMsg());
        }
        */
    }

    /** 등록 버튼 클릭시 */
    @OnClick(R.id.login_button_reg)
    public void regButtonOnclick() {
        finish();
        Intent intentSubActivity = new Intent(UserLoginActivity.this, UserRegActivity.class);
        startActivity(intentSubActivity);
    }

    private void getUserAlbumInfo(){

        AlbumController albumCtl = new AlbumController(this.userVo);
        this.resShareAlbum = albumCtl.getSharedAlbumInfo();

        // 공유정보 추출
        if(this.resShareAlbum.getShared_album() != null){    //공유중이라면,

            Log.i(getClass().toString(), "공유중");

            //TODO : 서버에서 공유 정보 추출
            pref.put(SFValue.PREF_IS_SHARE  , true);
            pref.put(SFValue.PREF_GROUP_ID  , Integer.valueOf(this.resShareAlbum.getShared_album().get(0).getGroup_id()));
            pref.put(SFValue.PREF_ALBUM_ID  , Integer.valueOf(this.resShareAlbum.getShared_album().get(0).getAlbum_id()));
            pref.put(SFValue.PREF_SHARE_ALBUM_NAME, this.resShareAlbum.getShared_album().get(0).getAlbum_name());
        }else{
            pref.put(SFValue.PREF_IS_SHARE, false);
            pref.put(SFValue.PREF_GROUP_ID, Const.SF_NULL_INT);
            pref.put(SFValue.PREF_ALBUM_ID, Const.SF_NULL_INT);
            pref.put(SFValue.PREF_SHARE_ALBUM_NAME, null);
        }
    }
}



