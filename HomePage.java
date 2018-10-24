package com.example.james.applogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {


    private FrameLayout mMainFrame;

    private CardView clickableSoc,clickableEnv, clickableAni, clickableOldppl;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    Toast.makeText(HomePage.this,"Home",Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.navigation_dashboard:
                    setTitle("Volunteer");
                    startActivity(new Intent(HomePage.this, Volunteer.class));
                    finish();
                    Toast.makeText(HomePage.this,"Volunteer",Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.navigation_notifications:
                    setTitle("Settings");
                    startActivity(new Intent(HomePage.this, Edit.class));
                    Toast.makeText(HomePage.this,"Settings",Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);


        clickableSoc = findViewById(R.id.socialclick);
        clickableEnv = findViewById(R.id.environmentclick);
        clickableAni = findViewById(R.id.animalclick);
        clickableOldppl = findViewById(R.id.oldpplclick);


        clickableSoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Social.class));
            }
        });

        clickableEnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Environment.class));
            }
        });

        clickableAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Animal.class));
            }
        });

        clickableOldppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, OldPeople.class));
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_nav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


}
