package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by PSIONIC on 9/21/2016.
 */
public class SettingsActivity extends Activity {

    Button mLogout;
    Button m_password_change_button;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        Typeface font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        mLogout= (Button) findViewById(R.id.logout_button);
        m_password_change_button= (Button) findViewById(R.id.password_change_button);

        mLogout.setTypeface(font_lato);
        m_password_change_button.setTypeface(font_lato);


    }
    public void logout(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token","");
        editor.apply();
        Toast.makeText(SettingsActivity.this, "logged out", Toast.LENGTH_SHORT).show();

        Intent i= new Intent(SettingsActivity.this,HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    public void password_change_method(View view) {
        Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show();
    }
}
