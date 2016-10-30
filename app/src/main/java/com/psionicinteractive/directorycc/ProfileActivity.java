package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by PSIONIC on 10/30/2016.
 */

public class ProfileActivity extends Activity {
    ImageView imageView;
    RoundImage roundImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        imageView= (ImageView) findViewById(R.id.profile_image);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.profile_img);
        roundImage=new RoundImage(bitmap);
        imageView.setImageDrawable(roundImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Image changed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
