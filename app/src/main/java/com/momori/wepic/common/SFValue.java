package com.momori.wepic.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.momori.wepic.WepicApplication;

/**
 * Created by sec on 2015-04-10.
 */
public final class SFValue {
    static final String TAG = SFValue.class.getName();

    private final static String PREF_NAME = "com.momori.pref";

    public final static String PREF_AUTO_LOGIN    = "PREF_AUTO_LOGIN"   ;
    public final static String PREF_USER_EMAIL    = "PREF_USER_EMAIL"   ;
    public final static String PREF_USER_PASSWORD = "PREF_USER_PASSWORD";
    public final static String PREF_USER_ID    = "PREF_USER_ID";
    public final static String PREF_IS_SHARE   = "PREF_IS_SHARE";
    public final static String PREF_GROUP_ID   = "PREF_GROUP_ID";
    public final static String PREF_ALBUM_ID   = "PREF_ALBUM_ID";
    public final static String PREF_SHARE_ALBUM_NAME   = "PREF_SHARE_ALBUM_NAME";

    public final static  String PREF_REG_ID = "PREF_REG_ID"; //API Key
    public final static  String PREF_APP_VERSION = "PREF_APPVERSION";

    private WepicApplication mContext;

    public SFValue() {
        Log.d(TAG, "SFValue 객체 생성");
        mContext = WepicApplication.getInstance();
    }


    public void put(String key, String value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(key, value);
        editor.commit();
    }

    public String getValue(String key, String dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }

    }

    public int getValue(String key, int dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getInt(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }

    }

    public boolean getValue(String key, boolean dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getBoolean(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }
}
