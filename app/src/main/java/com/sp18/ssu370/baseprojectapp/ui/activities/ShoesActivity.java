package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.sp18.ssu370.baseprojectapp.R;
import com.sp18.ssu370.baseprojectapp.loadImg;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.sp18.ssu370.baseprojectapp.m_Recycler.MyAdapter;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Gabri on 3/21/2018.
 */

public class ShoesActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoes_page);


        final RecyclerView rv = (RecyclerView) findViewById(R.id.shoes_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(ShoesActivity.this, getData()));


        ImageButton backBtn = findViewById(R.id.BackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ShoesActivity.this, layoutClass.class);
                startActivity(intent);
            }
        });

        Button newBtn = findViewById(R.id.NewBtnShoes);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoesActivity.this, CameraForShoes.class);
                // Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });
    }

    private ArrayList<loadImg> getData() {
        ArrayList<loadImg> loadingImg = new ArrayList<>();
        //TARGET FOLDER

        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "OutfitMatcher/shoes");

        loadImg s;

        if (directory.exists()) {
            //GET ALL FILES IN DOWNLOAD FOLDER
            File[] files = directory.listFiles();

            //LOOP THRU THOSE FILES GETTING NAME AND URI
            for (int i = 0; i < files.length; i++) {
                File file = files[i];

                s = new loadImg();
                s.setName(file.getName());
                s.setUri(Uri.fromFile(file));

                loadingImg.add(s);
            }
        }


        return loadingImg;
    }
}


