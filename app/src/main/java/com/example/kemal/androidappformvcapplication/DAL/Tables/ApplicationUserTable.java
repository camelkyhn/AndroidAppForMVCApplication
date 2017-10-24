package com.example.kemal.androidappformvcapplication.DAL.Tables;

public class ApplicationUserTable
{
    private String TableName = "User";
    private String ColumnId = "Id";
    private String ColumnFirstName = "FirstName";
    private String ColumnLastName = "LastName";
    private String ColumnAddress = "Address";
    private String ColumnEmail = "Email";
    private String ColumnUserName = "UserName";

    public String createQuery()
    {
        return "CREATE TABLE '" + TableName + "' (" +
                ColumnId + " VARCHAR PRIMARY KEY, " +
                ColumnFirstName + " VARCHAR NOT NULL, " +
                ColumnLastName + " VARCHAR NOT NULL, " +
                ColumnAddress + " VARCHAR, " +
                ColumnEmail + " VARCHAR NOT NULL, " +
                ColumnUserName + " VARCHAR NOT NULL);";
    }

    public String dropQuery() { return "DROP TABLE IF EXISTS '" + TableName + "';"; }

    public String getTableName() { return TableName; }

    public String getColumnId() { return ColumnId; }

    public String getColumnFirstName() { return ColumnFirstName; }

    public String getColumnLastName() { return ColumnLastName; }

    public String getColumnAddress() { return ColumnAddress; }

    public String getColumnEmail() { return ColumnEmail; }

    public String getColumnUserName() { return ColumnUserName; }
}
