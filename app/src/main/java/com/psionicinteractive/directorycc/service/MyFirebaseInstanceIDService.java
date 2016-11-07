package com.psionicinteractive.directorycc.service;

/**
 * Created by PSIONIC on 10/18/2016.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.psionicinteractive.directorycc.BackgroundTask;
import com.psionicinteractive.directorycc.app.Config;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String REG_TOKEN = MyFirebaseInstanceIDService.class.getSimpleName();



    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
//
//        Toast.makeText(this, "Refreshing token", Toast.LENGTH_SHORT).show();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
        Log.v("eitanotuntoken",refreshedToken);

    }


    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
//        Log.e(REG_TOKEN, "sendRegistrationToServer: " + token);
        Log.e("THEETOKEN", "sendRegistrationToServer: " + token);
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("fcm_token_send",token,"test");
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }

}