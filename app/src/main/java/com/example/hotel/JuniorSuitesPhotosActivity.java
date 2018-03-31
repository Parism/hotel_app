package com.example.hotel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;

import java.io.ByteArrayOutputStream;

public class JuniorSuitesPhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior_suites_photos);

        ImageView suite1 = findViewById(R.id.suite1View);
        suite1.setTag(R.drawable.suite1);

        ImageView suite2 = findViewById(R.id.suite2View);
        suite2.setTag(R.drawable.suite2);

        ImageView suite3 = findViewById(R.id.suite3View);
        suite3.setTag(R.drawable.suite3);

        ImageView suite4 = findViewById(R.id.suite4View);
        suite4.setTag(R.drawable.suites4);

        ImageView suite5 = findViewById(R.id.suite5View);
        suite5.setTag(R.drawable.suites5);

        ImageView suite6 = findViewById(R.id.suite6View);
        suite6.setTag(R.drawable.suites6);

        GridLayout photosGrid = findViewById(R.id.juniorSuitesPhotosGrid);

        //Toast.makeText(getApplicationContext(), "total children" + photosGrid.getChildCount(), Toast.LENGTH_SHORT).show();

        setSingleEvent(photosGrid);


    }

    private void setSingleEvent(GridLayout photosGrid){
        for(int i = 0; i<photosGrid.getChildCount(); i++){
            CardView cardView = (CardView)photosGrid.getChildAt(i);
            LinearLayout linear = (LinearLayout)cardView.getChildAt(0);
            ImageView image = (ImageView)linear.getChildAt(0);




            final int tag = (int) image.getTag();

            final Drawable imageSrc = image.getDrawable();
            // final int drawable = image.getImageAlpha();
            final ImagePopup imagePopup = new ImagePopup(JuniorSuitesPhotosActivity.this);
            imagePopup.setBackgroundColor(Color.GRAY);
            imagePopup.setFullScreen(true);
           // imagePopup.setWindowHeight(1200);
            //imagePopup.setWindowWidth(1200);
            imagePopup.setImageOnClickClose(true);

            imagePopup.initiatePopup(imageSrc);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /* Intent intent = new Intent(getApplicationContext(), OpenPhotoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", tag);
                    intent.putExtras(bundle);
                    startActivity(intent);*/

                   // Toast.makeText(getApplicationContext(), "clicked" , Toast.LENGTH_SHORT).show();

                   imagePopup.viewPopup();



                }
            });
        }
    }
}
