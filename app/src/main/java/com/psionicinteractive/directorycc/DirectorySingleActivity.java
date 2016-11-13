package com.psionicinteractive.directorycc;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by iShaheed on 8/27/2016.
 */
public class DirectorySingleActivity extends Activity implements OnClickListener {
    ImageView imageView;
    TextView textName;
    TextView textEmail;
    TextView textPhone;

    String name="";
    String emailAddress="";
    String imageUrl="";
    String phoneNumber="";


    Button smsButton;
    Button callButton;
    Button emailButton;

    RoundImage roundImage;

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





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_directory_single);
//        setCornerRadius(float cornerRadius)
//        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);
//        dr.setCornerRadius(cornerRadius);
//        imageView.setImageDrawable(dr);

        Typeface lato_font = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        imageView = (ImageView) findViewById(R.id.profile_image);
        textName = (TextView) findViewById(R.id.namedata);
        textEmail = (TextView) findViewById(R.id.emaildata);
        textPhone = (TextView) findViewById(R.id.phonedata);

        smsButton = (Button) findViewById(R.id.sendSms);
        smsButton.setOnClickListener(this);

        callButton = (Button) findViewById(R.id.doCall);
        callButton.setOnClickListener(this);

        emailButton = (Button) findViewById(R.id.sendEmail);
        emailButton.setOnClickListener(this);

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
        smsButton.setTypeface(lato_font);
        callButton.setTypeface(lato_font);
        emailButton.setTypeface(lato_font);










//        TextView mTextNOTICE;

        Intent intent = getIntent();

        //receiving data from CustomLstAdapter class
        //and
        //setting user phone,email,sms contact info for button call
        name = intent.getStringExtra("name");
        emailAddress = intent.getStringExtra("id");
        imageUrl = intent.getStringExtra("image");
        phoneNumber = intent.getStringExtra("phone");

        //setting profile_img name and email address and other info in the activity_directory_single layout to display
        textName.setText(name);
        textEmail.setText(emailAddress);
        textPhone.setText(phoneNumber);

        //loading profile_img image in imageView of activity_directory_single
//        Picasso.with(this)
//                .load(imageUrl)
//                .resize(200,200)
//                .centerCrop()
//                .into(imageView);

//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.profile_img);

        Bitmap bitmap= getit(imageUrl);
        roundImage=new RoundImage(bitmap);
        imageView.setImageDrawable(roundImage);



//        Toast.makeText(this, fName + " " + lName, Toast.LENGTH_LONG).show();

    }

    private Bitmap getit(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v.getId()==R.id.doCall)
        {
//            String url1="tel:01799445555";
            Intent call =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
            Intent chooser= Intent.createChooser(call,"Choose Application");
            startActivity(chooser);


        }else if(v.getId()==R.id.sendEmail){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            intent.setData(Uri.parse("mailto:"+emailAddress));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if(v.getId()==R.id.sendSms){
            Uri uri = Uri.parse("smsto:"+phoneNumber);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.putExtra("sms_body", "");
            startActivity(i);


        }

    }

}
