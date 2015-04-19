package com.momori.wepic.start;

import android.content.Intent;

/**
 * Created by Hyeon on 2015-04-18.
 */
public interface StartPresenter {

    void login();

    void checkLoginAndStartMainActivity();

    void onActivityResult(int requestCode, int resultCode, Intent data);

}
