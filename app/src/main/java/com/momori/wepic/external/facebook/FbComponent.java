package com.momori.wepic.external.facebook;

import android.graphics.Bitmap;
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
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyeon on 2015-04-19.
 */
public class FbComponent {
    static final String TAG = FbComponent.class.getName();

    private WepicApplication app;
    private ProfileTracker profileTracker;

    public FbComponent() {
        this.app = WepicApplication.getInstance();
        if(!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(app);
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

        Log.i(TAG, "Facebook 사용자 정보를 가져옵니다.");
        GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(),"/me" );
        GraphResponse response = request.executeAndWait();
        JSONObject result = response.getJSONObject();

        UserModel loginUser = this.app.getLoginUser();
        String fb_user_id = result.optString("id");
        loginUser.setExternal_id(fb_user_id);
        loginUser.setUser_email(result.optString("email"));
        loginUser.setUser_name(result.optString("name"));

        return loginUser;
    }

    public String getPictureUrl(String fb_user_id, String type){
        return "https://graph.facebook.com/"+fb_user_id+"/picture?type="+type;
    }


    public List<UserModel> getFbFriendsList(){

        List<UserModel> friendList = new ArrayList<UserModel>();

        try{
            Log.i(TAG, "Facebook 친구 정보를 가져옵니다.");
            GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(),"/me/friends" );
            GraphResponse response = request.executeAndWait();
            JSONObject result = response.getJSONObject();
            JSONArray array = result.getJSONArray("data");

            int length = array.length();
            for(int i=0; i< length; i++){
                JSONObject friendJson = array.getJSONObject(i);

                UserModel friend = new UserModel();
                String id = friendJson.optString("id");
                friend.setExternal_id(id);
                friend.setUser_name(friendJson.optString("name"));

                friendList.add(friend);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Facebook 친구 정보 조회 실패 : " + e.getMessage());
            new RuntimeException(e);
        }
        return friendList;
    }
}
