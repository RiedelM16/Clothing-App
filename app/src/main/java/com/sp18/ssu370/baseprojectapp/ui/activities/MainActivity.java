package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.graphics.Typeface;
import android.text.Html;
import android.widget.TextView;


import com.sp18.ssu370.baseprojectapp.R;

public class MainActivity extends AppCompatActivity {

    TextView cityField,
             detailsField,
             currentTemperatureField,
             humidity_field,
             pressure_field,
             weatherIcon,
             updatedField;

    Typeface weatherFont;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.mycompany.OutfitMatcher", MODE_PRIVATE);

        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        //updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        //humidity_field = (TextView)findViewById(R.id.humidity_field);
        //pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);

        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city,
                                      String weather_description,
                                      String weather_temperature,
                                      String weather_humidity,
                                      String weather_pressure,
                                      String weather_updatedOn,
                                      String weather_iconText,
                                      String sun_rise) {

                cityField.setText(weather_city);
               // updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
               // humidity_field.setText("Humidity: "+weather_humidity);
               // pressure_field.setText("Pressure: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));
            }
        });
        asyncTask.execute("38.340329", "-122.699980"); //  asyncTask.execute("Latitude", "Longitude")


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
    }
}
