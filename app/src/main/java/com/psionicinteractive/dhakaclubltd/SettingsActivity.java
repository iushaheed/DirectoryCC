package com.psionicinteractive.dhakaclubltd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by PSIONIC on 9/21/2016.
 */
public class SettingsActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
    }
    public void logout(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token","");
        editor.apply();
        Toast.makeText(SettingsActivity.this, "logged out", Toast.LENGTH_SHORT).show();

        Intent i= new Intent(SettingsActivity.this,HomeActivity.class);
        startActivity(i);
        finish();
    }

}
