package com.momori.wepic.external.facebook;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.momori.wepic.WepicApplication;
import com.momori.wepic.model.UserModel;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hyeon on 2015-04-19.
 */
public class FbComponent {
    static final String TAG = FbComponent.class.getName();

    private WepicApplication context;
    private ProfileTracker profileTracker;

    public FbComponent(WepicApplication context) {
        if(!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(context);
            Log.i(TAG, "FacebookSdk 초기화");
        }
        this.profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                syncLoginUser();
            }
        };

        AccessToken.getCurrentAccessToken();
        Profile.getCurrentProfile();
        this.context = context;
        Log.d(TAG, "FbComponent 객체 생성");
    }

    public boolean isAccessTokenValid(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken!=null && !accessToken.isExpired() ?  true : false;
    }

    // 비동기로 Facebook 내 정보를 동기화한다.
    public void syncLoginUser(){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
                callFbLoginUser();
               return null;
            }
        }.execute();
    }

    // Facebook에서 내 정보를 불러 셋팅한다.
    public UserModel callFbLoginUser(){
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        UserModel loginUser = this.context.getLoginUser();
        try{
            Log.i(TAG, "Facebook 사용자 정보를 가져옵니다.");
            GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(),"/me" );
            GraphResponse response = request.executeAndWait();
            JSONObject result = response.getJSONObject();

            String fb_user_id = result.optString("id");
            loginUser.setExternal_id(fb_user_id);
            loginUser.setUser_email(result.optString("email"));
            loginUser.setUser_name(result.optString("name"));

            URL url = new URL("https://graph.facebook.com/"+fb_user_id+"/picture?type=small");
            urlConnection = (HttpURLConnection)url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            loginUser.setUser_picture(BitmapFactory.decodeStream(in));

        }catch(Exception e){
            Log.e(TAG, "Facebook 사용자 정보 조회 실패 : " + e.getMessage());
            new RuntimeException(e);
        }finally {
            if(in!=null) try{in.close();}catch(Exception e){}
            if(urlConnection!=null) try{urlConnection.disconnect();}catch(Exception e){}
        }
        return loginUser;
    }

}
