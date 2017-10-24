package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserAreaActivity extends BaseActivity
{
    private TextView textViewWelcomeMessage, textViewNames, textViewAddress, textViewEmail;
    private Button buttonShoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        setNavigationView(R.id.userAreaLayout, this);
        navigationView.setNavigationItemSelectedListener(this);

        textViewWelcomeMessage = (TextView) findViewById(R.id.textViewWelcome);
        textViewNames = (TextView) findViewById(R.id.textViewFNameLName);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        buttonShoppingCart = (Button) findViewById(R.id.buttonShoppingCart);

        buttonShoppingCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent cartIntent = new Intent(UserAreaActivity.this, CartActivity.class);
                UserAreaActivity.this.startActivity(cartIntent);
            }
        });


        String email = userService.getUser().getEmail();
        String firstName = userService.getUser().getFirstName();
        String lastName = userService.getUser().getLastName();
        String address = userService.getUser().getAddress();

        String welcomeMessage ="Welcome to your profile";
        textViewWelcomeMessage.setText(welcomeMessage);

        String stringAddress = "Address: " + address;
        textViewAddress.setText(stringAddress);

        String names = firstName + " " + lastName;
        textViewNames.setText(names);

        String emailText = "Signed in with " + email;
        textViewEmail.setText(emailText);
    }
}
