package com.momori.wepic;

import android.app.Application;
import android.util.Log;

import com.momori.wepic.common.SFValue;
import com.momori.wepic.common.database.DBHandler;
import com.momori.wepic.external.facebook.FbComponent;
import com.momori.wepic.external.gcm.GcmComponent;
import com.momori.wepic.model.UserModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Hyeon on 2015-04-22.
 */
public class WepicApplication extends Application{
    public static final String TAG = "WepicApplication";

    private static WepicApplication application;

    // 로그인 유저 모델
    private UserModel loginUser;

    private SFValue sfValue;
    private FbComponent fbComponent;
    private GcmComponent gcmComponent;

    private DBHandler dbHandler;

    @Override
    public void onCreate(){
        super.onCreate();
        application = this;
        initApplication();
    }

    public static WepicApplication getInstance(){
        return application;
    }

    private void initApplication(){
        Log.v(TAG, "Wepic 앱 초기화 셋팅합니다.");
        this.loginUser = new UserModel();

        this.sfValue = new SFValue();
        this.fbComponent = new FbComponent();
        this.gcmComponent = new GcmComponent();
        initImageLoader();
    }

    private void initImageLoader(){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }

    public SFValue getSfValue() {
        return sfValue;
    }

    public FbComponent getFbComponent() {
        return fbComponent;
    }

    public GcmComponent getGcmComponent() {
        return gcmComponent;
    }

    public boolean isLoggedin(){
        return this.loginUser!=null && this.loginUser.getUser_id()!=null && !this.loginUser.getUser_id().isEmpty();
    }

    public UserModel getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserModel loginUser) {
        this.loginUser = loginUser;
    }
}
