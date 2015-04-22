package com.momori.wepic.presenter.inter;

import android.content.Intent;

/**
 * Created by Hyeon on 2015-04-18.
 */
public interface StartPresenter {

    public boolean isReadyToLogin();

    public void startRegistOrLogin();

    public void onActivityResult(int requestCode, int resultCode, Intent data);
}
