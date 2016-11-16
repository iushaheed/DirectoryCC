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

import org.w3c.dom.Text;

/**
 * Created by PSIONIC on 8/31/2016.
 */
public class ContactActivity extends Activity {

    TextView m_tv_contact_text;
    TextView m_tv_dcl_text;
    TextView m_tv_address_text_layout;
    TextView m_tv_web_text_layout;
    TextView m_tv_mail_text_layout;

    TextView m_tv_phone_text_layout_1;
    TextView m_tv_phone_text_layout_2;
    TextView m_tv_phone_text_layout_3;

//    Button btn_11;
//    Button btn_12;
//    Button btn_13;
//    Button btn_21;
//    Button btn_22;
//    Button btn_23;
//    Button btn_31;
//    Button btn_32;
//    Button btn_33;
//    Button btn_41;
//    Button btn_42;
//    Button btn_43;
//    Button btn_51;
//    Button btn_52;
//    Button btn_53;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_contact);

        Typeface font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        m_tv_contact_text = (TextView)findViewById(R.id.contact_text);
        m_tv_mail_text_layout = (TextView)findViewById(R.id.mail_text_layout);
        m_tv_web_text_layout = (TextView)findViewById(R.id.web_text_layout);
        m_tv_address_text_layout = (TextView)findViewById(R.id.address_text_layout);
        m_tv_dcl_text = (TextView)findViewById(R.id.dcl_text);

        m_tv_phone_text_layout_1 = (TextView)findViewById(R.id.phone_1);
        m_tv_phone_text_layout_2 = (TextView)findViewById(R.id.phone_2);
        m_tv_phone_text_layout_3 = (TextView)findViewById(R.id.phone_3);

        m_tv_contact_text.setTypeface(font_lato);
        m_tv_dcl_text.setTypeface(font_lato);
        m_tv_address_text_layout.setTypeface(font_lato);
        m_tv_web_text_layout.setTypeface(font_lato);
        m_tv_mail_text_layout.setTypeface(font_lato);

        m_tv_phone_text_layout_1.setTypeface(font_lato);
        m_tv_phone_text_layout_2.setTypeface(font_lato);
        m_tv_phone_text_layout_3.setTypeface(font_lato);

//        btn_11 = (Button) findViewById(R.id.btn_11);btn_12 = (Button)findViewById(R.id.btn_12);btn_13 = (Button)findViewById(R.id.btn_13);btn_21 = (Button)findViewById(R.id.btn_21);btn_22 = (Button)findViewById(R.id.btn_22);btn_23 = (Button)findViewById(R.id.btn_23);btn_31 = (Button)findViewById(R.id.btn_31);btn_32 = (Button)findViewById(R.id.btn_32);btn_33 = (Button)findViewById(R.id.btn_33);btn_41 = (Button)findViewById(R.id.btn_41);btn_42 = (Button)findViewById(R.id.btn_42);btn_43 = (Button)findViewById(R.id.btn_43);btn_51 = (Button)findViewById(R.id.btn_51);btn_52 = (Button)findViewById(R.id.btn_52);btn_53 = (Button)findViewById(R.id.btn_53);
//        btn_11.setTypeface(font_lato);btn_12.setTypeface(font_lato);btn_13.setTypeface(font_lato);btn_21.setTypeface(font_lato);btn_22.setTypeface(font_lato);btn_23.setTypeface(font_lato);btn_31.setTypeface(font_lato);btn_32.setTypeface(font_lato);btn_33.setTypeface(font_lato);btn_41.setTypeface(font_lato);btn_42.setTypeface(font_lato);btn_43.setTypeface(font_lato);btn_51.setTypeface(font_lato);btn_52.setTypeface(font_lato);btn_53.setTypeface(font_lato);

    }


    public void call_1(View view) {

        String phoneNumber="+8801921334500";
        Intent call =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        Intent chooser= Intent.createChooser(call,"Choose Application");
        startActivity(chooser);
    }

    public void call_2(View view) {

        String phoneNumber="+8801921334500";
        Intent call =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        Intent chooser= Intent.createChooser(call,"Choose Application");
        startActivity(chooser);
    }

    public void call_3(View view) {

        String phoneNumber="+8801921334500";
        Intent call =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        Intent chooser= Intent.createChooser(call,"Choose Application");
        startActivity(chooser);
    }
}
