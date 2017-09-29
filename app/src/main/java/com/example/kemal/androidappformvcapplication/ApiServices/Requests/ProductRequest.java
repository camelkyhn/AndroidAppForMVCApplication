package com.example.kemal.androidappformvcapplication.ApiServices.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class ProductRequest extends StringRequest
{
    private static final String apiUrl = "http://192.168.1.120:10617/api/ProductsApi/GetProducts";

    public ProductRequest(Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.GET, apiUrl, listener, errorListener);
    }
}
