package com.shanaptech.bloodbank;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.shanaptech.bloodbank.Utils.ImageAdapter;


public class MainActivity extends AppCompatActivity {

    Button skip_btn ;
    ViewPager viewPager ;
    InkPageIndicator inkPageIndicator ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);


        skip_btn = findViewById(R.id.skip);
        skip_btn.setOnClickListener(new View.OnClickListener() {

         @Override
           public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
            }
       });

        viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
        changeState();

          }

         private void changeState()
    {

             viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                 public void onPageScrollStateChanged(int state) {
                     inkPageIndicator.setViewPager(viewPager);

                 }
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
                {

                }

                public void onPageSelected(int position) {

                 }
             });     }



    }

