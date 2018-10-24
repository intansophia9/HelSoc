package com.example.james.applogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Social extends AppCompatActivity {

    private CardView clickablesolshealth, clickableNGOHub, clickablecitylauncher, clickablevolunteerteacher,
            clickablepsychologist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        clickablesolshealth = findViewById(R.id.solshealth);
        clickableNGOHub = findViewById(R.id.NGOHub);
        clickablecitylauncher = findViewById(R.id.citylauncher);
        clickablevolunteerteacher = findViewById(R.id.volunteerteacher);
        clickablepsychologist = findViewById(R.id.psychologist);


        clickablesolshealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Social.this, sols.class));
            }
        });

        clickableNGOHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Social.this, NGO.class));
            }
        });

        clickablecitylauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Social.this, Citylauncher.class));
            }
        });

        clickablevolunteerteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Social.this, Volunteerteacher.class));
            }
        });

        clickablepsychologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Social.this, Psychologist.class));
            }
        });


    }

}
