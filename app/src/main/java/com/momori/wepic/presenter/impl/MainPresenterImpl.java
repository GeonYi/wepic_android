package com.momori.wepic.presenter.impl;

import android.os.AsyncTask;
import android.util.Log;

import com.momori.wepic.WepicApplication;
import com.momori.wepic.activity.MainActivity;
import com.momori.wepic.activity.fragment.AlbumCardFragment;
import com.momori.wepic.common.Func;
import com.momori.wepic.controller.post.AlbumController;
import com.momori.wepic.model.AlbumModel;
import com.momori.wepic.model.response.ResAlbumListModel;
import com.momori.wepic.presenter.inter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyeon on 2015-05-04.
 */
public class MainPresenterImpl implements MainPresenter{
    static final String TAG = MainPresenterImpl.class.getName();

    private WepicApplication context;
    private MainActivity activity;
    private MainPresenter.View view;

    private List<AlbumModel> album_list;

    public MainPresenterImpl(MainActivity mainActivity){
        this.activity = mainActivity;
        this.context = WepicApplication.getInstance();
    }

    @Override
    public void initAlbum_list() {
        this.album_list = getAlbum_list();


    }

    @Override
    public void setView(View view) {
        this.view = view;
    }


    @Override
    public void showAlbumCardFragment(final int fragment_layout, final AlbumCardFragment albumCardFragment){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
                album_list =  getAlbum_list();
                return null;
            }

            @Override
            protected void onPostExecute(Object obj){
                activity.getSupportFragmentManager().beginTransaction().add(fragment_layout, albumCardFragment).commit();
            }

        }.execute();
    }

    public List<AlbumModel> getAlbum_list(){
        if(this.album_list==null){
            this.album_list =  callAlbum_list(this.context.getUser_id());
        }
        return this.album_list;
    }

    private List<AlbumModel> callAlbum_list(String user_id){
        AlbumController  controller = new AlbumController();
        ResAlbumListModel res = controller.getAlbum_list(user_id);
        if(Func.isPostSucc(res)){
            return res.getAlbum_list();
        }else{
            return new ArrayList<>();
        }
    }
}
