package com.example.hotel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;

public class JuniorPhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior_photos);

        GridView gridView = findViewById(R.id.juniorPhotosGridView);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(JuniorPhotosActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public class ImageAdapter extends BaseAdapter{
        private Context mContext;

        public ImageAdapter(Context c){
            mContext = c;
        }

        public int getCount() {
            return mThumbsIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position){
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView =  new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350,350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
            imageView.setImageResource(mThumbsIds[position]);


            final ImagePopup imagePopup = new ImagePopup(JuniorPhotosActivity.this);
            imagePopup.setBackgroundColor(Color.BLACK);
            imagePopup.setFullScreen(true);
            // imagePopup.setWindowHeight(1200);
            //imagePopup.setWindowWidth(1200);
            imagePopup.setImageOnClickClose(true);

            Drawable dr = imageView.getDrawable();

            imagePopup.initiatePopup(dr);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imagePopup.viewPopup();
                }
            });

            return imageView;
        }

        private Integer[] mThumbsIds = {
                R.drawable.suite1,
                R.drawable.suite2,
                R.drawable.suite3,
                R.drawable.suites4,
                R.drawable.suites5,
                R.drawable.suites6
        };
    }
}
