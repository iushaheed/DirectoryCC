package com.psionicinteractive.directorycc;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by iShaheed on 8/27/2016.
 */
public class PutGetExtra extends Activity implements OnClickListener {
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
//        setCornerRadius(float cornerRadius)
//        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);
//        dr.setCornerRadius(cornerRadius);
//        imageView.setImageDrawable(dr);

        imageView = (ImageView) findViewById(R.id.profile_image);
        textName = (TextView) findViewById(R.id.textView1);
        textEmail = (TextView) findViewById(R.id.textView2);
        textPhone = (TextView) findViewById(R.id.textView3);

        smsButton = (Button) findViewById(R.id.sendSms);
        smsButton.setOnClickListener(this);

        callButton = (Button) findViewById(R.id.doCall);
        callButton.setOnClickListener(this);

        emailButton = (Button) findViewById(R.id.sendEmail);
        emailButton.setOnClickListener(this);

//        TextView mTextNOTICE;

        Intent intent = getIntent();

        //receiving data from CustomLstAdapter class
        //and
        //setting user phone,email,sms contact info for button call
        name = intent.getStringExtra("name");
        emailAddress = intent.getStringExtra("id");
        imageUrl = intent.getStringExtra("image");
        phoneNumber = intent.getStringExtra("phone");

        //setting profile name and email address and other info in the user_profile layout to display
        textName.setText(name);
        textEmail.setText(emailAddress);
        textPhone.setText(phoneNumber);

        //loading profile image in imageView of user_profile
        Picasso.with(this).load(imageUrl).into(imageView);


//        Toast.makeText(this, fName + " " + lName, Toast.LENGTH_LONG).show();

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
