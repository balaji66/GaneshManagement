package com.durga.balaji66.ganeshmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageViewSplash1;
    Animation mAnimation1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initObjects();
        anim1();

    }

    private void initViews() {
        imageViewSplash1 = (ImageView) findViewById(R.id.imageView);
    }

    private void initObjects() {
        mAnimation1 = AnimationUtils.loadAnimation(this, R.anim.antirotate);
    }

    private void anim1() {
        imageViewSplash1.startAnimation(mAnimation1);
        mAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
