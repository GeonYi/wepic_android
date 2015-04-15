package com.momori.wepic.model.response;

import com.momori.wepic.model.ImageModel;

import java.util.List;

/**
 * Created by sec on 2015-04-13.
 */
public class ResImageListModel {

    private List<ImageModel> shared_album   ;

    public List<ImageModel> getShared_album() {
        return shared_album;
    }

    public void setShared_album(List<ImageModel> shared_album) {
        this.shared_album = shared_album;
    }

    @Override
    public String toString() {
        return "ResImageListModel{" +
                "shared_album=" + shared_album.get(0).getPhoto_ori_url().toString() + shared_album.get(0).getPhoto_thumb_url().toString() +
                '}';
    }
}
