package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.graphics.Typeface;
import android.text.Html;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sp18.ssu370.baseprojectapp.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import java.io.File;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD


=======
    TextView cityField,
             detailsField,
             currentTemperatureField,
             weatherIcon;


    Typeface weatherFont;
    SharedPreferences prefs = null;
    LocationManager locationManager;
    String provider;

    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    File file;

    Random rand = new Random();

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

>>>>>>> master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.mycompany.OutfitMatcher", MODE_PRIVATE);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = locationManager.getBestProvider(new Criteria(), false);
        checkLocationPermission();
/*
        String pathName = Environment.getExternalStorageDirectory()+File.separator+"OutfitMatcher/bottoms/img1524521290283.jpg";
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        BitmapDrawable bd = new BitmapDrawable(res, bitmap);
        View view = findViewById(R.id.placeHere);
        view.setBackgroundDrawable(bd);
*/
        file = new File(Environment.getExternalStorageDirectory()+File.separator+"OutfitMatcher/bottoms");

        if (file.isDirectory()) {
            listFile = file.listFiles();
            // Create a String array for FilePathStrings
            FilePathStrings = new String[listFile.length];
            // Create a String array for FileNameStrings
            FileNameStrings = new String[listFile.length];

            int  n = rand.nextInt(listFile.length);

            for (int i = 0; i < listFile.length; i++) {
                // Get the path of the image file
                FilePathStrings[i] = listFile[i].getAbsolutePath();
                // Get the name image file
                FileNameStrings[i] = listFile[i].getName();


            }
            String pathName = Environment.getExternalStorageDirectory()+File.separator+"OutfitMatcher/bottoms/"+listFile[n].getName();
            Resources res = getResources();
            Bitmap bitmap = BitmapFactory.decodeFile(pathName);
            BitmapDrawable bd = new BitmapDrawable(res, bitmap);
            View view = findViewById(R.id.placeHereShirt);
            view.setBackgroundDrawable(bd);

        }



        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);



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

        ImageButton ClosetBtn = findViewById(R.id.ClosetBtn);
        ClosetBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, layoutClass.class);
                startActivity(intent);
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
}
