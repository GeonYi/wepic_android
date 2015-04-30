package com.momori.wepic.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momori.wepic.R;
import com.momori.wepic.WepicApplication;
import com.momori.wepic.model.UserModel;

import java.util.List;

/**
 * Created by Hyeon on 2015-04-26.
 */
public class InviteListAdapter extends BaseAdapter {

    static final int PICTURE_WIDTH = 100;
    static final int PICTURE_HEIGHT = 100;

    Activity activity;
    LayoutInflater inflater;
    List<UserModel> list;
    int layout;

    public InviteListAdapter(Activity activity, int layout, List<UserModel> list){
        this.activity = activity;
        this.inflater =  LayoutInflater.from(activity.getApplicationContext());
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

        View view;
        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater =  LayoutInflater.from(activity.getApplicationContext());
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

        UserModel user = this.list.get(position);
        setData(user, viewHolder);

        return view;
    }

    private void setData(UserModel user,  ViewHolder viewHolder){
        viewHolder.nameView.setText(user.getUser_name());
        String pictureUrl = WepicApplication.getInstance().getFbComponent()
                                    .getPictureUrl(user.getExternal_id(), PICTURE_WIDTH, PICTURE_HEIGHT);

        Glide.with(this.activity)
                .load(pictureUrl)
                .placeholder(R.drawable.com_facebook_profile_picture_blank_portrait)
                .centerCrop()
                .crossFade()
                .transform(WepicApplication.getInstance().getCircleTransform())
                .into(viewHolder.pictureView);

        viewHolder.loaded = true;
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
        boolean loaded =false;
    }
}
