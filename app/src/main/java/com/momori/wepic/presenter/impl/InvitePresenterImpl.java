package com.momori.wepic.presenter.impl;

import android.os.AsyncTask;
import android.util.Log;

import com.momori.wepic.R;
import com.momori.wepic.WepicApplication;
import com.momori.wepic.activity.InviteActivity;
import com.momori.wepic.model.InviteModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.presenter.adapter.InviteListAdapter;
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
        new AsyncTask<Object, Void, List<UserModel>>() {
            @Override
            protected List<UserModel> doInBackground(Object[] params) {
                Log.i(TAG, "친구 초대 리스트 비동기 출력 시작");
                List<UserModel> inviteList = model.getInviteList();

                //TODO: 테스트를 위해 로그인 유저 포함 지워야한다.
                inviteList.add(0, context.getLoginUser());
                return inviteList;
            }

            @Override
            protected void onPostExecute(List<UserModel> inviteList){
                if(view!=null){
                    Log.d(TAG, "친구리스트 화면에 보여주기 갯수 : " + inviteList.size());
                    InviteListAdapter inviteListAdapter = new InviteListAdapter(context, R.layout.activity_invite_list_item, inviteList);
                    view.showInviteList(inviteListAdapter);
                }
            }
        }.execute();
    }
}
