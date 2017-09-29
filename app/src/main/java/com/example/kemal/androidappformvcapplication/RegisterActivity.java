package com.example.kemal.androidappformvcapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.RegisterRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.RegisterListenerService;
import com.example.kemal.androidappformvcapplication.Models.RegisterModel;

public class RegisterActivity extends AppCompatActivity
{
    private EditText email, password, confirmPassword, firstName, lastName;
    private Button buttonRegister;
    private RegisterModel registerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.RegisterEmail);
        password = (EditText) findViewById(R.id.RegisterPassword);
        confirmPassword = (EditText) findViewById(R.id.RegisterConfirmPassword);
        firstName = (EditText) findViewById(R.id.FirstName);
        lastName = (EditText) findViewById(R.id.LastName);
        buttonRegister = (Button) findViewById(R.id.ButtonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Get information to build register model.
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String ConfirmPassword = confirmPassword.getText().toString();
                String FirstName = firstName.getText().toString();
                String LastName = lastName.getText().toString();
                registerModel = new RegisterModel(Email, Password, ConfirmPassword, FirstName, LastName);

                //Get the response listener and the response error listener to add into registerRequest object.
                RegisterListenerService listenerService = new RegisterListenerService(RegisterActivity.this);
                RegisterRequest registerRequest = new RegisterRequest(registerModel, listenerService.getListener(), listenerService.getErrorListener());

                //Now execute the login request.
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
