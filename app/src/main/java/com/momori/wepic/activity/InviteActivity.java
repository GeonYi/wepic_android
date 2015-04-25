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
public class InviteActivity extends Activity{

    private WepicApplication context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        this.context = (WepicApplication)getApplicationContext();
        showInviteList();
    }

    public void showInviteList(){
        new AsyncTask<Object, Void, List<UserModel>>() {

            @Override
            protected List<UserModel> doInBackground(Object[] params) {
                return  context.getFbComponent().getFbFriendsList();
            }

            @Override
            protected void onPostExecute(List<UserModel> friendList){
                UserModelAdapter inviteListAdapter = new UserModelAdapter(context, R.layout.activity_invite_list_item, friendList);

                ListView inviteList = (ListView)findViewById(R.id.inviteList);
                inviteList.setAdapter(inviteListAdapter);
            }
        }.execute();

    }

    class UserModelAdapter extends BaseAdapter{

        WepicApplication context;
        LayoutInflater inflater;
        List<UserModel> list;
        int layout;

        public UserModelAdapter(WepicApplication context, int layout, List<UserModel> list){
            this. context= context;
            //this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.inflater =  LayoutInflater.from(context);
            this.list = list;
            this.layout = layout;
        }

        @Override
        public int getCount(){
            return this.list.size();
        }

        @Override
        public Object getItem(int position){
            return this.list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = this.inflater.inflate(this.layout, null);
                UserModel friend = this.list.get(position);
                ImageView pictureView = (ImageView)convertView.findViewById(R.id.invitePicture);
                ImageLoader.getInstance().displayImage(this.context.getFbComponent().getPictureUrl(friend.getExternal_id(), "large"), pictureView);

                TextView nameView = (TextView)convertView.findViewById(R.id.inviteName);
                nameView.setText(friend.getUser_name());
            }

            return convertView;
        }
    }
}
