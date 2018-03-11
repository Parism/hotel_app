package com.example.hotel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailField, passwordField;
    private String email, password;
    private Button signupButton;
    private FirebaseAuth mAuth;
    private static final String TAG = "emailPass";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailField = (EditText) findViewById(R.id.email_input);
        passwordField = (EditText) findViewById(R.id.password_input);
        signupButton = (Button) findViewById(R.id.signup_button);


// ...
        mAuth = FirebaseAuth.getInstance();



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });


    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            Class destinationActivity = HomeActivity.class;
            Intent startHomeActivityIntent = new Intent(getApplicationContext(), destinationActivity);
            startActivity(startHomeActivityIntent);
        }
    }

    private void signup(){
        email = emailField.getText().toString();
        password = passwordField.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        Log.w(TAG, "mpikaaa");
                        // ...
                    }
                });
    }
}
