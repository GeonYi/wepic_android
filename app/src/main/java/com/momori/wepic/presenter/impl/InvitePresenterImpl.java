package com.momori.wepic.presenter.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.momori.wepic.R;
import com.momori.wepic.WepicApplication;
import com.momori.wepic.activity.AlbumViewActivity;
import com.momori.wepic.activity.InviteActivity;
import com.momori.wepic.model.AlbumModel;
import com.momori.wepic.model.InviteModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.activity.adapter.InviteListAdapter;
import com.momori.wepic.presenter.inter.InvitePresenter;

import java.util.List;

/**
 * Created by Hyeon on 2015-04-26.
 */
public class InvitePresenterImpl implements InvitePresenter{
    static final String TAG = InvitePresenterImpl.class.getName();

    private WepicApplication context;
    private InviteActivity activity;
    private InvitePresenter.View view;
    private InviteModel model;

    private InviteListAdapter inviteListAdapter;

    public InvitePresenterImpl(InviteActivity inviteActivity){
        this.activity = inviteActivity;
        this.context = WepicApplication.getInstance();
        this.model = new InviteModel();
    }
    @Override
    public void setView(InvitePresenter.View view) {
        this.view = view;
    }

    @Override
    public void initInviteActivity(){
        presentInviteList();
    }

    private void presentInviteList(){
        if(inviteListAdapter != null){
            view.showInviteList(inviteListAdapter);
        }else{
            presentNewInviteList();
        }
    }

    private void presentNewInviteList(){
        new AsyncTask<Object, Void, List<UserModel>>() {
            @Override
            protected List<UserModel> doInBackground(Object[] params) {
                Log.i(TAG, "친구 초대 리스트 비동기 출력 시작");
                List<UserModel> inviteList = model.getInviteList();
                return inviteList;
            }

            @Override
            protected void onPostExecute(List<UserModel> inviteList){
                if(view!=null){
                    Log.d(TAG, "친구리스트 화면에 보여주기 갯수 : " + inviteList.size());
                    inviteListAdapter = new InviteListAdapter(activity, R.layout.activity_invite_list_item, inviteList);
                    presentInviteList();
                }
            }
        }.execute();
    }

    public void onSelectItem(android.view.View view , int position){
       boolean isChecked = this.inviteListAdapter.toggleCheck(view);
       String selectedExternal_id = this.inviteListAdapter.getExternal_id(position);
       if(isChecked){
           this.model.addSelectedExternal_id(selectedExternal_id);
       }else{
           this.model.removeSelectedExternal_id(selectedExternal_id);
       }
       int selectedCount = this.model.getSelectedCount();
       this.view.setSelectedCount(selectedCount);
       this.view.enableConfirmText(selectedCount > 0);
    }

    @Override
    public void onConfirm() {
        UserModel loginUser = this.context.getLoginUser();
        List<String> selectedList = this.model.getSelectedList();
        sendInviteAlbum(loginUser, selectedList, null);
    }

    private void sendInviteAlbum(final UserModel loginUser, final List<String> selectedList, final String album_id){
        new AsyncTask<Object, Void, String>(){
            @Override
            protected String doInBackground(Object[] params) {
                return context.getGcmComponent().sendInviteAlbum(loginUser, selectedList, album_id);
            }

            @Override
            protected void onPostExecute(String album_id){
                if(!album_id.isEmpty()){
                    startAlbumViewActivity(album_id);
                }else{
                    Log.e(TAG, "앨범 초대 실패");
                    //TODO : 알람 띄우고 재시도 하도록 한다.
                }
            }
        }.execute();
    }

    private void startAlbumViewActivity(String album_id){
        Log.i(TAG, "AlbumViewActivity로 이동");
        Intent intent = new Intent(this.activity, AlbumViewActivity.class);
        intent.putExtra(AlbumModel.ALBUM_ID, album_id);

        this.activity.finish();
        this.activity.startActivity(intent);
    }
}
