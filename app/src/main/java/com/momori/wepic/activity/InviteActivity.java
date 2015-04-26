package com.momori.wepic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.momori.wepic.R;
import com.momori.wepic.activity.adapter.InviteListAdapter;
import com.momori.wepic.presenter.impl.InvitePresenterImpl;
import com.momori.wepic.presenter.inter.InvitePresenter;


/**
 * Created by Hyeon on 2015-04-23.
 */
public class InviteActivity extends Activity implements InvitePresenter.View{
    static final String TAG = InviteActivity.class.getName();

    private InvitePresenter presenter;

    private ListView inviteListView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        this.presenter = new InvitePresenterImpl(InviteActivity.this);
        this.presenter.setView(this);

        this.inviteListView = (ListView)findViewById(R.id.inviteList);
        this.inviteListView.setOnItemClickListener(inviteListItemClickListner());


        Log.i(TAG, "초대 화면 시작");
        presenter.initInviteActivity();
    }

    @Override
    public void showInviteList(InviteListAdapter adapter){
        this.inviteListView.setAdapter(adapter);
    }

    public AdapterView.OnItemClickListener inviteListItemClickListner() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onSelect(view, position);
            }
        };
    }
}
