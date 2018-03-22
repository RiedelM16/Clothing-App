package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sp18.ssu370.baseprojectapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton Camera = findViewById(R.id.CameraBtn);
        Camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //setContentView(R.layout.camera_view);
                Intent intent = new Intent(MainActivity.this, AndroidCameraApi.class);
                startActivity(intent);
            }
        });

        ViewPager viewPager = findViewById(R.id.ScrollViewPgr);
        viewPager.setAdapter(new CustomPagerAdapter(this));

        //ViewPager viewPager1 = findViewById(R.id.ClothesViewPager);
        //viewPager1.setAdapter(new CustomPagerAdapter1(this));

        Button ClosetBtn = findViewById(R.id.ClosetBtn);
        ClosetBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, layoutClass.class);
                startActivity(intent);
            }
        });
    }
}
