package com.example.hotel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;


public class HomeActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int RC_SIGN_IN = 1;

    private String uId;//user's id

    public String getUId(){
        return uId;
    }

    public void setCurrentItem (int item, boolean smoothScroll) { //scrolls from the current fragment to the item fragment
        viewPager.setCurrentItem(item, smoothScroll);
    }


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mFirebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        uId = intent.getStringExtra("userId"); //if it's a string you stored.


        setContentView(R.layout.activity_home);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        final DrawerLayout mDrawerLayout = this.findViewById(R.id.menu_drawer);

        NavigationView navigationView = this.findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(                   //Handle the drawer options
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        int id = menuItem.getItemId();

                        if (id == R.id.nav_like_facebook) { //opens an intent to the fb page
                            Intent fbIntent = newFacebookIntent(getPackageManager(),"https://www.facebook.com/antonysuites/");
                            startActivity(fbIntent);

                        } else if(id == R.id.nav_follow_instagram){ //opens an intent to the insta page
                            newInstagramIntent(getApplicationContext(), "http://instagram.com/_u/antony_suites");
                        }

                        else if(id == R.id.nav_logout_account){

                            AuthUI.getInstance()
                                    .signOut(getApplicationContext())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(),"Logged out",Toast.LENGTH_SHORT).show();
                                            HomeActivity.this.startActivity(new Intent(HomeActivity.this, LogInActivity.class));
                                        }
                                    });

                        }

                        else if(id == R.id.nav_delete_account){
                            AuthUI.getInstance()
                                    .delete(getApplicationContext())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(),"Account deleted",Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getApplicationContext(),"Deletion failed",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }


                        mDrawerLayout.closeDrawers();   // close drawer when item is tapped

                        return true;
                    }
                });


    }


}
