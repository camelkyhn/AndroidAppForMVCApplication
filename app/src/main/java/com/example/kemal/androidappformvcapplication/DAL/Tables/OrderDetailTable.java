package com.example.kemal.androidappformvcapplication.DAL.Tables;

public class OrderDetailTable
{
    private String TableName = "OrderDetail";
    private String ColumnId = "Id";
    private String ColumnOrderId = "OrderId";
    private String ColumnProductId = "ProductId";
    private String ColumnQuantity = "Quantity";
    private String ColumnUnitPrice = "UnitPrice";

    public String createQuery()
    {
        return "CREATE TABLE '" + TableName + "' (" +
                ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnOrderId + " INTEGER NOT NULL, " +
                ColumnProductId + " INTEGER NOT NULL, " +
                ColumnQuantity + " INTEGER NOT NULL, " +
                ColumnUnitPrice + " DOUBLE NOT NULL);";
    }

    public String dropQuery()
    {
        return "DROP TABLE IF EXISTS '" + TableName + "';";
    }

    public String getTableName() { return TableName; }

    public String getColumnId() { return ColumnId; }

    public String getColumnOrderId() { return ColumnOrderId; }

    public String getColumnProductId() { return ColumnProductId; }

    public String getColumnQuantity() { return ColumnQuantity; }

    public String getColumnUnitPrice() { return ColumnUnitPrice; }
}
