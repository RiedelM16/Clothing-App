package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.module.AppGlideModule;

import com.sp18.ssu370.baseprojectapp.R;
import com.sp18.ssu370.baseprojectapp.loadImg;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.sp18.ssu370.baseprojectapp.m_Recycler.MyAdapter;
import java.io.File;
import java.util.ArrayList;


import java.io.File;

/**
 * Created by Gabri on 3/21/2018.
 */

public class BottomsActivity extends AppCompatActivity {



    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "running.jpg");


//    Glide.withwith(context).load(internetUrl)
//    .into(targetImageView);


    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottoms_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView rv= (RecyclerView) findViewById(R.id.bottoms_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv.setAdapter(new MyAdapter(BottomsActivity.this,getData()));
            }
        });

        //ImageView targetImageView = (ImageView) findViewById(R.id.imgView);
        //File file = new File(Environment.getExternalStorageDirectory(), "/" + "OutfitMatcher/" + "bottoms/" + "google.jpg");
        //Glide.with(this).load(file).into(targetImageView);

/*
        String fileName = "running.jpg";
        String completePath = Environment.getExternalStorageDirectory()+File.separator + "/OutfitMatcher/bottoms" + fileName;

        File file = new File(completePath);
        Uri imageUri = Uri.fromFile(file);

        Glide.with(this)
                .load(imageUri)
                .into(targetImageView);
                */


        ImageButton backBtn = findViewById(R.id.BackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(BottomsActivity.this, layoutClass.class);
                startActivity(intent);
            }
        });

        Button newBtn = findViewById(R.id.NewBtnBottoms);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BottomsActivity.this, CameraForBottoms.class);
                // Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }




    private ArrayList<loadImg> getData()
    {
        ArrayList<loadImg> loadingImg=new ArrayList<>();
        //TARGET FOLDER

        File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"OutfitMatcher/bottoms");

        loadImg s;

        if(directory.exists())
        {
            //GET ALL FILES IN DOWNLOAD FOLDER
            File[] files=directory.listFiles();

            //LOOP THRU THOSE FILES GETTING NAME AND URI
            for (int i=0;i<files.length;i++)
            {
                File file=files[i];

                s=new loadImg();
                s.setName(file.getName());
                s.setUri(Uri.fromFile(file));

                loadingImg.add(s);
            }
        }


        return loadingImg;
    }


}