package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.LoginRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.LoginListenerService;
import com.example.kemal.androidappformvcapplication.Models.LoginModel;

public class LoginActivity extends BaseActivity
{
    private EditText email, password;
    private Button buttonLogin;
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //This actions should be in the main activity's onCreate() method where the application starts from.
        initializer.getStoreItemsAndInsert();
        if (isLoggedIn())
            this.startActivity(new Intent(this, StoreActivity.class));

        setNavigationView(R.id.loginLayout, this);
        navigationView.setNavigationItemSelectedListener(this);

        email = (EditText) findViewById(R.id.LoginEmail);
        password = (EditText) findViewById(R.id.LoginPassword);
        buttonLogin = (Button) findViewById(R.id.ButtonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Get email and password to loginModel object.
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();
                loginModel = new LoginModel(Email, Password);

                //Get the response listener and the response error listener to add into loginRequest object.
                LoginListenerService loginListeners = new LoginListenerService(LoginActivity.this);
                LoginRequest loginRequest = new LoginRequest(loginModel, loginListeners.getListener(), loginListeners.getErrorListener());

                //Now execute the login request.
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });
    }
}
