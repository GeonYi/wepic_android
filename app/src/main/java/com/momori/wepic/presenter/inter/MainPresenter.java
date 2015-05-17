package com.momori.wepic.presenter.inter;

import com.momori.wepic.activity.fragment.AlbumCardFragment;
import com.momori.wepic.model.AlbumModel;

import java.util.List;

/**
 * Created by Hyeon on 2015-05-04.
 */
public interface MainPresenter {

    public void initAlbum_list();

    public void showAlbumCardFragment(int fragment_layout, AlbumCardFragment albumCardFragment);

    public List<AlbumModel> getAlbum_list();

    public void setView(View view);

    public interface View{

    }
}
