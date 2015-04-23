package com.momori.wepic.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hyeon on 2015-04-21.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "wipicdatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
       db.execSQL("CREATE TABLE FRIENDS (user_id TEXT PRIMARY KEY, external_id TEXT, name TEXT);");
       db.execSQL("CREATE TABLE FRIENDS_PICTURE (user_id TEXT PRIMARY KEY, picture_url TEXT, picture BLOB" +
               ", CONSTRAINT user_id_fk FOREIGN KEY(user_id) REFERENCES USER(user_id) );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FRIENDS");
        db.execSQL("DROP TABLE IF EXISTS FRIENDS_PICTURE");
        onCreate(db);
    }
}
