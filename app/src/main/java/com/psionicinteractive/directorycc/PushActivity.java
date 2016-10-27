package com.psionicinteractive.directorycc;

/**
 * Created by PSIONIC on 10/18/2016.
 */
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.psionicinteractive.directorycc.app.Config;
import com.psionicinteractive.directorycc.database.DatabaseHandler;
import com.psionicinteractive.directorycc.model.Push;
import com.psionicinteractive.directorycc.util.NotificationUtils;

import java.util.ArrayList;
import java.util.List;


public class PushActivity extends AppCompatActivity {

    ArrayList<Push> arrayList;
    ListView lv;
    ProgressDialog dialog;
    Context context;

    private static final String TAG = PushActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
//    private TextView txtRegId, txtMesage;
    private TextView txtRegId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_push);

        txtRegId = (TextView) findViewById(R.id.txt_reg_id);
//        txtMessage = (TextView) findViewById(R.id.txt_push_message);

        DatabaseHandler db=new DatabaseHandler(this);
        Log.d("Reading: ", "Reading all contacts..");

        arrayList=new ArrayList<>();
        List<Push> push = db.getAllPush();
        lv = (ListView) findViewById(R.id.pushListView);

        for (int c=push.size()-1;c>=0;c--) {
            String log = "Id: " + push.get(c).getID() + " ,Name: " + push.get(c).getTitle() + " ,Phone: " + push.get(c).getMessage();
            Log.v("database update: ", log);
            // Writing Contacts to log
            arrayList.add(new Push(push.get(c).getID(),push.get(c).getTitle(),push.get(c).getMessage()));
//            Log.v("database update: ", log);
        }
        CustomPushListAdapter adapter=new CustomPushListAdapter(getApplicationContext(),R.layout.list_push,arrayList);
        lv.setAdapter(adapter);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    Toast.makeText(context, ""+intent.getStringExtra("message"), Toast.LENGTH_SHORT).show();

//                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

//                    txtMessage.setText(message);
                }
            }
        };

//        displayFirebaseRegId();
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
//    private void displayFirebaseRegId() {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
//        String regId = pref.getString("regId", null);
//
//        Log.e(TAG, "Firebase reg id: " + regId);
//
//        if (!TextUtils.isEmpty(regId))
//            txtRegId.setText("Firebase Reg Id: " + regId);
//        else
//            txtRegId.setText("Firebase Reg Id is not received yet!");
//    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());


    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}