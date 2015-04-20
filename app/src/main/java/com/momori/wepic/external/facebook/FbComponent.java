package com.momori.wepic.external.facebook;

import android.content.Context;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

/**
 * Created by Hyeon on 2015-04-19.
 */
public class FbComponent {
    static final String TAG = FbComponent.class.getName();

    private static FbComponent fbComponent;
    private Context context;

    public static void initFbComponent(Context context){
        fbComponent = new FbComponent(context);
        Log.d(TAG, "FbComponent 싱글톤 객체 생성");
    }

    public static FbComponent getInstance() {
        return fbComponent;
    }

    private FbComponent(Context context) {
        if(!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(context);
            Log.i(TAG, "FacebookSdk 초기화");
        }
        this.context = context;
    }

    public boolean isAccessTokenValid(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken!=null && !accessToken.isExpired() ?  true : false;
    }
}
