package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sp18.ssu370.baseprojectapp.R;

/**
 * Created by Gabri on 3/22/2018.
 */

public class BottomsGridView extends Activity {

//  Cursor used to access the results from querying for images on the SD card.

    private Cursor cursor;

    // Column index for the Thumbnails Image IDs.

    private int columnIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottoms_page);

        // Set up an array of the Thumbnail Image ID column we want
        String[] projection = {MediaStore.Images.Thumbnails._ID};
        // Create the cursor pointing to the SDCard
        cursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                projection, // Which columns to return
                null,       // Return all rows
                null,
                MediaStore.Images.Thumbnails.IMAGE_ID);
        // Get the column index of the Thumbnails Image ID
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

        GridView sdcardImages = (GridView) findViewById(R.id.gridview_bottoms);
        sdcardImages.setAdapter(new ImageAdapter(BottomsGridView.this));


    }
}

