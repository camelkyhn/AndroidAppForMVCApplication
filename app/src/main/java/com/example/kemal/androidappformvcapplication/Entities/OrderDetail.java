package com.example.kemal.androidappformvcapplication.Entities;

public class OrderDetail
{
    private int Id;
    private int OrderID;
    private int ProductID;
    private int Quantity;
    private Double Price;

    public OrderDetail(int id, int orderID, int productID, int quantity, Double price)
    {
        Id = id;
        OrderID = orderID;
        ProductID = productID;
        Quantity = quantity;
        Price = price;
    }

    public OrderDetail(int orderID, int productID, int quantity, Double price)
    {
        OrderID = orderID;
        ProductID = productID;
        Quantity = quantity;
        Price = price;
    }

    public int getId() { return Id; }

    public int getOrderID() { return OrderID; }

    public int getProductID() { return ProductID; }

    public int getQuantity() { return Quantity; }

    public Double getPrice() { return Price; }
}
