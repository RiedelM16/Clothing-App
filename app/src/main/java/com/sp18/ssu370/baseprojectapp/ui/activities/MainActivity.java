package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sp18.ssu370.baseprojectapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.ScrollViewPgr);
        viewPager.setAdapter(new CustomPagerAdapter(this));

        Button ClosetBtn = findViewById(R.id.ClosetBtn);
        ClosetBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setContentView(R.layout.layout);
            }
        });
    }
}
