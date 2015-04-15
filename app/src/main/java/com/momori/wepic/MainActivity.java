package com.momori.wepic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.momori.wepic.common.Const;
import com.momori.wepic.common.SFValue;
import com.momori.wepic.service.ImageCatchService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.main_button_invite     ) Button inviteButton;
    @InjectView(R.id.main_button_shareStart ) Button shareButton;

    @InjectView(R.id.main_text_shareAlbumName) TextView shareAlbumName;

    SFValue pref = new SFValue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        if(pref.getValue(SFValue.PREF_IS_SHARE, false) == true){
            inviteButton.setVisibility(View.GONE);
        }else{
            inviteButton.setVisibility(View.VISIBLE);
        }

        shareAlbumName.setText(pref.getValue(SFValue.PREF_SHARE_ALBUM_NAME, ""));
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
}



