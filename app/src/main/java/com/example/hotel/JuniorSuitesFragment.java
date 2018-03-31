package com.example.hotel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class JuniorSuitesFragment extends Fragment {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.suite1, R.drawable.suite2, R.drawable.suite3};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View juniorSuitesView = inflater.inflate(R.layout.fragment_junior_suites, container, false);

        TextView juniorSuitesDesc = juniorSuitesView.findViewById(R.id.junior_suites_desc_view);
        juniorSuitesDesc.setText(Html.fromHtml(getString(R.string.junior_suites_desc)));

        CardView morePhotos = juniorSuitesView.findViewById(R.id.morePhotosCardView);

        morePhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),JuniorPhotosActivity.class);
                startActivity(intent);
            }
        });

        carouselView = (CarouselView) juniorSuitesView.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        return juniorSuitesView;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}
