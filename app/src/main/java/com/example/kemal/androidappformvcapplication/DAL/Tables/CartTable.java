package com.example.kemal.androidappformvcapplication.DAL.Tables;

public class CartTable
{
    private String TableName = "Cart";
    private String ColumnId = "Id";
    private String ColumnCartId = "CartId";
    private String ColumnItemCount = "ItemCount";
    private String ColumnProductId = "ProductId";
    private String ColumnProductName = "ProductName";
    private String ColumnProductPrice = "ProductPrice";

    public String createQuery()
    {
        return "CREATE TABLE '" + TableName + "' (" +
                ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnCartId + " VARCHAR, " +
                ColumnItemCount + " INTEGER NOT NULL, " +
                ColumnProductId + " INTEGER NOT NULL, " +
                ColumnProductName + " VARCHAR NOT NULL, " +
                ColumnProductPrice + " DOUBLE NOT NULL);";
    }

    public String dropQuery()
    {
        return "DROP TABLE IF EXISTS '" + TableName + "';";
    }

    public String getTableName() { return TableName; }

    public String getColumnId() { return ColumnId; }

    public String getColumnCartId() { return ColumnCartId; }

    public String getColumnItemCount() { return ColumnItemCount; }

    public String getColumnProductId() { return ColumnProductId; }

    public String getColumnProductName() { return ColumnProductName; }

    public String getColumnProductPrice () { return ColumnProductPrice; }
}
