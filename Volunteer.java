package com.example.james.applogin;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Volunteer extends AppCompatActivity {

    private EditText feedbackInputField;
    private EditText nameInputField;
    private EditText emailInputField;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    startActivity(new Intent(Volunteer.this, HomePage.class));
                    finish();
                    Toast.makeText(Volunteer.this,"Home",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    setTitle("Volunteer");
                    Toast.makeText(Volunteer.this,"Volunteer",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    setTitle("Settings");
                    startActivity(new Intent(Volunteer.this, Edit.class));
                    Toast.makeText(Volunteer.this,"Settings",Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_nav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbar.setTitle("Help Society");
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        feedbackInputField = (EditText) findViewById(R.id.feedback_input);
        nameInputField = (EditText) findViewById(R.id.name_input);
        emailInputField = (EditText) findViewById(R.id.email_input);

        findViewById(R.id.submit_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validateInput();
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_volunteer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            new MaterialStyledDialog.Builder(this)
                    .setTitle("About")
                    .setDescription(R.string.dialog_content)
                    .setStyle(Style.HEADER_WITH_TITLE)
                    .setHeaderColor(R.color.colorPrimary)
                    .setPositiveText("Github")
                    .withDialogAnimation(true)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            String url = (getString(R.string.github_url));
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    })
                    .setNegativeText("Ok")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        }
                    })
                    .withDivider(true)
                    .setCancelable(false)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void validateInput() { // Validate text input

        if (feedbackInputField.getText().toString().trim().length() == 0 && nameInputField.getText().toString().trim().length() == 0 && emailInputField.getText().toString().trim().length() == 0) {
            feedbackInputField.setError("Enter your preferred activity!");
            nameInputField.setError("Enter if you're a member!");
            emailInputField.setError("Enter your email!");
            Toast.makeText(Volunteer.this, "Please fill in the required fields!", Toast.LENGTH_LONG).show();
        } else {
            validateEmail();
        }
    }

    private void validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(emailInputField.getText()).matches())
            sendData();
        else {
            emailInputField.setError("Enter a valid email!");
            Toast.makeText(Volunteer.this, "Please fill in a Valid Email Address!", Toast.LENGTH_LONG).show();
        }
    }

    private void sendData() { // Send feedback to Google Spreadsheet if text input is valid

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/") // Your spreadsheet URL
                .build();
        final HelpOut spreadsheetWebService = retrofit.create(HelpOut.class);

        String feedbackInput = feedbackInputField.getText().toString();
        String nameInput = nameInputField.getText().toString();
        String emailInput = emailInputField.getText().toString();

        Call<Void> feedbackCall = spreadsheetWebService.feedbackSend(feedbackInput, nameInput, emailInput);
        feedbackCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("XXX", "Submitted. " + response);
                Toast.makeText(Volunteer.this,"Your Volunteer is submitted!",Toast.LENGTH_LONG).show();
                // Clear all fields after submitting
                feedbackInputField.setText("");
                nameInputField.setText("");
                emailInputField.setText("");
                startActivity(new Intent(Volunteer.this, HomePage.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("XXX", "Failed", t);
                Toast.makeText(Volunteer.this,"There was an error!",Toast.LENGTH_LONG).show();
            }
        });
    }
}