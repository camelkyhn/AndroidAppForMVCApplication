package com.example.kemal.androidappformvcapplication.Models;

public class LoginModel
{
    private String Email;
    private String Password;

    public LoginModel(String email, String password)
    {
        this.Email = email;
        this.Password = password;
    }

    public String getEmail() { return Email; }

    public String getPassword() { return Password; }
}
