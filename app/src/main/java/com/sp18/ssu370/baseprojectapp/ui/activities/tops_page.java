package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sp18.ssu370.baseprojectapp.R;

/**
 * Created by Gabri on 3/21/2018.
 */

public class tops_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tops_page);

        ImageButton backBtn = findViewById(R.id.BackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(tops_page.this, layoutClass.class);
                startActivity(intent);
            }
        });

        Button newBtn = findViewById(R.id.NewBtn);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tops_page.this, Camera.class);
            }
        });
    }
}
