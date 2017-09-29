package com.example.kemal.androidappformvcapplication.ApiServices.Requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.kemal.androidappformvcapplication.Models.LoginModel;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest
{
    private static final String apiUrl = "http://192.168.1.120:10617/Token";
    private Map<String, String> headers;
    private LoginModel model;

    public LoginRequest(LoginModel loginModel, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.POST, apiUrl, listener, errorListener);
        this.model = loginModel;
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        String jsonBodyString = "username=" + model.getEmail() + "&" + "password=" + model.getPassword() + "&grant_type=password";
        return jsonBodyString.getBytes();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded");
        return headers;
    }

    @Override
    public String getBodyContentType()
    {
        return "application/x-www-form-urlencoded";
    }
}
