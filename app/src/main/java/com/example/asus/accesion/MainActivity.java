package com.example.asus.accesion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NotificationManager manager;
    private Button mButton1, mButton2, mButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mButton1 = (Button) findViewById(R.id.but1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.but2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.but3);
        mButton3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but1:
                Notification.Builder builder = new Notification.Builder(this);
                builder.setTicker("你有一个新消息");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("myno");
                builder.setContentText("新消息");
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 1,
                        new Intent(MainActivity.this, MainActivity.class), 0);
                builder.setContentIntent(pendingIntent);
                manager.notify(1, builder.build());
                //build()报错改最低版本project——builder——minsdk15改为16
                break;
            case R.id.but2:
                Notification notifacation = new Notification();
                notifacation.icon = R.mipmap.ic_launcher;
                notifacation.tickerText = "自定义的消息";
                notifacation.flags = Notification.FLAG_AUTO_CANCEL;//设置消除
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.my_notification);
                remoteViews.setTextViewText(R.id.text_view,"短消息");
                long date = System.currentTimeMillis();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
                String time = simpleDateFormat.format(date);
                remoteViews.setTextViewText(R.id.time_view, time);
                PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 1,
                        new Intent(MainActivity.this, MainActivity.class), 0);
                notifacation.contentView=remoteViews;
                notifacation.contentIntent = pendingIntent1;
                manager.notify(2, notifacation);
                break;
            case R.id.but3:
                manager.cancel(1);
                break;

        }
    }


}
