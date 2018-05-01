package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sp18.ssu370.baseprojectapp.R;

/**
 * Created by Gabriel on 3/20/2018.
 */

public class layoutClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);


        Button topsBtn = (Button) findViewById(R.id.TopsBtn);
        topsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layoutClass.this, TopsActivity.class);
                startActivity(intent);
            }
        });

        Button bottomsBtn = (Button) findViewById(R.id.BottomsBtn);
        bottomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layoutClass.this, BottomsActivity.class);
                startActivity(intent);
            }
        });

        Button accBtn = (Button) findViewById(R.id.AccessoriesBtn);
        accBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layoutClass.this, AccessoriesActivity.class);
                startActivity(intent);
            }
        });

        Button shoesBtn = (Button) findViewById(R.id.ShoesBtn);
        shoesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layoutClass.this, ShoesActivity.class);
                startActivity(intent);
            }
        });

        Button outerwearBtn = (Button) findViewById(R.id.OutterWearBtn);
        outerwearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layoutClass.this, OuterWearActivity.class);
                startActivity(intent);
            }
        });

        Button dressesBtn = (Button) findViewById(R.id.DressesBtn);
        dressesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layoutClass.this, DressesActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backBtn = findViewById(R.id.BackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(layoutClass.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
