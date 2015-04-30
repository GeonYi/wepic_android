package com.momori.wepic.presenter.inter;

import com.momori.wepic.activity.adapter.InviteListAdapter;

/**
 * Created by Hyeon on 2015-04-26.
 */
public interface InvitePresenter {

    public void initInviteActivity();

    public void onSelectItem(android.view.View view, int position);

    public void onConfirm();

    public void setView(View view);

    public interface View{
        void showInviteList(InviteListAdapter adapter);
        void setSelectedCount(int count);
        void enableConfirmText(boolean enable);
    }
}
