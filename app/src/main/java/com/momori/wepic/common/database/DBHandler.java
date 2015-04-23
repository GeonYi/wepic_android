package com.momori.wepic.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Hyeon on 2015-04-21.
 */
public class DBHandler {
    public static final int READABLE_DATABASE = 0;
    public static final int WRITABLE_DATABASE = 1;

    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler(Context context, int type){
        this.helper = new DBHelper(context);

        if(type==READABLE_DATABASE){
            this.db = helper.getReadableDatabase();
        }else{
            this.db = helper.getWritableDatabase();
        }
    }

    public SQLiteDatabase database(){
        return db;
    }

    public static DBHandler open(Context context, int type) throws SQLException{
        DBHandler handler = new DBHandler(context, type);
        return handler;
    }

    public void close(){
        db.close();
    }

    public long insert(String table, ContentValues values){
        return db.insert(table, null, values);
    }

    public long update(String table, ContentValues values, String whereClause,  String[] whereArgs){
        return db.update(table, values, whereClause, whereArgs);
    }

    public long delete(String table, String whereClause, String[] whereArgs){
        return db.delete(table, whereClause, whereArgs);
    }

    public Cursor select(String table, String[] columns, String selection, String[] selectionArgs
            , String groupBy, String having, String orderBy, String limit){
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }


}
