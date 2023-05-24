package com.example.studentnotificationapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.example.studentnotificationapp.MainActivity;
import com.example.studentnotificationapp.EventDetailActivity;

public class MyFirebaseNotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        System.out.println("From: " + remoteMessage.getFrom());
        Log.d("MainActivity", "Am I here?");

        // Check if message contains a notification payload.
        if (remoteMessage.getData() != null) {
            System.out.println("Message Notification Body: ");
        }
        else {
            Log.d("MainActivity", "Hellllllau");
        }
        Log.d("MainActivity", "Message ok");
;
        sendNotification(remoteMessage);
    }

//    private void sendNotification(String from, String body) {
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//
//            @Override
//            public void run() {
//                Toast.makeText(MyFirebaseNotificationService.this.getApplicationContext(), from + " -> " + body,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void sendNotification(RemoteMessage messageBody) {

        Log.d("MainActivity", "OK?");
        String title = messageBody.getData().get("title");
        String description = messageBody.getData().get("body");
//        Log.d("MainActivity", "Got data");
//        Log.d("MainActivity", title);
//        Log.d("MainActivity", description);
//        Intent intent = new Intent(this, EventDetailActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("eventTitle", title);
//        intent.putExtra("eventDescription", description);
//        intent.putExtra("eventCategory", "Tech");
        Log.d("MainActivity", "I am here!!!");
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Log.d("MainActivity", "still ok");
        String NOTIFICATION_CHANNEL_ID="EDMTDev";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("MainActivity", "Ok2");
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My notification",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("My channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        Log.d("MainActivity", "Ok3");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setTicker("Hearty365")
                .setContentTitle(title)
                .setContentText(description)
//                .setContentIntent(pendingIntent)
                .setContentInfo("info");

//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.ic_stat_notification)
//                        .setContentTitle(messageTile)
//                        .setContentText(messageBody)
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);



        Log.d("MainActivity", "Build ok");
        //getSystemService(NotificationManager.class).createNotificationChannel(channel);
        //NotificationManagerCompat.from(this).notify(1, notificationBuilder.build());
        notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());
        Log.d("MainActivity", "Notify ok");
    }
}
