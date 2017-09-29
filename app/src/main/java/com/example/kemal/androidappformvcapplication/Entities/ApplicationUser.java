package com.example.kemal.androidappformvcapplication.Entities;

public class ApplicationUser
{
    private String Id;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Email;
    private String UserName;

    public ApplicationUser(String id, String firstName, String lastName, String address, String email, String userName)
    {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Email = email;
        UserName = userName;
    }

    public String getId() { return Id;}

    public String getFirstName() { return FirstName; }

    public String getLastName() { return LastName; }

    public String getAddress() { return Address; }

    public String getEmail() { return Email; }

    public String getUserName() { return UserName; }
}
