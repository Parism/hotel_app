package com.example.hotel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class OpenPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_photo);

        Bundle extras = getIntent().getExtras();
       /* byte[] byteArray = extras.getByteArray("image");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = findViewById(R.id.openImageView);

        image.setImageBitmap(bmp);*/

        int tag = extras.getInt("image");
        ImageView image = findViewById(R.id.openImageView);
        Drawable d = getResources().getDrawable(tag);
        image.setImageDrawable(d);
    }
}
