package com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CartRepository;
import org.json.JSONArray;
import org.json.JSONException;

public class CartListenersService
{
    private Response.Listener<String> listener;
    private Response.ErrorListener errorListener;
    private JSONArray responseJson;

    public CartListenersService(final Context context)
    {
        this.listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    if (response != null)
                    {
                        responseJson = new JSONArray(response);
                        CartRepository cartRepository = new CartRepository(context);
                        cartRepository.insertAll(responseJson);
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("There is no cart or just failed to fetch cart items from server!")
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
