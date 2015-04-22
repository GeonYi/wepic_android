package com.momori.wepic.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.momori.wepic.WepicApplication;
import com.momori.wepic.common.Const;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.controller.post.ImageController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ImageCatchService extends Service {

    private long preTime = 0;
    private Cursor mediaDbCur = null;

    private int userId  = 0;
    private int albumId = 0;

    private ContentObserver inContentObserver;
    private ContentObserver exContentObserver;

    /**
     * 새로운 image파일이 catch되면
     *  - media store에서 파일 경로 추출
     *  - 추출된 파일 upload
     * */

    @Override
    public void onCreate() {
        Toast.makeText(this, "service start", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "서비스 onStartCommand", Toast.LENGTH_SHORT).show();
        this.preTime = Long.valueOf(String.valueOf(System.currentTimeMillis()).substring(0, 10));

        SFValue pref = ((WepicApplication)getApplicationContext()).getSfValue();
        this.userId  = pref.getValue(SFValue.PREF_USER_ID , Const.SF_NULL_INT);
        this.albumId = pref.getValue(SFValue.PREF_ALBUM_ID, Const.SF_NULL_INT);

        Log.i(getClass().toString(), "PREF_USER_ID  : " + this.userId);
        Log.i(getClass().toString(), "PREF_ALBUM_ID : " + this.albumId);

        shareImage();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(inContentObserver);
        getContentResolver().unregisterContentObserver(exContentObserver);

        Toast.makeText(this, "서비스 onDestro", Toast.LENGTH_SHORT).show();

        Log.i(getClass().toString(), "service onDestroy");
    }

    private void shareImage(){
        this.exContentObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                Log.i(getClass().toString(),"External Media has been added!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                getNewImageInfo(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                super.onChange(selfChange);
            }
        };

        this.inContentObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                Log.i(getClass().toString(),"Internal Media has been added!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                getNewImageInfo(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                super.onChange(selfChange);
            }
        };

        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // if external content get
                getContentResolver().registerContentObserver(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, exContentObserver);
                // if internal content get
                getContentResolver().registerContentObserver(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI, false, inContentObserver);
            }
        }, 0);
    }

    protected void getNewImageInfo(Uri uri){
        try {
            ContentResolver cr = getContentResolver();

            Log.i(getClass().toString(), "search new image file");
            Log.i(getClass().toString(), "uri : " + uri.toString());
            mediaDbCur = cr.query(uri,
                    null,
                    "date_modified > " + this.preTime,
                    null,
                    null);

            if(mediaDbCur.getCount() > 0){
                if(mediaDbCur.moveToLast()){
                    do{
                        String addTime = getYYYYmmDD(Long.parseLong(mediaDbCur.getString(mediaDbCur.getColumnIndex(MediaStore.Images.ImageColumns.DATE_ADDED))));
                        Log.i(getClass().toString(), "file path  : " + mediaDbCur.getString(mediaDbCur.getColumnIndex(MediaStore.Images.ImageColumns.DATA)));
                        Log.i(getClass().toString(), "add time yyyymmdd : " + addTime);

                        //upload image
                        ImageController imgCtl = new ImageController( mediaDbCur.getString(mediaDbCur.getColumnIndex(MediaStore.Images.ImageColumns.DATA)));
                        imgCtl.uploadImage(addTime, this.albumId, this.userId);

                    }while(mediaDbCur.moveToNext());
                    this.preTime = Long.valueOf(String.valueOf(System.currentTimeMillis()).substring(0, 10));
                }
            } else {
                Log.i(getClass().toString(), "delete image file.. skip.........!!");
                this.preTime = Long.valueOf(String.valueOf(System.currentTimeMillis()).substring(0, 10));
            }
            mediaDbCur.close();

        }
            catch (Exception e) {
        }
    }

    private String getYYYYmmDD(Long secondTime) {
        Date date = new Date(secondTime * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return sdf.format(date);
    }
}
