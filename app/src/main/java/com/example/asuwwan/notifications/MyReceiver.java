package com.example.asuwwan.notifications;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();

        if("clearNotification".equals(action)) {
            MainActivity.mNotificationManager.cancel(MainActivity.NOTIFY_ID);
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
