package com.momori.wepic.common.callback;

import android.util.Log;

/**
 * Created by Hyeon on 2015-04-19.
 */
public interface AsyncCallback<T> {
    public void onResult(T result);
    public void exceptionOccured(Exception e);
    public void cancelled();

    public static abstract class AsyncResult<T> implements AsyncCallback<T> {

        @Override
        public void exceptionOccured(Exception e) {
            Log.e("Exception",  e.getMessage());
        }

        @Override
        public void cancelled() {
            Log.i("Cancelled",  "Work Cancelled");
        }

    }
}
