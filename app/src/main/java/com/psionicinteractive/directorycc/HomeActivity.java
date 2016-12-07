package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity {
    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        ET_NAME = (EditText)findViewById(R.id.user_name);
        ET_PASS = (EditText)findViewById(R.id.user_pass);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        String token = preferences.getString("token","");
        if(token.equals(""))
        {
//            Toast.makeText(HomeActivity.this, "Log in or Register", Toast.LENGTH_LONG).show();
        }
        else
        {

            Intent i = new Intent(HomeActivity.this, LiquidActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void userReg(View view)
    {
//        startActivity(new Intent(this,Register.class));
    }

    public void userLogin(View view)
    {
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,login_name,login_pass);
    }
    public void register(View view)
    {
        Toast.makeText(HomeActivity.this, "Registration", Toast.LENGTH_LONG).show();
    }

//    public void gotomenu(View view)
//    {
//        Intent i = new Intent(HomeActivity.this, MenuActivity.class);
//        startActivity(i);
//
//    }
    public void clearToken(View view)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token","");
        editor.apply();
        Toast.makeText(HomeActivity.this, "Session cleaned", Toast.LENGTH_LONG).show();

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        HomeActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
