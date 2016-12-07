package com.psionicinteractive.directorycc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gospelware.liquidbutton.LiquidButton;

/**
 * Created by PSIONIC on 12/7/2016.
 */

public class LiquidActivity extends AppCompatActivity {
    private float progress;


//    @Bind(R.id.liquid_button)
    LiquidButton liquidButton;

//    @Bind(R.id.progress_tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquid);
        getSupportActionBar().hide();

        liquidButton= (LiquidButton) findViewById(R.id.liquid_button);
        textView= (TextView) findViewById(R.id.progress_tv);

        liquidButton.startPour();
        liquidButton.setAutoPlay(true);

        liquidButton.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                Intent i = new Intent(LiquidActivity.this, MenuActivity.class);
                Toast.makeText(LiquidActivity.this, "WELCOME BACK", Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();
            }

            @Override
            public void onProgressUpdate(float progress) {
                textView.setText(String.format("%.2f", progress * 100) + "%");
            }
        });

    }
}
