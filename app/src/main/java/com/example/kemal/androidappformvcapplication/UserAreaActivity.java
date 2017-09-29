package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView textViewWelcomeMessage = (TextView) findViewById(R.id.textViewWelcome);
        final TextView textViewNames = (TextView) findViewById(R.id.textViewFNameLName);
        final TextView textViewEmail = (TextView) findViewById(R.id.textViewEmail);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");

        String welcomeMessage ="Welcome to your profile";
        textViewWelcomeMessage.setText(welcomeMessage);

        String names = firstName + " " + lastName;
        textViewNames.setText(names);

        String emailText = "Signed in with " + email;
        textViewEmail.setText(emailText);
    }
}
