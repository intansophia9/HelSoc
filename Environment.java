package com.example.james.applogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GoogleApiAvailability;

import java.math.RoundingMode;

public class Environment extends AppCompatActivity {

    private CardView clickablemothernature, clickableriveroflife, clickablegabfoundation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);

        clickablemothernature = findViewById(R.id.mothernature);
        clickableriveroflife = findViewById(R.id.riveroflife);
        clickablegabfoundation = findViewById(R.id.gabfoundation);

        clickablemothernature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Environment.this, MotherNature.class));
            }
        });

        clickableriveroflife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Environment.this, RiverOfLife.class));
            }
        });

        clickablegabfoundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Environment.this, GabFoundation.class));
            }
        });

        /* Back Button */
        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    /*Back Button */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
