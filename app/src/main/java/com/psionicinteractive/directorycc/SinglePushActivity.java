package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by PSIONIC on 10/26/2016.
 */
public class SinglePushActivity extends Activity {
    TextView textTitle;
    TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_push);

        textTitle= (TextView) findViewById(R.id.title);
        textMessage= (TextView) findViewById(R.id.message);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String message=intent.getStringExtra("message");

        textTitle.setText(title);
        textMessage.setText(message);


    }
}
