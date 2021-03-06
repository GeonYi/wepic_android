package com.momori.wepic.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.momori.wepic.R;
import com.momori.wepic.activity.StartActivity;
import com.momori.wepic.external.gcm.GcmBroadcastReceiver;

/**
 * Created by Hyeon on 2015-04-08.
 */
public class GcmIntentService extends IntentService{
    static final String TAG = GcmIntentService.class.getName();

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentService(){
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        String album_id = intent.getStringExtra("album_id");
        String msg = intent.getStringExtra("msg");

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)){
            //sendNotification("Send error : " + extras.toString());
        }else if(GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
             //sendNotification("Deleted messages on server : " + extras.toString());
         }else if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){
             sendNotification(msg);
             GcmBroadcastReceiver.completeWakefulIntent(intent);
         }
    }

    private void sendNotification(String msg){
        mNotificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, StartActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.abc_ic_menu_share_mtrl_alpha)
                .setContentTitle("Wepic 초대")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

        Vibrator v = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }
}
