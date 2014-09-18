package com.example.asuwwan.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
    static final int NOTIFY_ID=1337;
    static NotificationManager mNotificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.widget);


        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);
        // The stack builder object will contain an artificial back stack for
        // the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
         mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent resultPendingIntentShow = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.button, resultPendingIntentShow);
        Intent intent=new Intent(this,MyReceiver.class);
        intent.setAction("clearNotification");
        PendingIntent resultPendingIntentClose = PendingIntent.getBroadcast(this,NOTIFY_ID,intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.close, resultPendingIntentClose);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_launcher).setContent(
                remoteViews).setAutoCancel(true);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(NOTIFY_ID, mBuilder.build());

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
