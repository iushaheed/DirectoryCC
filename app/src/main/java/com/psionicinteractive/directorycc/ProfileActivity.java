package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Created by PSIONIC on 10/30/2016.
 */

public class ProfileActivity extends Activity {
    ImageView imageView;
    RoundImage roundImage;
    String UPLOAD_URL="http://192.168.0.104:8000/image";

    TextView textName;
    TextView textEmail;
    TextView textPhone;

    String name="";
    String emailAddress="";
    String imageUrl="";
    String phoneNumber="";

    TextView headertext;
    TextView joiningtext;
    TextView member_sn_text;
    TextView type_text;
    TextView nametag;
    //    TextView namedata;
    TextView dobtag;
    TextView dobdata;
    TextView addresstag;
    TextView addressdata;
    TextView emailtag;
    //    TextView emaildata;
    TextView phonetag;
    //    TextView phonedata;
    TextView statustag;
    TextView statusdata;
    TextView anniversarytag;
    TextView anniversarydata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        Typeface lato_font = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        imageView= (ImageView) findViewById(R.id.profile_image);


        textName = (TextView) findViewById(R.id.namedata);
        textEmail = (TextView) findViewById(R.id.emaildata);
        textPhone = (TextView) findViewById(R.id.phonedata);

        headertext = (TextView) findViewById(R.id.header_text);
        joiningtext = (TextView) findViewById(R.id.joiningtext);
        member_sn_text = (TextView) findViewById(R.id.member_sn_text);
        type_text = (TextView) findViewById(R.id.type_text);
        nametag = (TextView) findViewById(R.id.nametag);
//        namedata = (TextView) findViewById(R.id.namedata);
        dobtag = (TextView) findViewById(R.id.dobtag);
        dobdata = (TextView) findViewById(R.id.dobdata);
        addresstag = (TextView) findViewById(R.id.addresstag);
        addressdata = (TextView) findViewById(R.id.addressdata);
        emailtag = (TextView) findViewById(R.id.emailtag);
//        emaildata = (TextView) findViewById(R.id.emaildata);
        phonetag = (TextView) findViewById(R.id.phonetag);
//        phonedata = (TextView) findViewById(R.id.phonedata);
        statustag = (TextView) findViewById(R.id.statustag);
        statusdata = (TextView) findViewById(R.id.statusdata);
        anniversarytag = (TextView) findViewById(R.id.anniversarytag);
        anniversarydata = (TextView) findViewById(R.id.anniversarydata);

        headertext.setTypeface(lato_font);
        joiningtext.setTypeface(lato_font);
        member_sn_text.setTypeface(lato_font);
        type_text.setTypeface(lato_font);
        nametag.setTypeface(lato_font);
        textName.setTypeface(lato_font);
        dobtag.setTypeface(lato_font);
        dobdata.setTypeface(lato_font);
        addresstag.setTypeface(lato_font);
        addressdata.setTypeface(lato_font);
        emailtag.setTypeface(lato_font);
        textEmail.setTypeface(lato_font);
        phonetag.setTypeface(lato_font);
        textPhone.setTypeface(lato_font);
        statustag.setTypeface(lato_font);
        statusdata.setTypeface(lato_font);
        anniversarytag.setTypeface(lato_font);
        anniversarydata.setTypeface(lato_font);



        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.profile_img);
        roundImage=new RoundImage(bitmap);
        imageView.setImageDrawable(roundImage);

    }

}

