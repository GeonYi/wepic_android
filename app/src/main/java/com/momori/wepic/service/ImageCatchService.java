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

public class ImageCatchService extends Service {

    private long preTime = 0;
    private Cursor mediaDbCur = null;

    /**
     * 새로운 image파일이 catch되면
     *  - media store에서 파일 경로 추출
     *  - 추출된 파일 upload
     * */

    public ImageCatchService() {
    }

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
        Log.i(getClass().toString(), "first share sec : " + this.preTime);
        shareImage();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "서비스 onDestro", Toast.LENGTH_SHORT).show();
    }

    private void shareImage(){
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // if external content get
                getContentResolver().registerContentObserver(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false,
                        new ContentObserver(new Handler()) {
                            @Override
                            public void onChange(boolean selfChange) {
                                Log.i(getClass().toString(),"External Media has been added!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                getNewImageInfo(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                super.onChange(selfChange);
                            }
                        });
                // if internal content get
                getContentResolver().registerContentObserver(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI, false,
                        new ContentObserver(new Handler()) {
                            @Override
                            public void onChange(boolean selfChange) {
                                Log.i(getClass().toString(),"Internal Media has been added!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                getNewImageInfo(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                super.onChange(selfChange);
                            }
                        });
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
                if(mediaDbCur.moveToFirst()){
                    do{
                        Log.i(getClass().toString(), "file name  : " + mediaDbCur.getString(mediaDbCur.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)));
                        Log.i(getClass().toString(), "file path  : " + mediaDbCur.getString(mediaDbCur.getColumnIndex(MediaStore.Images.ImageColumns.DATA)));
                        Log.i(getClass().toString(), "data added : " + mediaDbCur.getString(mediaDbCur.getColumnIndex(MediaStore.Images.ImageColumns.DATE_ADDED)));
                        Log.i(getClass().toString(), "mini_thubm2 : " + mediaDbCur.getString(mediaDbCur.getColumnIndex(MediaStore.MediaColumns._ID)));
                    }while(mediaDbCur.moveToNext());
                    this.preTime = Long.valueOf(String.valueOf(System.currentTimeMillis()).substring(0, 10));
                }
            } else {
                Log.i(getClass().toString(), "delete image file.. skip.........!!");
            }
            mediaDbCur.close();

        }
            catch (Exception e) {
        }
    }
}
