package com.psionicinteractive.directorycc.service;

/**
 * Created by PSIONIC on 10/18/2016.
 */
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.psionicinteractive.directorycc.PushActivity;
import com.psionicinteractive.directorycc.database.DatabaseHandler;
import com.psionicinteractive.directorycc.model.Push;
import com.psionicinteractive.directorycc.util.NotificationUtils;

import org.json.JSONException;
import org.json.JSONObject;


public class MyFirebaseMessagingService_backup extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService_backup.class.getSimpleName();
    DatabaseHandler db = new DatabaseHandler(this);
    private NotificationUtils notificationUtils;

//    public void onMessageReceived(RemoteMessage remoteMessage)
//    {
//        String title=remoteMessage.getNotification().getTitle();
//        String message=remoteMessage.getNotification().getBody();
////        String dAction=remoteMessage.getData().get("click_action");
//        Log.v("messssage",message);
////        Log.v("dAction",dAction);
//        //from server
//        String click_action="com.psionicinteractive.directorycc_TARGET_NOTIFICATION";
//        Intent intent=new Intent(click_action);
//        db.addPush(new Push("Notification", remoteMessage.getNotification().getBody()));
////        Intent intent =new Intent(this,PushActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//
//        NotificationCompat.Builder notificationBuilder =new NotificationCompat.Builder(this);
//        notificationBuilder.setContentTitle(title);
//        notificationBuilder.setContentText(message);
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
////        notificationBuilder.setLights(Color.BLUE,1,1);
////        notificationBuilder.setSound(Uri.parse("raw/notification.mp3"));
//
//        notificationBuilder.setContentIntent(pendingIntent);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0,notificationBuilder.build());
//    }


        @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//
//            String title=remoteMessage.getNotification().getTitle();
//            String message=remoteMessage.getNotification().getBody();
////        String dAction=remoteMessage.getData().get("click_action");
//            Log.v("messssage",message);
////        Log.v("dAction",dAction);
//            //from server
//            String click_action="com.psionicinteractive.directorycc_TARGET_NOTIFICATION";
//            Intent intent=new Intent(click_action);
//            db.addPush(new Push("Notification", remoteMessage.getNotification().getBody()));
////        Intent intent =new Intent(this,PushActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//
//            NotificationCompat.Builder notificationBuilder =new NotificationCompat.Builder(this);
//            notificationBuilder.setContentTitle(title);
//            notificationBuilder.setContentText(message);
//            notificationBuilder.setAutoCancel(true);
//            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
////        notificationBuilder.setLights(Color.BLUE,1,1);
////        notificationBuilder.setSound(Uri.parse("raw/notification.mp3"));
//
//            notificationBuilder.setContentIntent(pendingIntent);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(0,notificationBuilder.build());
//
//        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            Log.v("paisi","data paisi jj");
            Log.v("dataaa message", "Data Payload: " + remoteMessage.getData().toString());
//            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
//
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
////                handleDataMessage(json);
//                JSONObject data = json.getJSONObject("Data Payload");

                String click_action="com.psionicinteractive.directorycc_TARGET_NOTIFICATION";
                Intent intent=new Intent(click_action);

                String title = json.getString("title");
                String message = json.getString("message");
                db.addPush(new Push("Notification", message));
                notificationUtils=new NotificationUtils(this);
                notificationUtils.showNotificationMessage(title,message,"12:00",intent);

//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//
//                NotificationCompat.Builder notificationBuilder =new NotificationCompat.Builder(this);
//                notificationBuilder.setContentTitle(title);
//                notificationBuilder.setContentText(message);
//                notificationBuilder.setAutoCancel(true);
//                notificationBuilder.setSmallIcon(R.drawable.pushicon);
//                notificationBuilder.setLights(Color.BLACK,1,1);
//                notificationBuilder.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
//                        + "://" + this.getPackageName() + "/raw/push_sound"));
//                notificationBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
//                notificationBuilder.setContentIntent(pendingIntent);
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(0,notificationBuilder.build());


