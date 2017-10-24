package com.example.kemal.androidappformvcapplication.DAL.Tables;

public class OrderTable
{
    private String TableName = "Order";
    private String ColumnId = "Id";
    private String ColumnUserName = "UserName";
    private String ColumnFirstName = "FirstName";
    private String ColumnLastName = "LastName";
    private String ColumnAddress = "Address";
    private String ColumnEmail = "Email";
    private String ColumnPhoneNumber = "PhoneNumber";
    private String ColumnCompanyName = "CompanyName";
    private String ColumnShippingName = "ShippingName";
    private String ColumnOrderDate = "OrderDate";
    private String ColumnTotalPrice = "TotalPrice";

    public String createQuery()
    {
        return "CREATE TABLE '" + TableName + "' (" +
                ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnUserName + " VARCHAR NOT NULL, " +
                ColumnFirstName + " VARCHAR NOT NULL, " +
                ColumnLastName + " VARCHAR NOT NULL, " +
                ColumnAddress + " TEXT, " + //Probably address is not returned from server.
                ColumnEmail + " VARCHAR NOT NULL, " +
                ColumnPhoneNumber + " VARCHAR NOT NULL, " +
                ColumnCompanyName + " VARCHAR NOT NULL, " +
                ColumnShippingName + " VARCHAR NOT NULL, " +
                ColumnOrderDate + " VARCHAR NOT NULL, " +
                ColumnTotalPrice + " DOUBLE NOT NULL);";
    }

    public String dropQuery()
    {
        return "DROP TABLE IF EXISTS '" + TableName + "';";
    }

    public String getTableName() { return TableName; }

    public String getColumnId() { return ColumnId; }

    public String getColumnUserName() { return ColumnUserName; }

    public String getColumnFirstName() { return ColumnFirstName; }

    public String getColumnLastName() { return ColumnLastName; }

    public String getColumnAddress() { return ColumnShippingName; }

    public String getColumnEmail() { return ColumnEmail; }

    public String getColumnPhoneNumber() { return ColumnPhoneNumber; }

    public String getColumnCompanyName() { return ColumnCompanyName; }

    public String getColumnShippingName() { return ColumnShippingName; }

    public String getColumnOrderDate() { return ColumnOrderDate; }

    public String getColumnTotalPrice() { return ColumnTotalPrice; }
}
