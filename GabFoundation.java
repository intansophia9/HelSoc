package com.example.james.applogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GabFoundation extends AppCompatActivity {
    private Button btnVolunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gab_foundation);

        btnVolunteer = findViewById(R.id.sign_gab);
        btnVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GabFoundation.this, Volunteer.class));
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
