package com.momori.wepic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.momori.wepic.R;
import com.momori.wepic.activity.fragment.FramentShareAlbum;
import com.momori.wepic.common.SFValue;
import com.rey.material.app.ToolbarManager;

public class MainActivity extends ActionBarActivity implements ToolbarManager.OnToolbarGroupChangedListener {

//    @InjectView(R.id.main_button_invite     ) Button inviteButton;
//    @InjectView(R.id.main_button_shareStart ) Button shareButton;
//    @InjectView(R.id.main_text_shareAlbumName) TextView shareAlbumName;
//    @InjectView(R.id.main_layout_shareAlbum ) LinearLayout shareAlbumLayout;

//    private DrawerLayout dl_navigator;

    private Toolbar mToolbar;
    private ToolbarManager mToolbarManager;

    SFValue pref = new SFValue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        dl_navigator = (DrawerLayout)findViewById(R.id.main_dl);


//        ButterKnife.inject(this);
//
//        if(pref.getValue(SFValue.PREF_IS_SHARE, false) == true){
//            inviteButton.setVisibility(View.GONE);
//            shareAlbumLayout.setVisibility(View.VISIBLE);
//        }else{
//            inviteButton.setVisibility(View.VISIBLE);
//            shareAlbumLayout.setVisibility(View.GONE);
//        }
//
//        shareAlbumName.setText(pref.getValue(SFValue.PREF_SHARE_ALBUM_NAME, ""));

        //card library
        //Add Header to card

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        mToolbarManager = new ToolbarManager(this, mToolbar, 0, R.style.ToolbarRippleStyle, R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    public static class FragmentShareAlbum extends FramentShareAlbum {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.demo_fragment_native_misc_card, null);
        }

        public void onReasum(){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbarManager.createMenu(R.menu.manu_main);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mToolbarManager.onPrepareMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tb_add:
                Log.i(this.getClass().toString(), "TODO : make album");
//                mToolbarManager.setCurrentGroup(R.id.tb_group_contextual);
                break;
            case R.id.tb_done:
            case R.id.tb_done_all:
                mToolbarManager.setCurrentGroup(0);
                break;
            case R.id.tb_refresh:
                Log.i(this.getClass().toString(), "TODO : get album list");
                break;
        }
        return true;
    }

    @Override
    public void onToolbarGroupChanged(int oldGroupId, int groupId) {
        mToolbarManager.notifyNavigationStateChanged();
    }

//    //TODO : 친구초대
//    /**
//     * [PROCESS]
//     *  - 친구의 email추출
//     *  - 해당 email로 push메세지 전송
//     * */
//    @OnClick(R.id.main_button_invite)
//    public void invateButtonOnclick() {
//        //PROCESS
//        String[] friendList = new String[Const.MAX_INVATE_CNT];
//        friendList[0] = "test@gmail.com";
//    }
//
//    @OnClick(R.id.main_button_shareStart)
//    public void regButtonOnclick() {
//
//        //todo : 사진 공유중이라면 text 변경 필요
//        if(shareButton.getText().toString().equals("share start")){
//            stopService(new Intent(getApplicationContext(), ImageCatchService.class));
//            shareButton.setText("share stop");
//            startService(new Intent(getApplicationContext(), ImageCatchService.class));
//        }
//        else if(shareButton.getText().toString().equals("share stop")){
//            shareButton.setText("share start");
//            stopService(new Intent(getApplicationContext(), ImageCatchService.class));
//        }
//    }
//
//    @OnClick(R.id.main_button_showImage)
//    public void showImageButtonOnclick() {
//        Intent intentSubActivity = new Intent(MainActivity.this, AlbumViewActivity.class);
//        startActivity(intentSubActivity);
//    }
//

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



