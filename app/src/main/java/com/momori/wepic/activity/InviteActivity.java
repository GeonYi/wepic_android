package com.momori.wepic.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.momori.wepic.R;
import com.momori.wepic.WepicApplication;
import com.momori.wepic.external.facebook.FbComponent;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.presenter.adapter.InviteListAdapter;
import com.momori.wepic.presenter.impl.InvitePresenterImpl;
import com.momori.wepic.presenter.inter.InvitePresenter;
import com.momori.wepic.presenter.inter.StartPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyeon on 2015-04-23.
 */
public class InviteActivity extends Activity implements InvitePresenter.View{
    static final String TAG = InviteActivity.class.getName();

    private InvitePresenter invitePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        this.invitePresenter = new InvitePresenterImpl(InviteActivity.this);
        this.invitePresenter.setView(this);

        Log.i(TAG, "초대 화면 시작");
        invitePresenter.initInviteActivity();
    }

    @Override
    public void showInviteList(InviteListAdapter adapter){
        ListView inviteList = (ListView)findViewById(R.id.inviteList);
        inviteList.setAdapter(adapter);
    }

}
