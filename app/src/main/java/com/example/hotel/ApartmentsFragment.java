package com.example.hotel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class ApartmentsFragment extends Fragment {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.ap1, R.drawable.ap2, R.drawable.ap3};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_apartments, container, false);

        TextView apartDesc = view.findViewById(R.id.apartmentsDescView);
        apartDesc.setText(Html.fromHtml(getString(R.string.apartmentsDesc)));

        carouselView = (CarouselView) view.findViewById(R.id.carouselApartmentsView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}
