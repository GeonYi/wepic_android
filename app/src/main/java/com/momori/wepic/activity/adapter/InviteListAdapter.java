package com.momori.wepic.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.momori.wepic.R;
import com.momori.wepic.WepicApplication;
import com.momori.wepic.model.UserModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyeon on 2015-04-26.
 */
public class InviteListAdapter extends BaseAdapter {

    static final int PICTURE_WIDTH = 100;
    static final int PICTURE_HEIGHT = 100;

    WepicApplication context;
    LayoutInflater inflater;
    List<UserModel> list;
    int layout;

    public InviteListAdapter(WepicApplication context, int layout, List<UserModel> list){
        this. context= context;
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

    public String getExternal_id(int position){
        return ((UserModel)this.getItem(position)).getExternal_id();
    }

    @Override
    // 호출 하지 않는다.
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        final View view;
        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater =  LayoutInflater.from(context);
            view = inflater.inflate(this.layout, null);

            viewHolder = new ViewHolder();
            viewHolder.nameView = (TextView)view.findViewById(R.id.inviteName);
            viewHolder.pictureView = (ImageView)view.findViewById(R.id.invitePicture);
            viewHolder.checkBox = (CheckBox)view.findViewById(R.id.inviteCheckBox);
            viewHolder.checkBox.setClickable(false);
            viewHolder.checkBox.setFocusable(false);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        // TODO 스크롤 플립 추가 http://blog.naver.com/yangace00/60136968668
        UserModel user = this.list.get(position);
        setData(user, position ,viewHolder);

        return view;
    }

    private void setData(UserModel user, int position , ViewHolder viewHolder){
        viewHolder.nameView.setText(user.getUser_name());

        String pictureUrl = this.context.getFbComponent().getPictureUrl(user.getExternal_id(), PICTURE_WIDTH, PICTURE_HEIGHT);
        ImageLoader.getInstance().displayImage(pictureUrl, viewHolder.pictureView);
    }

    public boolean toggleCheck(View view){
        CheckBox checkBox = ((ViewHolder)view.getTag()).checkBox;
        checkBox.setChecked(!checkBox.isChecked());
        return checkBox.isChecked();
    }

    static class ViewHolder{
        TextView nameView;
        ImageView pictureView;
        CheckBox checkBox;
        boolean loaded;
    }
}
