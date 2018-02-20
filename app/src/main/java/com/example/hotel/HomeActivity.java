package com.example.hotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.homeToolbar); //toolbar creation
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);//menu icon creation
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu, menu);//menu buttons creation
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // if chat was selected
            case R.id.action_chat:
                Toast.makeText(this, "Chat selected", Toast.LENGTH_SHORT).show();
                break;
            default: //if menu was selected
                Toast.makeText(getApplicationContext(),"Menu selected",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
