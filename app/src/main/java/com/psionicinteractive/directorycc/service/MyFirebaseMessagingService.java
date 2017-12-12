package com.psionicinteractive.directorycc.service;

/**
 * Created by PSIONIC on 10/18/2016.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.psionicinteractive.directorycc.PushActivity;
import com.psionicinteractive.directorycc.R;
import com.psionicinteractive.directorycc.database.DatabaseHandler;
import com.psionicinteractive.directorycc.model.Push;
import com.psionicinteractive.directorycc.util.NotificationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import static android.graphics.Color.rgb;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    DatabaseHandler db = new DatabaseHandler(this);
    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            Log.v("paisi","data paisi jj");
            Log.v("dataaa message", "Data Payload: " + remoteMessage.getData().toString());
//            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
//
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());

                String click_action="com.psionicinteractive.directorycc_TARGET_NOTIFICATION";
                Intent intent=new Intent(click_action);

                String title = json.getString("title");
                String message = json.getString("message");
                db.addPush(new Push("Notification", message));
                notificationUtils=new NotificationUtils(this);
                notificationUtils.showNotificationMessage(title,message,"00:00",intent);
            } catch (Exception e) {
                Log.v("error khaise", String.valueOf(e));
            }
//

        }
    }
}