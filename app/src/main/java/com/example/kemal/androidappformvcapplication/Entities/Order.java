package com.example.kemal.androidappformvcapplication.Entities;

import java.util.List;

public class Order
{
    private int Id;
    private String UserName;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Email;
    private String PhoneNumber;
    private String CompanyName;
    private String ShippingName;
    private String OrderDate;
    private Double TotalPrice;
    private List<OrderDetail> OrderDetails;

    public Order() { }

    public Order(int id, String userName, String firstName,
                 String lastName, String address,
                 String email, String phoneNumber,
                 String companyName, String shippingName,
                 String orderDate, Double totalPrice)
    {
        Id = id;
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Email = email;
        PhoneNumber = phoneNumber;
        CompanyName = companyName;
        ShippingName = shippingName;
        OrderDate = orderDate;
        TotalPrice = totalPrice;
    }

    public Order(String userName, String firstName,
                 String lastName, String address, String email,
                 String phoneNumber, String companyName, String shippingName,
                 String orderDate, Double totalPrice)
    {
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Email = email;
        PhoneNumber = phoneNumber;
        CompanyName = companyName;
        ShippingName = shippingName;
        OrderDate = orderDate;
        TotalPrice = totalPrice;
    }

    public int getId() { return Id; }

    public String getUserName() { return UserName; }

    public String getFirstName() { return FirstName; }

    public String getLastName() { return LastName; }

    public String getAddress() { return Address; }

    public String getEmail() { return Email; }

    public String getPhoneNumber() { return PhoneNumber; }

    public String getCompanyName() { return CompanyName; }

    public String getShippingName() { return ShippingName; }

    public String getOrderDate() { return OrderDate; }

    public Double getTotalPrice() { return TotalPrice; }

    public List<OrderDetail> getOrderDetails() { return OrderDetails; }

    public void setId(int id) { Id = id; }

    public void setUserName(String userName) { UserName = userName; }

    public void setFirstName(String firstName) { FirstName = firstName; }

    public void setLastName(String lastName) { LastName = lastName; }

    public void setAddress(String address) { Address = address; }

    public void setEmail(String email) { Email = email; }

    public void setPhoneNumber(String phoneNumber) { PhoneNumber = phoneNumber; }

    public void setCompanyName(String companyName) { CompanyName = companyName; }

    public void setShippingName(String shippingName) { ShippingName = shippingName; }

    public void setOrderDate(String orderDate) { OrderDate = orderDate; }

    public void setTotalPrice(Double totalPrice) { TotalPrice = totalPrice; }

    public void setOrderDetails(List<OrderDetail> orderDetails) { OrderDetails = orderDetails; }
}
