package com.momori.wepic.start;

import android.content.Intent;

/**
 * Created by Hyeon on 2015-04-18.
 */
public interface StartPresenter {

    public void initApplication();

    public boolean isReadyToLogin();

    public void wepicLogin();

}
