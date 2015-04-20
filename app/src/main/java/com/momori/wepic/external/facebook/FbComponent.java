package com.momori.wepic.external.facebook;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.momori.wepic.model.FbUserModel;

import org.json.JSONObject;

/**
 * Created by Hyeon on 2015-04-19.
 */
public class FbComponent {
    static final String TAG = FbComponent.class.getName();

    private static FbComponent fbComponent;
    private Context context;

    private FbUserModel fbUserModel;

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

    public FbUserModel getFbUserModel(){
        if(this.fbUserModel==null){
            try{
                Log.i(TAG, "Facebook 사용자 정보를 가져옵니다.");
                GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(),"/me" );
                GraphResponse response = request.executeAndWait();
                JSONObject result = response.getJSONObject();
                this.fbUserModel = new FbUserModel(result.optString("id")
                        , result.optString("email")
                        , result.optString("name"));
            }catch(Exception e){
                Log.e(TAG, "Facebook 사용자 정보 조회 실패 : " + e.getMessage());
                new RuntimeException(e);
            }
        }
        return this.fbUserModel;
    }
}
