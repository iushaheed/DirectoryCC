package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by imam on 8/16/2016.
 */
public class MenuActivity extends Activity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);
        Typeface lato_font = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        tx = (TextView)findViewById(R.id.headerText);
        m_directory_text_layout= (TextView)findViewById(R.id.directory_text_layout);
        m_contact_text_layout= (TextView)findViewById(R.id.contact_text_layout);
        m_message_text_layout= (TextView)findViewById(R.id.message_text_layout);
        m_anniversary_text_layout= (TextView)findViewById(R.id.anniversary_text_layout);
        m_checkin_text_layout= (TextView)findViewById(R.id.checkin_text_layout);
        m_myclub_text_layout= (TextView)findViewById(R.id.myclub_text_layout);
        m_search_text_layout= (TextView)findViewById(R.id.search_text_layout);
        m_myprofile_text_layout= (TextView)findViewById(R.id.myprofile_text_layout);
        m_settings_text_layout= (TextView)findViewById(R.id.settings_text_layout);


        tx.setTypeface(lato_font);
        m_directory_text_layout.setTypeface(lato_font);
        m_contact_text_layout.setTypeface(lato_font);
        m_message_text_layout.setTypeface(lato_font);
        m_anniversary_text_layout.setTypeface(lato_font);
        m_checkin_text_layout.setTypeface(lato_font);
        m_myclub_text_layout.setTypeface(lato_font);
        m_search_text_layout.setTypeface(lato_font);
        m_myprofile_text_layout.setTypeface(lato_font);
        m_settings_text_layout.setTypeface(lato_font);



    }

    public void gotodirectory(View view) {
        Intent i = new Intent(MenuActivity.this, DirectoryActivity.class);
        startActivity(i);
    }

    public void gotoaboutus(View view) {
        Intent i = new Intent(MenuActivity.this, AboutUsActivity.class);
        startActivity(i);
    }
    public void gotoevents(View view)
    {
        Toast.makeText(MenuActivity.this, "EVENT PAGE", Toast.LENGTH_LONG).show();
    }

    public void gotobenifits(View view) {
        Intent i = new Intent(MenuActivity.this, FacilityActivity.class);
        startActivity(i);
    }
    public void gotopush(View view) {
        Intent i = new Intent(MenuActivity.this, PushActivity.class);
        startActivity(i);
    }
    public void gotomsg(View view) {
        Toast.makeText(MenuActivity.this, "MSG PAGE", Toast.LENGTH_LONG).show();
    }

    public void gotoecmembers(View view) {
        Toast.makeText(MenuActivity.this, "EC PAGE", Toast.LENGTH_LONG).show();
    }

    public void gotofounders(View view) {
        Toast.makeText(MenuActivity.this, "FOUNDER PAGE", Toast.LENGTH_LONG).show();
    }

    public void gotolocation(View view)
    {
        String lat="23.738644";
        String lon="90.397128";
        Intent i= new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:"+lat+","+lon+"?z=17&q="+lat+","+lon));
        Intent chooser= Intent.createChooser(i,"choose map");
        startActivity(chooser);
    }

    public void gotoaffiliation(View view) {
        Toast.makeText(MenuActivity.this, "AFFILIATION PAGE", Toast.LENGTH_LONG).show();
    }

    public void gotonothing(View view) {
        Toast.makeText(MenuActivity.this, "DOING NOTHING", Toast.LENGTH_LONG).show();
    }
    public void gotobirthday(View view) {
        Intent i = new Intent(MenuActivity.this, BirthdaysActivity.class);
        startActivity(i);
    }

    public void gotoprofile(View view) {
        Intent i = new Intent(MenuActivity.this, ProfileActivity.class);
        startActivity(i);
        Toast.makeText(MenuActivity.this, "PROFILE", Toast.LENGTH_LONG).show();
    }


    public void gotogallery(View view) {
        Intent i = new Intent(MenuActivity.this, GalleryActivity.class);
        startActivity(i);
    }

    public void gotocontactus(View view)
    {
        Intent i=new Intent(MenuActivity.this,ContactActivity.class);
        startActivity(i);
    }

    public void gotosettings(View view) {
        Intent i=new Intent(MenuActivity.this,SettingsActivity.class);
////        startActivityForResult(i, 107);
        startActivity(i);
    }

    public void gotodocuments(View view) {
        Toast.makeText(MenuActivity.this, "DOCUMENTS", Toast.LENGTH_LONG).show();
    }

    public void gotosearch(View view) {
        Intent i=new Intent(MenuActivity.this,SearchActivity.class);
////        startActivityForResult(i, 107);
        startActivity(i);
    }



    public void showtoken(View view)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MenuActivity.this);
        String token = preferences.getString("token","");
        if(token.equals(""))
        {
            Toast.makeText(MenuActivity.this, "no token", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MenuActivity.this, token, Toast.LENGTH_LONG).show();
        }


    }

    public void onBackPressed() {
        finish();
    }


}