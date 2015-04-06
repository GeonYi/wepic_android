package com.momori.wepic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.momori.wepic.common.Const;
import com.momori.wepic.service.ImageCatchService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.invate) Button invateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        // TODO : 공유중이 아닐때만 초대 버튼 보여줌
        // 일단 무조건 공유중 아닌걸로!!!!
        if(false){
            invateButton.setVisibility(View.GONE);
        } else {
            invateButton.setVisibility(View.VISIBLE);
        }
    }

    //TODO : 친구초대
    /**
     * [PROCESS]
     *  - 친구의 email추출
     *  - 해당 email로 push메세지 전송
     * */
    @OnClick(R.id.invate)
    public void invateButtonOnclick() {
        //PROCESS
        String[] friendList = new String[Const.MAX_INVATE_CNT];
        friendList[0] = "test@gmail.com";
    }

    //TODO : 사진 공유 기능 구현부!
    @OnClick(R.id.share_button)
    public void regButtonOnclick() {
        Intent service= new Intent(getApplicationContext(), ImageCatchService.class);
        startService(service);
    }
}



