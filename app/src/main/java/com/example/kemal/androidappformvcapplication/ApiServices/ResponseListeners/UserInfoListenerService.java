package com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.ApplicationUserService;
import com.example.kemal.androidappformvcapplication.Entities.ApplicationUser;
import com.example.kemal.androidappformvcapplication.UserAreaActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoListenerService
{
    private Response.Listener<String> listener;
    private Response.ErrorListener errorListener;
    private JSONObject responseJson;

    public UserInfoListenerService(final Context context)
    {
        //Response listener to get response from GetUserInfo request.
        this.listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    responseJson = new JSONObject(response);
                    Boolean hasRegistered = responseJson.getBoolean("hasRegistered");
                    //If registered then he/she got an account. That means we will get the account information from response message.
                    if (hasRegistered)
                    {
                        String Id = responseJson.getString("id");
                        String email = responseJson.getString("email");
                        String firstName = responseJson.getString("firstName");
                        String lastName = responseJson.getString("lastName");
                        String address = responseJson.getString("address");

                        //Insert user information to database
                        ApplicationUserService userService = new ApplicationUserService(context);
                        ApplicationUser user = new ApplicationUser(Id, firstName, lastName, address, email, email);
                        userService.insert(user);

                        //We will bring the user to his/her profile with these informations.
                        context.startActivity(new Intent(context, UserAreaActivity.class));
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Failed retrieve the user information!")
                                .setNegativeButton("Try again.", null)
                                .create()
                                .show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };
        //Error listener to get error response if there is any from GetUserInfo request.
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

