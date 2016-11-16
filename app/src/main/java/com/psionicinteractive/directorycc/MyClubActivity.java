package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by imam on 8/17/2016.
 */
public class MyClubActivity extends Activity {
    TextView mTextView_title;
    TextView mTextView_main_text_body;
    Button mButton_contact_us;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_myclub);

        Typeface font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        mTextView_title= (TextView) findViewById(R.id.myclub_title_textview);
        mTextView_main_text_body= (TextView) findViewById(R.id.textView_main_body);
        mButton_contact_us= (Button)findViewById(R.id.contact_button);

        mTextView_title.setTypeface(font_lato);
        mTextView_main_text_body.setTypeface(font_lato);
        mButton_contact_us.setTypeface(font_lato);




    }


    public void callMyClub(View view) {
        String phoneNumber="+8801921334500";
        Intent call =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        Intent chooser= Intent.createChooser(call,"Choose Application");
        startActivity(chooser);
    }
}
