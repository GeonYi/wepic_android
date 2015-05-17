package com.momori.wepic.model.response;

import com.momori.wepic.model.AlbumModel;

import java.util.List;

/**
 * Created by Hyeon on 2015-05-04.
 */
public class ResAlbumListModel extends ResCommonModel{

    private List<AlbumModel> album_list;

    public List<AlbumModel> getAlbum_list() {
        return album_list;
    }

    public void setAlbum_list(List<AlbumModel> album_list) {
        this.album_list = album_list;
    }
}
