package com.momori.wepic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.momori.wepic.R;
import com.momori.wepic.common.Const;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.service.ImageCatchService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.main_button_invite     ) Button inviteButton;
    @InjectView(R.id.main_button_shareStart ) Button shareButton;
    @InjectView(R.id.main_text_shareAlbumName) TextView shareAlbumName;
    @InjectView(R.id.main_layout_shareAlbum ) LinearLayout shareAlbumLayout;

    String user_id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        Intent intent = getIntent();
        user_id = intent.getStringExtra(UserModel.USER_ID);

        if(SFValue.getInstance().getValue(SFValue.PREF_IS_SHARE, false) == true){
            inviteButton.setVisibility(View.GONE);
            shareAlbumLayout.setVisibility(View.VISIBLE);
        }else{
            inviteButton.setVisibility(View.VISIBLE);
            shareAlbumLayout.setVisibility(View.GONE);
        }

        shareAlbumName.setText(SFValue.getInstance().getValue(SFValue.PREF_SHARE_ALBUM_NAME, ""));
    }

    //TODO : 친구초대
    /**
     * [PROCESS]
     *  - 친구의 email추출
     *  - 해당 email로 push메세지 전송
     * */
    @OnClick(R.id.main_button_invite)
    public void invateButtonOnclick() {
        //PROCESS
        String[] friendList = new String[Const.MAX_INVATE_CNT];
        friendList[0] = "test@gmail.com";
    }

    @OnClick(R.id.main_button_shareStart)
    public void regButtonOnclick() {

        //todo : 사진 공유중이라면 text 변경 필요
        if(shareButton.getText().toString().equals("share start")){
            stopService(new Intent(getApplicationContext(), ImageCatchService.class));
            shareButton.setText("share stop");
            startService(new Intent(getApplicationContext(), ImageCatchService.class));
        }
        else if(shareButton.getText().toString().equals("share stop")){
            shareButton.setText("share start");
            stopService(new Intent(getApplicationContext(), ImageCatchService.class));
        }
    }

    @OnClick(R.id.main_button_showImage)
    public void showImageButtonOnclick() {
        Intent intentSubActivity = new Intent(MainActivity.this, AlbumViewActivity.class);
        startActivity(intentSubActivity);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(keyCode){
            // 뒤로 가기 버튼을 누르면 홈 키를 누른거 처럼 내려간다
            case KeyEvent.KEYCODE_BACK:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                        | Intent.FLAG_ACTIVITY_FORWARD_RESULT
                        | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivity(intent);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}



