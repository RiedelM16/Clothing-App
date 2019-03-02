package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Environment;

import android.database.Cursor;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.graphics.Typeface;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.location.*;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sp18.ssu370.baseprojectapp.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Toast;

import java.io.File;

import java.util.Random;

import static java.lang.Math.abs;


public class MainActivity extends AppCompatActivity {


    TextView cityField,
             detailsField,
             currentTemperatureField,
             weatherIcon;


    Typeface weatherFont;
    SharedPreferences prefs = null;
    LocationManager locationManager;
    String provider;
    DatabaseHelper articleDB;
    TagDatabaseHelper tagDB;


    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    private String[] FilePathString;
    private String[] FileNameString;
    private File[] listFiles;
    File file;
    File files;

    Random rand = new Random();
    Random rands = new Random();

    private FusedLocationProviderClient mFusedLocationClient;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        final Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
                            public void processFinish(String weather_city,
                                                      String weather_description,
                                                      String weather_temperature,
                                                      String weather_humidity,
                                                      String weather_pressure,
                                                      String weather_updatedOn,
                                                      String weather_iconText,
                                                      String sun_rise) {

                                cityField.setText(weather_city);

                                detailsField.setText(weather_description);
                                currentTemperatureField.setText(weather_temperature);
                                weatherIcon.setText(Html.fromHtml(weather_iconText));
                            }
                        });
                        //Request location updates:
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    double lat = location.getLatitude();
                                    double longi = location.getLongitude();
                                    String latitude = String.valueOf(lat);
                                    String longitude = String.valueOf(longi);

                                    asyncTask.execute(latitude, longitude);

                                }
                                else
                                    asyncTask.execute("25.180000", "89.530000"); //  asyncTask.execute("Latitude", "Longitude")
                            }
                        });
                    }
                } else {
                    finish();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        prefs = getSharedPreferences("com.mycompany.OutfitMatcher", MODE_PRIVATE);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        articleDB = new DatabaseHelper(this);
        tagDB = new TagDatabaseHelper(this);
        provider = locationManager.getBestProvider(new Criteria(), false);
        checkLocationPermission();


        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);
        randShirt();
        randPants();


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

        View view = findViewById(R.id.placeHereShirt);
        view.setRotation(90);
        view.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                randShirt();
            }
        });
        View views = findViewById(R.id.placeHerePants);
        views.setRotation(90);
        views.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                randPants();
            }
        });

        ImageButton ClosetBtn = findViewById(R.id.ClosetBtn);
        ClosetBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, layoutClass.class);
                startActivity(intent);
            }
        });

        ImageButton RandBtn = findViewById(R.id.RandomBtn);
        RandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     Intent intent = new Intent(MainActivity.this, OutfitActivity.class);
                     startActivity(intent);

                }
        }
        );

        ImageButton Laundrybtm = findViewById(R.id.laundry);
        Laundrybtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = articleDB.deleteAll();
                Toast.makeText(MainActivity.this, "Cleared: " + count + " Articles from DB", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //asyncTask.cancel(true);
    }

    protected void onResume() {
        super.onResume();
        randShirt();
        randPants();
        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            setContentView(R.layout.first_time_run);
            ViewPager vp = findViewById(R.id.size_vp);
            vp.setAdapter(new First_Run(this));
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            final Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
                public void processFinish(String weather_city,
                                          String weather_description,
                                          String weather_temperature,
                                          String weather_humidity,
                                          String weather_pressure,
                                          String weather_updatedOn,
                                          String weather_iconText,
                                          String sun_rise) {

                    cityField.setText(weather_city);

                    detailsField.setText(weather_description);
                    currentTemperatureField.setText(weather_temperature);
                    weatherIcon.setText(Html.fromHtml(weather_iconText));
                }
            });
            //Request location updates:
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        double lat = location.getLatitude();
                        double longi = location.getLongitude();
                        String latitude = String.valueOf(lat);
                        String longitude = String.valueOf(longi);

                        asyncTask.execute(latitude, longitude);

                    } else
                        asyncTask.execute("25.180000", "89.530000"); //  asyncTask.execute("Latitude", "Longitude")
                }
            });
        }
    }

   public void randShirt()
   {
       if (!articleDB.empty()) {
           Cursor Articles = articleDB.getAllData();
           while (true) {
               int value = (int) System.currentTimeMillis();
               Articles.moveToPosition(abs(value % Articles.getCount()));
               if (Articles.getString(2).equals("1") || Articles.getCount() == 1)
                   break;
           }
           if (Articles.getString(2).equals("1")) {
               String pathName = Articles.getString(3);
               ImageView view = findViewById(R.id.placeHereShirt);
               File file = new File(pathName);
               Glide.with(this).load(Uri.fromFile(file)).into(view);
           }
       }
   }

   public void randPants() {

       if (!articleDB.empty()) {
           Cursor Articles = articleDB.getAllData();
           while (true) {
               int value = (int) System.currentTimeMillis();
               Articles.moveToPosition(abs(value % Articles.getCount()));
               if (Articles.getString(2).equals("2" ) || Articles.getCount() == 1)
                   break;
           }
           if (Articles.getString(2).equals("2" )) {
               String pathNames = Articles.getString(3);
               ImageView views = findViewById(R.id.placeHerePants);
               File file = new File(pathNames);
               Glide.with(this).load(Uri.fromFile(file)).into(views);
           }
       }

   }


}
