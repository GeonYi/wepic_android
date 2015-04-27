package com.momori.wepic.model.response;

/**
 * Created by Hyeon on 2015-04-27.
 */
public class ResGcmInviteModel {

    private String result;
    private String msg;
    private String album_id;

    public ResGcmInviteModel(String result, String msg, String album_id) {
        this.result = result;
        this.msg = msg;
        this.album_id = album_id;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
