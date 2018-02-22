package com.example.hotel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private TextView signupTextLink;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();

        loginButton = (LoginButton) findViewById(R.id.fb_login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Context context = getApplicationContext();
                CharSequence text = "GOOOOOOD";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


                Class destinationActivity = HomeActivity.class;
                Intent startSignupActivityIntent = new Intent(context, destinationActivity);
                startActivity(startSignupActivityIntent);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });



        final ImageView icAndroid = (ImageView) findViewById(R.id.hotelLogo);
        icAndroid.setImageResource(R.drawable.ic_hotel_logo);

        signupTextLink = (TextView) findViewById(R.id.signupText);

        signupTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupTextLink.setTextColor(Color.BLACK);
                Context context = MainActivity.this;
                Class destinationActivity = SignUpActivity.class;
                Intent startSignupActivityIntent = new Intent(context, destinationActivity);
                startActivity(startSignupActivityIntent);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
