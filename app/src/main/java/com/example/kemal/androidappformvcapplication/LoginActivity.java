package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.LoginRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.LoginListenerService;
import com.example.kemal.androidappformvcapplication.Models.LoginModel;

public class LoginActivity extends AppCompatActivity
{
    private EditText email, password;
    private Button buttonLogin;
    private TextView registerLink, textViewStore;
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.LoginEmail);
        password = (EditText) findViewById(R.id.LoginPassword);
        buttonLogin = (Button) findViewById(R.id.ButtonLogin);
        registerLink = (TextView) findViewById(R.id.textViewRegister);
        textViewStore = (TextView) findViewById(R.id.textViewStore);

        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //This will leads you to register page.
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        textViewStore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //This will lead you to store
                Intent storeIntent = new Intent(LoginActivity.this, StoreActivity.class);
                LoginActivity.this.startActivity(storeIntent);
            }
        });

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
