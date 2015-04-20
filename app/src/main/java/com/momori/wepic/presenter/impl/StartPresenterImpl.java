package com.momori.wepic.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.momori.wepic.activity.FbLoginActivity;
import com.momori.wepic.activity.MainActivity;
import com.momori.wepic.activity.StartActivity;
import com.momori.wepic.common.Func;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.common.callback.AsyncCallback;
import com.momori.wepic.controller.post.UserController;
import com.momori.wepic.external.facebook.FbComponent;
import com.momori.wepic.external.gcm.GcmComponent;
import com.momori.wepic.model.FbUserModel;
import com.momori.wepic.model.UserDeviceModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.response.ResLogInModel;
import com.momori.wepic.presenter.inter.StartPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Hyeon on 2015-04-18.
 */
public class StartPresenterImpl implements StartPresenter {
    static final String TAG = StartPresenterImpl.class.getName();
    private final int FBLOGINACTIVITY_REQEST = 1000;

    private StartActivity activity;

    public StartPresenterImpl(StartActivity startActivity) {
        this.activity = startActivity;
    }

    // 최초 앱 실행시 초기화
    public void initApplication(){
        Log.v(TAG, "앱 초기화 셋팅합니다.");
        Context context = this.activity.getApplicationContext();
        SFValue.initInstance(context);
        FbComponent.initFbComponent(context);
        GcmComponent.initInstance(this.activity);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(config);
    }

    public boolean isReadyToLogin(){
        if(!FbComponent.getInstance().isAccessTokenValid()){
            facebookLogin();
            return false;
        }else if(getRegId().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    private void facebookLogin() {
        Log.d(TAG, "Facebook 로그인 Activity 시작");
        Intent intent = new Intent(activity, FbLoginActivity.class);
        activity.startActivityForResult(intent, FBLOGINACTIVITY_REQEST);
    }

    public void wepicLogin(){
        if(isReadyToLogin()){
            Log.i(TAG, "Wepic 로그인 시작");

            UserModel user = createUserModel();
            UserController userController = new UserController(user);
            ResLogInModel resLogin = userController.fbLoginUser();

            if(Func.isPostSucc(resLogin.getResult())){
                Log.i(TAG, "Wepic 로그인 완료  : " + resLogin.getUserId());
                startMainActivity(resLogin.getUserId());
            }else{
                Log.i(TAG, "Wepic 로그인 실패 : " + resLogin.getMsg());
            }
        }
    }

    private UserModel createUserModel(){
        UserModel user = new UserModel();
        FbUserModel fbUserModel = FbComponent.getInstance().getFbUserModel();
        if(fbUserModel!=null){
            user.setExternal_id(fbUserModel.getUser_id());
            user.setUser_email(fbUserModel.getEmail());
        }
        UserDeviceModel userDevice = createUserDeviceModel();
        user.setUserDevice(userDevice);
        return user;
    }

    private UserDeviceModel createUserDeviceModel(){
        TelephonyManager telephonyManager = (TelephonyManager)this.activity.getSystemService(Context.TELEPHONY_SERVICE);
        String dev_id = telephonyManager.getDeviceId();
        String dev_number = telephonyManager.getLine1Number();
        return new UserDeviceModel(dev_id, dev_number, GcmComponent.getInstance().getRegId());
    }

    private String getRegId(){
        GcmComponent gcmComponent = GcmComponent.getInstance();
        String reg_id = gcmComponent.getRegId();
        if(reg_id.isEmpty()){
            Log.i(TAG, "reg_id 를 신규 등록합니다.");
            registRegId(this.activity);
        }
        return reg_id;
    }

    private void registRegId(final StartActivity activity){
        GcmComponent.getInstance().registRegId(new AsyncCallback.AsyncResult<String>() {
            @Override
            public void onResult(String result) {
                Log.d(TAG, "reg_id : " + result);
                activity.checkReadyAndLogin();
            }

            @Override
            public void exceptionOccured(Exception e) {
                Log.e(TAG, "GCM 등록 실패 " + e.getMessage());
                //TODO : 알람 후 확인 누르면 종료
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode== Activity.RESULT_OK){
            if(requestCode == FBLOGINACTIVITY_REQEST){
                Log.i(TAG, "페이스북 계정 확인 완료, 다시 로그인 준비를 체크하고 로그인 합니다.");
                this.activity.checkReadyAndLogin();
            }
        }
    }

    private void startMainActivity(String user_id){
        Log.i(TAG, "MainActivity로 이동");
        Intent intent = new Intent(this.activity, MainActivity.class);
        intent.putExtra(UserModel.USER_ID , user_id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.activity.startActivity(intent);
    }
}