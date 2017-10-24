package com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.OrderRepository;
import com.example.kemal.androidappformvcapplication.OrderSuccessActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderListenerService
{
    private Response.Listener<String> listener;
    private Response.ErrorListener errorListener;
    private JSONObject responseJson;

    public OrderListenerService(final Context context)
    {
        this.listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    responseJson = new JSONObject(response);
                    OrderRepository orderRepository = new OrderRepository(context);
                    orderRepository.insertJsonOrder(responseJson);

                    Intent intent = new Intent(context, OrderSuccessActivity.class);
                    context.startActivity(intent);
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
                //Failed to create.
                Toast.makeText(context, error+"", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public Response.Listener<String> getListener() { return listener; }

    public Response.ErrorListener getErrorListener() { return errorListener; }
}
