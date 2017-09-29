package com.example.kemal.androidappformvcapplication.ApiServices.Requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

//This is a string request class which uses GET method to get the user information
//from web application with access token after the user logs in successfully.
public class UserInfoRequest extends StringRequest
{
    private static final String apiUrl = "http://192.168.1.120:10617/api/AccountsApi/GetUserInfo";
    private Map<String, String> headers;

    public UserInfoRequest(Response.Listener<String> listener, Response.ErrorListener errorListener, String token)
    {
        super(Method.GET, apiUrl, listener, errorListener);
        this.headers = new HashMap<>();
        headers.put("authorization","Bearer  " + token);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        return headers;
    }
}
