package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by teamBinary on 8/17/2016.
 */
public class FacilityActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_facility);
    }
    public void gotofacility_a(View view){
        Toast.makeText(FacilityActivity.this, "Facility A Selected", Toast.LENGTH_LONG).show();
        Intent i = new Intent(FacilityActivity.this, Facility_a_Activity.class);
        startActivity(i);
    }
    public void gotofacility_b(View view){
//        Intent i = new Intent(FacilityActivity.this, DirectoryActivity.class);
        Toast.makeText(FacilityActivity.this, "Facility B Selected", Toast.LENGTH_LONG).show();
//        startActivity(i);
        Intent i = new Intent(FacilityActivity.this, Facility_b_Activity.class);
        startActivity(i);

    }
    public void gotofacility_c(View view){
//        Intent i = new Intent(FacilityActivity.this, DirectoryActivity.class);
        Toast.makeText(FacilityActivity.this, "Facility C Selected", Toast.LENGTH_LONG).show();
//        startActivity(i);
        Intent i = new Intent(FacilityActivity.this, Facility_c_Activity.class);
        startActivity(i);
    }
    public void gotofacility_d(View view){
//        Intent i = new Intent(FacilityActivity.this, DirectoryActivity.class);
        Toast.makeText(FacilityActivity.this, "Facility D Selected", Toast.LENGTH_LONG).show();
//        startActivity(i);
    }
    public void gotofacility_e(View view){
//        Intent i = new Intent(FacilityActivity.this, DirectoryActivity.class);
        Toast.makeText(FacilityActivity.this, "Facility E Selected", Toast.LENGTH_LONG).show();
//        startActivity(i);
    }
    public void gotofacility_f(View view){
//        Intent i = new Intent(FacilityActivity.this, DirectoryActivity.class);
        Toast.makeText(FacilityActivity.this, "Facility F Selected", Toast.LENGTH_LONG).show();
//        startActivity(i);
    }
}
