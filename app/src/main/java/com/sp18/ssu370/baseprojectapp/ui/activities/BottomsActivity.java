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

        ImageView targetImageView = (ImageView) findViewById(R.id.imgView);
        File file = new File(Environment.getExternalStorageDirectory(), "/" + "OutfitMatcher/" + "bottoms/" + "blog_image_1.jpg");
        Glide.with(this).load(file).into(targetImageView);

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


}