package com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.CartRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.UserInfoRequest;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.TokenService;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginListenerService
{
    private Response.Listener<String> listener;
    private Response.ErrorListener errorListener;
    private JSONObject responseJson;

    public LoginListenerService(final Context context)
    {
        //Response listener to get the response from Login request.
        this.listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    responseJson = new JSONObject(response);
                    String access_token = responseJson.getString("access_token");
                    if (access_token != null) //If we got token then that means we are logged in successfully.
                    {
                        //Insert the token information to table.
                        TokenService tokenService = new TokenService(context);
                        tokenService.insert(responseJson);

                        //Get the cart if he/she got items in his/her shopping cart before.
                        CartListenersService cartListenersService = new CartListenersService(context);
                        CartRequest cartRequest = new CartRequest(responseJson.getString("userName"), cartListenersService.getListener(), cartListenersService.getErrorListener());


                        //We must create a request for user information to create his/her profile.
                        //And it will bring us to profile side if user information comes successful.
                        UserInfoListenerService service = new UserInfoListenerService(context);
                        UserInfoRequest userInfoRequest = new UserInfoRequest(service.getListener(), service.getErrorListener(), access_token);

                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        requestQueue.add(cartRequest);
                        requestQueue.add(userInfoRequest);
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Failed to login!")
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
        //Error listener to see the error message if there is any.
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

