package com.example.kemal.androidappformvcapplication.ApiServices.Requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class CartRequest extends StringRequest
{
    private final static String apiUrl = "http://192.168.1.120:10617/api/CartsApi/GetCart/?id=";
    private final static String TAG = "Cart Request: ";
    private Map<String, String> params, headers;
    private String CartId;

    public CartRequest(String cartId, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.GET, apiUrl + cartId, listener, errorListener);
        this.CartId = cartId;
    }

/*    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        params = new HashMap<>();
        params.put("id", this.CartId);
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded");
        return headers;
    }*/
}
