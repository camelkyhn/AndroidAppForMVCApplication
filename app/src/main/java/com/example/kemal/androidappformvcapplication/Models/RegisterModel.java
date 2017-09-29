package com.example.kemal.androidappformvcapplication.Models;

public class RegisterModel
{
    private String Email;
    private String Password;
    private String ConfirmPassword;
    private String FirstName;
    private String LastName;

    public RegisterModel(String email, String password, String confirmPassword, String firstName, String lastName)
    {
        this.Email = email;
        this.Password = password;
        this.ConfirmPassword = confirmPassword;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public String getEmail() { return Email; }

    public String getPassword() { return Password; }

    public String getConfirmPassword() { return ConfirmPassword; }

    public String getFirstName() { return FirstName; }

    public String getLastName() { return LastName; }
}
