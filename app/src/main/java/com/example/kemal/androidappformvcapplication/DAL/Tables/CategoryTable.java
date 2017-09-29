package com.example.kemal.androidappformvcapplication.DAL.Tables;

public class CategoryTable
{
    private String TableName = "Category";
    private String ColumnId = "Id";
    private String ColumnCategoryName = "CategoryName";
    private String ColumnDescription = "Description";

    public String createQuery()
    {
        return "CREATE TABLE " + TableName + " (" +
                ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnCategoryName + " VARCHAR NOT NULL, " +
                ColumnDescription + " VARCHAR NOT NULL);";
    }

    public String dropQuery()
    {
        return "DROP TABLE IF EXISTS '" + TableName + "';";
    }

    public String getTableName() { return TableName; }

    public String getColumnId() { return ColumnId; }

    public String getColumnCategoryName() { return ColumnCategoryName; }

    public String getColumnDescription() { return ColumnDescription; }
}
