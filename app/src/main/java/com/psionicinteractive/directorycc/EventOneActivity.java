package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by PSIONIC on 12/12/2016.
 */

public class EventOneActivity extends Activity {

    TextView mTextView1;
    TextView mTextView2;
    TextView mTextView3;
    TextView mTextView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_event_one);
        Typeface font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        mTextView1= (TextView) findViewById(R.id.text1);
        mTextView2= (TextView) findViewById(R.id.text2);
        mTextView3= (TextView) findViewById(R.id.text3);
        mTextView4= (TextView) findViewById(R.id.text4);

        mTextView1.setTypeface(font_lato);
        mTextView2.setTypeface(font_lato);
        mTextView3.setTypeface(font_lato);
        mTextView4.setTypeface(font_lato);



    }
}
