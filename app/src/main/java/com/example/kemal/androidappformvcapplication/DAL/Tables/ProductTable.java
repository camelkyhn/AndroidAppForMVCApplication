package com.example.kemal.androidappformvcapplication.DAL.Tables;

public class ProductTable
{
    private String TableName = "Product";
    private String ColumnId = "Id";
    private String ColumnProductName = "ProductName";
    private String ColumnProductDescription = "ProductDescription";
    private String ColumnStock = "Stock";
    private String ColumnImagePath = "ImagePath";
    private String ColumnPrice = "Price";
    private String ColumnCategoryId = "CategoryId";

    public String createQuery()
    {
        return "CREATE TABLE '" + TableName + "' (" +
                ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnProductName + " VARCHAR NOT NULL, " +
                ColumnProductDescription + " VARCHAR NOT NULL, " +
                ColumnStock + " INTEGER NOT NULL, " +
                ColumnImagePath + " VARCHAR NOT NULL, " +
                ColumnPrice + " DOUBLE NOT NULL, " +
                ColumnCategoryId + " INTEGER NOT NULL);";
    }

    public String dropQuery() { return "DROP TABLE IF EXISTS '" + TableName + "';"; }

    public String getTableName() { return TableName; }

    public String getColumnId() { return ColumnId; }

    public String getColumnProductName() { return ColumnProductName; }

    public String getColumnProductDescription() { return ColumnProductDescription; }

    public String getColumnStock() { return ColumnStock; }

    public String getColumnImagePath() { return ColumnImagePath; }

    public String getColumnPrice() { return ColumnPrice; }

    public String getColumnCategoryId() { return ColumnCategoryId; }
}
