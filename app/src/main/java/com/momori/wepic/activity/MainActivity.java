package com.momori.wepic.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.momori.wepic.R;
import com.momori.wepic.activity.fragment.AlbumCardFragment;
import com.momori.wepic.model.AlbumModel;
import com.momori.wepic.presenter.impl.MainPresenterImpl;
import com.momori.wepic.presenter.inter.MainPresenter;
import com.rey.material.app.ToolbarManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends ActionBarActivity implements MainPresenter.View, ToolbarManager.OnToolbarGroupChangedListener {
    static final String TAG = MainActivity.class.getName();

//    @InjectView(R.id.main_button_invite     ) Button inviteButton;
//    @InjectView(R.id.main_button_shareStart ) Button shareButton;
//    @InjectView(R.id.main_text_shareAlbumName) TextView shareAlbumName;
//    @InjectView(R.id.main_layout_shareAlbum ) LinearLayout shareAlbumLayout;

//    private DrawerLayout dl_navigator;

    private Toolbar mToolbar;
    private ToolbarManager mToolbarManager;

    public MainPresenter presenter;

    private AlbumCardFragment albumCardFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainPresenterImpl(MainActivity.this);
        this.presenter.setView(this);

        this.albumCardFragment = new AlbumCardFragment();
        this.presenter.showAlbumCardFragment(R.id.fragment_album_list, this.albumCardFragment);



        mToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        mToolbarManager = new ToolbarManager(this, mToolbar, 0, R.style.ToolbarRippleStyle, R.anim.abc_fade_in, R.anim.abc_fade_out);





/*
        LinearLayout layout_share_album = (LinearLayout)findViewById(R.id.layout_share_album);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        //noinspection ResourceType
        ll.setId(12345);



        getSupportFragmentManager().beginTransaction().add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .add(ll.getId(),  new ShareAlbumFragment())
                                    .commit();

        layout_share_album.addView(ll);
*/

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
                Intent intent = new Intent(MainActivity.this, InviteActivity.class);
                startActivity(intent);
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



