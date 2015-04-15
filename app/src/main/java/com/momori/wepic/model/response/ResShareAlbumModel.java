package com.momori.wepic.model.response;

import com.momori.wepic.model.AlbumModel;

import java.util.List;

/**
 * Created by sec on 2015-04-13.
 */
public class ResShareAlbumModel {

    private String          result  ;
    private String          msg     ;
    private List<AlbumModel> shared_album   ;

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

    public List<AlbumModel> getShared_album() {
        return shared_album;
    }

    public void setShared_album(List<AlbumModel> shared_album) {
        this.shared_album = shared_album;
    }

    @Override
    public String toString() {
        return "ResShareAlbumModel{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", shared_album=" + shared_album.get(0).getAlbum_id().toString() + "/" + shared_album.get(0).getAlbum_name().toString() +
                '}';
    }
}
