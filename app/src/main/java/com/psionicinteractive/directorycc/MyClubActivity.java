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
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by imam on 8/17/2016.
 */
public class MyClubActivity extends Activity {

    //OLD STUFFS
//    TextView mTextView_title;
//    TextView mTextView_main_text_body;
//    Button mButton_contact_us;
//    Button mButton_ec;

    TextView tx;
    TextView m_directory_text_layout;
    TextView m_contact_text_layout;
    TextView m_message_text_layout;
    TextView m_anniversary_text_layout;
    TextView m_checkin_text_layout;
    TextView m_myclub_text_layout;
    TextView m_search_text_layout;
    TextView m_myprofile_text_layout;
    TextView m_settings_text_layout;
    ImageButton m_ib;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_myclub);

        Typeface lato_font = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");


        //OLD
//        mTextView_title= (TextView) findViewById(R.id.myclub_title_textview);
//        mTextView_main_text_body= (TextView) findViewById(R.id.textView_main_body);
//        mButton_contact_us= (Button)findViewById(R.id.contact_button_a);
//        mButton_ec= (Button)findViewById(R.id.contact_button_b);
//
//        mTextView_title.setTypeface(font_lato);
//        mTextView_main_text_body.setTypeface(font_lato);
//        mButton_contact_us.setTypeface(font_lato);
//        mButton_ec.setTypeface(font_lato);

        //NEW

        tx = (TextView)findViewById(R.id.headerText);
        m_directory_text_layout= (TextView)findViewById(R.id.directory_text_layout);
        m_contact_text_layout= (TextView)findViewById(R.id.contact_text_layout);
//        m_message_text_layout= (TextView)findViewById(R.id.message_text_layout);
        m_anniversary_text_layout= (TextView)findViewById(R.id.anniversary_text_layout);
        m_checkin_text_layout= (TextView)findViewById(R.id.checkin_text_layout);
//        m_myclub_text_layout= (TextView)findViewById(R.id.myclub_text_layout);
        m_search_text_layout= (TextView)findViewById(R.id.search_text_layout);
        m_myprofile_text_layout= (TextView)findViewById(R.id.myprofile_text_layout);
//        m_settings_text_layout= (TextView)findViewById(R.id.settings_text_layout);
        m_ib= (ImageButton) findViewById(R.id.add_banner);


        tx.setTypeface(lato_font);
        m_directory_text_layout.setTypeface(lato_font);
        m_contact_text_layout.setTypeface(lato_font);
//        m_message_text_layout.setTypeface(lato_font);
        m_anniversary_text_layout.setTypeface(lato_font);
        m_checkin_text_layout.setTypeface(lato_font);
//        m_myclub_text_layout.setTypeface(lato_font);
        m_search_text_layout.setTypeface(lato_font);
        m_myprofile_text_layout.setTypeface(lato_font);
//        m_settings_text_layout.setTypeface(lato_font);

    }


    public void callMyClub(View view) {
        String phoneNumber="+8801921334500";
        Intent call =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        Intent chooser= Intent.createChooser(call,"Choose Application");
        startActivity(chooser);
    }

    public void gotoec(View view) {
        Intent i = new Intent(MyClubActivity.this, EcMembersActivity.class);
        startActivity(i);

    }

    public void gotofounders_myclub(View view) {
        Intent i = new Intent(MyClubActivity.this, FoundersActivity.class);
        startActivity(i);
    }

    public void gotogallery_myclub(View view) {
    }

    public void gotoevents_myclub(View view) {
    }

    public void gotonotice_myclub(View view) {
    }

    public void gotorules_myclub(View view) {
    }
}
