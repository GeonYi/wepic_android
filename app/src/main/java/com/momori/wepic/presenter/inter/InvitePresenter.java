package com.momori.wepic.presenter.inter;

import android.view.View;

import com.momori.wepic.activity.adapter.InviteListAdapter;

/**
 * Created by Hyeon on 2015-04-26.
 */
public interface InvitePresenter {

    public void initInviteActivity();

    public void onSelect(android.view.View view, int position);

    public void setView(View view);

    public interface View{
        void showInviteList(InviteListAdapter adapter);
    }
}
