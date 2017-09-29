package com.example.kemal.androidappformvcapplication.ApiServices.Requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.kemal.androidappformvcapplication.Models.RegisterModel;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest
{
    private static final String apiUrl = "http://192.168.1.120:10617/api/AccountsApi/Register";
    private Map<String, String> params;
    private RegisterModel model;

    public RegisterRequest(RegisterModel registerModel, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.POST, apiUrl, listener, errorListener);
        this.model = registerModel;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        params = new HashMap<>();
        params.put("Email", model.getEmail());
        params.put("Password", model.getPassword());
        params.put("ConfirmPassword", model.getConfirmPassword());
        params.put("FirstName", model.getFirstName());
        params.put("LastName", model.getLastName());
        return params;
    }
}
