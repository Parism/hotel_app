package com.example.hotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppCompatImageView icAndroid = (AppCompatImageView) findViewById(R.id.imageView);
        icAndroid.setImageResource(R.drawable.ic_hotel_logo);
    }
}
