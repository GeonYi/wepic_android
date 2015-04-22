package com.momori.wepic.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Hyeon on 2015-04-21.
 */
public class DBHandler {

    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler(Context context){
        this.helper = new DBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public SQLiteDatabase database(){
        return db;
    }

    public static DBHandler open(Context context) throws SQLException{
        DBHandler handler = new DBHandler(context);
        return handler;
    }

    public void close(){
        db.close();
        helper.close();
    }
}
