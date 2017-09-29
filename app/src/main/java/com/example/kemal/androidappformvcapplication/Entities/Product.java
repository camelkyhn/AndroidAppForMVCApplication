package com.example.kemal.androidappformvcapplication.Entities;

public class Product
{
    private int Id;
    private String ProductName;
    private String ProductDescription;
    private int Stock;
    private String ImagePath;
    private double Price;
    private int CategoryId;

    public Product(int id, String productName, String productDescription, int stock, String imagePath, double price, int categoryId) {
        this.Id = id;
        this.ProductName = productName;
        this.ProductDescription = productDescription;
        this.Stock = stock;
        this.ImagePath = imagePath;
        this.Price = price;
        this.CategoryId = categoryId;
    }

    public int getId() { return Id; }

    public String getProductName() { return ProductName; }

    public String getProductDescription() { return ProductDescription; }

    public int getStock() { return Stock; }

    public String getImagePath() { return ImagePath; }

    public double getPrice() { return Price; }

    public int getCategoryId() { return CategoryId; }
}
