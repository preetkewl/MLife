package com.mlife.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.activities.Extras.Activity_Feedback;
import com.mlife.activities.ActivityLogin;
import com.mlife.activities.ActivityHome;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;

/**
 * Created by milagro on 9/19/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public MahindraClappPreference mahindraClappPreference;

    String sChannelId = "MLife";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage.getData().get("body").toString(), remoteMessage.getData().get("showFeedback"), remoteMessage.getData().get("title"), remoteMessage.getData().get("notificationType"));
        }
    }

    NotificationManager notificationManager;
    NotificationCompat.Builder notificationBuilder;

    private void sendNotification(String messageBody, String ratingDialog, String title, String type) {


        mahindraClappPreference = new MahindraClappPreference(getApplicationContext());

        if (mahindraClappPreference.getpEmail().equals("")) {
            Intent intent = new Intent(this, ActivityLoginWithOTP.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder = new NotificationCompat.Builder(this,sChannelId).setSmallIcon(R.drawable.notification).setContentTitle("M-Life").setContentText(messageBody).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
        } else {
            if (ratingDialog.equals("1")) {
                Intent intent = new Intent(this, Activity_Feedback.class);
                intent.putExtra("title", title);
                intent.putExtra("type", type);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationBuilder = new NotificationCompat.Builder(this,sChannelId).setSmallIcon(R.drawable.notification).setContentTitle("M-Life").setContentText(messageBody).setAutoCancel(true).setContentIntent(pendingIntent);//.setSound(defaultSoundUri)
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificationBuilder.build());
            } else {
                Intent intent = new Intent(this, ActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationBuilder = new NotificationCompat.Builder(this,sChannelId).setSmallIcon(R.drawable.notification).setContentTitle("M-Life").setContentText(messageBody).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }

        }

        if(notificationManager != null && notificationBuilder != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                NotificationChannel channel = new NotificationChannel(sChannelId,
                        "MLife",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
                notificationManager.notify(0, notificationBuilder.build());

        }
    }
}