package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


import com.sp18.ssu370.baseprojectapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ClosetBtn = (Button) findViewById(R.id.ClosetBtn);
        ClosetBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setContentView(R.layout.layout);
            }
        });
    }
}
