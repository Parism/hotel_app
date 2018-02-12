package com.example.hotel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView signupTextLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