//            boolean isBackground = data.getBoolean("is_background");
            } catch (Exception e) {
                Log.v("error khaise", String.valueOf(e));
            }
//

        }
//        Log.v("Inserting :",remoteMessage.getMessageType()+" "+remoteMessage.getNotification().getBody());
//        db.addPush(new Push(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody()));
    }


        private void handleDataMessage(JSONObject json) {

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");


                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), PushActivity.class);
                resultIntent.putExtra("message", message);


                // check for image attachment
//                if (TextUtils.isEmpty(imageUrl)) {
//                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
//                } else {
//                    // image is present, show notification with image
//                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
//                }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }





    /////////////////////////////////

//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.e(TAG, "From: " + remoteMessage.getFrom());
//
//        if (remoteMessage == null)
//            return;
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
//            db.addPush(new Push("Notification", remoteMessage.getNotification().getBody()));
//            handleNotification(remoteMessage.getNotification().getBody());
//
//        }
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
//
//            try {
//                JSONObject json = new JSONObject(remoteMessage.getData().toString());
//                handleDataMessage(json);
//            } catch (Exception e) {
//                Log.e(TAG, "Exception: " + e.getMessage());
//            }
//        }
////        Log.v("Inserting :",remoteMessage.getMessageType()+" "+remoteMessage.getNotification().getBody());
////        db.addPush(new Push(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody()));
//    }
//
//    private void handleNotification(String message) {
//        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
//            // app is in foreground, broadcast the push message
//            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
//            pushNotification.putExtra("message", message);
//            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
//
//            // play notification sound
//            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
//            notificationUtils.playNotificationSound();
//        }else{
//
//            // If the app is in background, firebase itself handles the notification
//        }
//    }
//
//    private void handleDataMessage(JSONObject json) {
//        Log.e(TAG, "push json: " + json.toString());
//
//        try {
//            JSONObject data = json.getJSONObject("data");
//
//            String title = data.getString("title");
//            String message = data.getString("message");
//            boolean isBackground = data.getBoolean("is_background");
//            String imageUrl = data.getString("image");
//            String timestamp = data.getString("timestamp");
//            JSONObject payload = data.getJSONObject("payload");
//
//            Log.e(TAG, "title: " + title);
//            Log.e(TAG, "message: " + message);
//            Log.e(TAG, "isBackground: " + isBackground);
//            Log.e(TAG, "payload: " + payload.toString());
//            Log.e(TAG, "imageUrl: " + imageUrl);
//            Log.e(TAG, "timestamp: " + timestamp);
//
//
//            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
//                // app is in foreground, broadcast the push message
//                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
//                pushNotification.putExtra("message", message);
//                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
//
//                // play notification sound
//                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
//                notificationUtils.playNotificationSound();
//            } else {
//                // app is in background, show the notification in notification tray
//                Intent resultIntent = new Intent(getApplicationContext(), PushActivity.class);
//                resultIntent.putExtra("message", message);
//
//                // check for image attachment
//                if (TextUtils.isEmpty(imageUrl)) {
//                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
//                } else {
//                    // image is present, show notification with image
//                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
//                }
//            }
//        } catch (JSONException e) {
//            Log.e(TAG, "Json Exception: " + e.getMessage());
//        } catch (Exception e) {
//            Log.e(TAG, "Exception: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Showing notification with text only
//     */
//    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
//        notificationUtils = new NotificationUtils(context);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
//    }
//
//    /**
//     * Showing notification with text and image
//     */
//    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
//        notificationUtils = new NotificationUtils(context);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
//    }
//
//    public void onMessageReceived(RemoteMessage remoteMessage){
//        Log.v("msg paisi", "1");
//        Intent intent =new Intent(this,PushActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//        NotificationCompat.Builder notificationBuilder =new NotificationCompat.Builder(this);
//        notificationBuilder.setContentTitle("FCM NOTIFICATION");
//        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        notificationBuilder.setContentIntent(pendingIntent);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0,notificationBuilder.build());
//    }

}