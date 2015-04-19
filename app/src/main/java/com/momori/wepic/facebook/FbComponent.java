package com.momori.wepic.facebook;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

/**
 * Created by Hyeon on 2015-04-19.
 */
public class FbComponent {
    private static FbComponent fbComponent;
    private Context context;

    public static void initFbComponent(Context context){
        fbComponent = new FbComponent(context);
    }

    public static FbComponent getInstance() {
        return fbComponent;
    }

    private FbComponent(Context context) {
        if(!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(context);
        }
        this.context = context;
    }

    public boolean isAccessTokenValid(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken!=null && !accessToken.isExpired() ?  true : false;
    }
}
