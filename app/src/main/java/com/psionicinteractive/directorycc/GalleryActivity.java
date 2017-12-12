package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.psionicinteractive.directorycc.animation.DepthPage;
import com.psionicinteractive.directorycc.animation.Rotation;
import com.psionicinteractive.directorycc.animation.ZoomOut;

public class GalleryActivity extends AppCompatActivity {

    private ViewPager mViewPager1;
//    private ViewPager mViewPager2;
//    private ViewPager mViewPager3;

    private int[] mImageId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().hide();

        mImageId = new int[]{
                R.drawable.aa,
                R.drawable.ba,
                R.drawable.ca,
                R.drawable.da,
                R.drawable.ea,
                R.drawable.fa,
                R.drawable.ga,
                R.drawable.ha
        };


        // Locate the ViewPager in activity_main.xml
        mViewPager1 = (ViewPager) findViewById(R.id.view_pager_one);
//        mViewPager.setPageTransformer(true, new DepthPage());

        // Pass params to ViewPagerAdapter Class
        PagerAdapter adapter1 = new ViewPagerAdapter(GalleryActivity.this, mImageId);

        mViewPager1.setPageTransformer(true, new DepthPage());
        // Bind the ViewPager Adapter to the ViewPager
        mViewPager1.setAdapter(adapter1);



        /////////////////////////////////////
//        mViewPager2 = (ViewPager) findViewById(R.id.view_pager_two);
////        mViewPager.setPageTransformer(true, new DepthPage());
//
//        // Pass params to ViewPagerAdapter Class
//        PagerAdapter adapter2 = new ViewPagerAdapter(GalleryActivity.this, mImageId);
//
//        mViewPager2.setPageTransformer(true, new DepthPage());
//        // Bind the ViewPager Adapter to the ViewPager
//        mViewPager2.setAdapter(adapter2);



        //////////////////////////////////////
//        mViewPager3 = (ViewPager) findViewById(R.id.view_pager_three);
////        mViewPager.setPageTransformer(true, new DepthPage());
//
//        // Pass params to ViewPagerAdapter Class
//        PagerAdapter adapter3 = new ViewPagerAdapter(GalleryActivity.this, mImageId);
//
//        mViewPager3.setPageTransformer(true, new Rotation());
//        // Bind the ViewPager Adapter to the ViewPager
//        mViewPager3.setAdapter(adapter3);


    }
    ///////////////
}
