package com.momori.wepic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;

import com.momori.wepic.common.Func;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.controller.UserController;
import com.momori.wepic.model.response.ResCommonModel;
import com.momori.wepic.model.response.ResLogInModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.response.ResShareAlbumModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class UserRegActivity extends Activity {

    @InjectView(R.id.reg_text_email)      EditText    textEmail       ;
    @InjectView(R.id.reg_text_password)   EditText    textPassword    ;
    @InjectView(R.id.reg_text_nickname)   EditText    textNickname    ;

    UserModel           userVo      ;
    ResCommonModel      resCommon   ;
    ResLogInModel       resLogIn    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.reg_button_reg)
    public void regButtonOnclick() {

        Log.i(this.getClass().toString(), "test");


        if(Func.checkEmailFormat(textEmail.getText().toString()) == false){
            Log.i(this.getClass().toString(), "email format check error");
            return;
        }

        // device 번호 추출
        TelephonyManager systemService = (TelephonyManager)getSystemService (Context.TELEPHONY_SERVICE);
        String PhoneNumber = systemService.getLine1Number();
        PhoneNumber = "0" + PhoneNumber.substring(PhoneNumber.length()-10,PhoneNumber.length());
        Log.i(this.getClass().toString(), "phone num : " + PhoneNumber);

        // 회원가입
        UserModel user = new UserModel(textEmail.getText().toString(), textPassword.getText().toString(), textNickname.getText().toString(), PhoneNumber, systemService.getDeviceId());
        UserController usr = new UserController(user);
        this.resCommon = usr.registUser();

        if(Func.isPostSucc(this.resCommon.getResult()) == true ){ // 회원가입 성공시
            Log.i(this.getClass().toString(), "user reg success");

            // TODO : 회원 가입 후 정상 가입 여부 출력
            // 일단은 회원가입 후 자동 로그인 시킨다!!
            this.userVo = new UserModel(textEmail.getText().toString(), textPassword.getText().toString());
            this.resLogIn = usr.loginUser();

            if(Func.isPostSucc(this.resLogIn.getResult()) == true ){
                SFValue pref = SFValue.getInstance();
                if(pref.getValue(SFValue.PREF_AUTO_LOGIN, false) == false){
                   pref.put(SFValue.PREF_USER_EMAIL, this.userVo.getUserEmail());
                   pref.put(SFValue.PREF_USER_ID   , this.userVo.getUserPw   ());
                   pref.put(SFValue.PREF_AUTO_LOGIN, true                      );
                }

                Log.i(this.getClass().toString(), "login success");

                finish();
                Intent intentSubActivity = new Intent(UserRegActivity.this, MainActivity.class);
                startActivity(intentSubActivity);
            }
        }
        else {
            // TODO : 오류 출력
            Log.i(this.getClass().toString(), resCommon.getMsg());
        }
    }
}
