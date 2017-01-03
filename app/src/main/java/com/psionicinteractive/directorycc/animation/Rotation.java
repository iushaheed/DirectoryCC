package com.psionicinteractive.directorycc.animation;

import android.support.v4.view.ViewPager;
import android.view.View;


public class Rotation implements ViewPager.PageTransformer{

    private int degrees = 150;
    private float centerFactor = (float) Math.tan(Math.toRadians(degrees / 2))/2;

    @Override
    public void transformPage(View view, float position){

        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        view.setPivotX((float) pageWidth / 2);

        view.setPivotY(pageHeight + pageWidth * centerFactor);

        if(position < -1){ //[-infinity,1)
            //off to the arrow_left by a lot
            view.setRotation(0);
            view.setAlpha(0);
        }else if(position <= 1){ //[-1,1]
            view.setTranslationX((-position) * pageWidth); //shift the view over
            view.setRotation(position * (180 - degrees)); //rotate it
            // Fade the page relative to its distance from the center
            float minAlpha = 0.7f;
            view.setAlpha(Math.max(minAlpha, 1 - Math.abs(position)/3));
        }else{ //(1, +infinity]
            //off to the arrow_right by a lot
            view.setRotation(0);
            view.setAlpha(0);
        }
    }
}