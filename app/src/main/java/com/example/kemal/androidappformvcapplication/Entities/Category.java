package com.example.kemal.androidappformvcapplication.Entities;

public class Category
{
    private int Id;
    private String CategoryName;
    private String Description;

    public Category(int id, String categoryName, String description)
    {
        this.Id = id;
        this.CategoryName = categoryName;
        this.Description = description;
    }

    public int getId() { return Id; }

    public String getCategoryName() { return CategoryName; }

    public String getDescription() { return Description; }
}
