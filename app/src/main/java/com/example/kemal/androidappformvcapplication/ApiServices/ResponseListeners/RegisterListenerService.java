package com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kemal.androidappformvcapplication.LoginActivity;

public class RegisterListenerService
{
    private Response.Listener<String> listener;
    private Response.ErrorListener errorListener;

    public RegisterListenerService(final Context context)
    {
        this.listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if (response.contains("true"))
                {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Failed to register!")
                            .setNegativeButton("Try again.", null)
                            .create()
                            .show();
                }
            }
        };
        this.errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context, error+"", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public Response.Listener<String> getListener() { return listener; }

    public Response.ErrorListener getErrorListener() { return errorListener; }
}
