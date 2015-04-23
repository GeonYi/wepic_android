package com.momori.wepic.common.database.repository;

import android.content.ContentValues;

import com.momori.wepic.common.database.DBHandler;
import com.momori.wepic.model.UserModel;

/**
 * Created by Hyeon on 2015-04-23.
 */
public class FriendsRepository {

    private DBHandler dbHandler;

    public FriendsRepository(DBHandler dbHandler){
        this.dbHandler = dbHandler;
    }

    public long insertFriend(UserModel user){
        ContentValues values = new ContentValues();
        values.put("user_id", user.getUser_id());
        values.put("external_id", user.getExternal_id());
        values.put("name" , user.getUser_name());
        return dbHandler.insert("FRIENDS", values);
    }

    public long insertFriendsPicture(String user_id, String picture_url, byte[] picture){
        ContentValues values = new ContentValues();
        values.put("user_id", user_id);
        values.put("picture_url", picture_url);
        values.put("picture" , picture);
        return dbHandler.insert("FRIENDS_PICTURE", values);
    }
}
