package com.example.kemal.androidappformvcapplication.Entities;

public class Cart
{
    private int Id;
    private String CartId;
    private int ItemCount;
    private int ProductId;
    private String ProductName;
    private Double ProductPrice;

    public Cart(int id, String cartId, int itemCount, int productId, String productName, Double price)
    {
        Id = id;
        CartId = cartId;
        ItemCount = itemCount;
        ProductId = productId;
        ProductName = productName;
        ProductPrice = price;
    }

    public int getId() { return Id; }

    public String getCartId() { return CartId; }

    public int getItemCount() { return ItemCount; }

    public int getProductId() { return ProductId; }

    public String getProductName() { return ProductName; }

    public Double getPrice() { return ProductPrice; }

    public void setId(int id) { Id = id; }

    public void setCartId(String cartId) { CartId = cartId; }

    public void setItemCount(int itemCount) { ItemCount = itemCount; }

    public void setProductId(int productId) { ProductId = productId; }

    public void setProductName(String productName) { ProductName = productName; }

    public void setProductPrice(Double productPrice) { ProductPrice = productPrice; }
}
