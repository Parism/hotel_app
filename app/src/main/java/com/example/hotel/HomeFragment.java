package com.example.hotel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;


public class HomeFragment extends Fragment {

    private static Intent newFacebookIntent(PackageManager pm, String url) { //creates an intent to the facebook page
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }



    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void newInstagramIntent(Context activity, String url) {   //creates an intent to the instagram page

        Uri uri = Uri.parse(url);
        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
        insta.setPackage("com.instagram.android");

        if (isIntentAvailable(activity, insta)) {
            startActivity(insta);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        final DrawerLayout mDrawerLayout = homeView.findViewById(R.id.menu_drawer);


        Toolbar toolbar =  homeView.findViewById(R.id.homeToolbar); //toolbar creation
        final AppCompatActivity activity = (AppCompatActivity) getActivity(); //find current activity
        activity.setSupportActionBar(toolbar);

        if(activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false); //hide app title from toolbar
        }

        ImageView menu_icon = homeView.findViewById(R.id.action_menu);
        ImageView chat_icon = homeView.findViewById(R.id.action_chat);

        menu_icon.setOnClickListener(new View.OnClickListener() {  //menu listener creation
            @Override
            public void onClick(View view) {  //menu icon listener creation
                mDrawerLayout.openDrawer(GravityCompat.START);//
            }
        });



        chat_icon.setOnClickListener(new View.OnClickListener() {  //chat listener creation
            @Override
            public void onClick(View view) {  //chat icon listener
                ((HomeActivity)activity).setCurrentItem (1, true); //brings you to the chat fragment
            }
        });



       NavigationView navigationView = homeView.findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(                   //Handle the drawer options
               new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();

                        if (id == R.id.nav_like_facebook) { //opens an intent to the fb page
                            Intent fbIntent = newFacebookIntent(activity.getPackageManager(),"https://www.facebook.com/antonysuites/");
                            startActivity(fbIntent);

                        } else if(id == R.id.nav_follow_instagram){ //opens an intent to the insta page
                            newInstagramIntent(activity, "http://instagram.com/_u/antony_suites");

                        }



                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });




        homeView.setOnTouchListener(new OnSwipeTouchListener(activity) {  //Handles swipes
            public void onSwipeTop() {
                //Toast.makeText(activity, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {    //Right swipe opens the drawer
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            public void onSwipeLeft() {
                //Toast.makeText(activity, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                //Toast.makeText(activity, "bottom", Toast.LENGTH_SHORT).show();
            }

        });


        ViewPager viewPager = activity.findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { //Handles fragment page change
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {  //if chat fragment is selected,the navigation drawer is closed
                if(position == 1) {
                    mDrawerLayout.closeDrawers();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return homeView;
    }






}
