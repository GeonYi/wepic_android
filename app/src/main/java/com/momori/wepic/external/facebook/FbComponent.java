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

import org.json.JSONArray;
import org.json.JSONException;
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

    public FbComponent() {
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
        this.context = WepicApplication.getInstance();
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

        UserModel loginUser = this.context.getLoginUser();
        String fb_user_id = result.optString("id");
        loginUser.setExternal_id(fb_user_id);
        loginUser.setUser_email(result.optString("email"));
        loginUser.setUser_name(result.optString("name"));

        Bitmap user_picture = getFbUserPicture(fb_user_id, "small");
        loginUser.setUser_picture(user_picture);

        return loginUser;
    }

    private Bitmap getFbUserPicture(String fb_user_id, String type){
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        try{
            URL url = new URL("https://graph.facebook.com/"+fb_user_id+"/picture?type="+ type);
            urlConnection = (HttpURLConnection)url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

        }catch(Exception e){
            Log.e(TAG, "Facebook 사용자 프로필 조회 실패 : " + e.getMessage());
            new RuntimeException(e);
        }finally {
            if(in!=null) try{in.close();}catch(Exception e){}
            if(urlConnection!=null) try{urlConnection.disconnect();}catch(Exception e){}
        }

        return BitmapFactory.decodeStream(in);
    }

    public void syncFriends(){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
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
                    friend.setExternal_id(friendJson.optString("id"));
                    friend.setUser_email(friendJson.optString("name"));

                    // TODO : user_id를 얻어온 후 DB 에 동기화 해야함
                }

             } catch (JSONException e) {
                Log.e(TAG, "Facebook 친구 정보 조회 실패 : " + e.getMessage());
                new RuntimeException(e);
            }
                return null;
            }
        }.execute();
    }
}
