package com.example.kemal.androidappformvcapplication.BLL.DataServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.kemal.androidappformvcapplication.BLL.IRepository;
import com.example.kemal.androidappformvcapplication.DAL.Database.DatabaseHelper;
import com.example.kemal.androidappformvcapplication.DAL.Tables.CategoryTable;
import com.example.kemal.androidappformvcapplication.Entities.Category;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements IRepository<Category>
{
    private String TAG = "Category Repository: ";
    private DatabaseHelper dbHelper;
    private CategoryTable categoryTable;

    public CategoryRepository(Context context)
    {
        this.dbHelper = new DatabaseHelper(context);
        this.categoryTable = new CategoryTable();
    }

    @Override
    public void insertAll(JSONArray jsonArray) throws JSONException
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records if there are any. It will drop the table but we just delete it for now.
        database.delete("Category", null, null);
        ContentValues contentValues = new ContentValues();

        JSONObject jsonCategory;
        try
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                jsonCategory = jsonArray.getJSONObject(i);
                int Id = jsonCategory.getInt("id");
                String CategoryName = jsonCategory.getString("categoryName");
                String Description = jsonCategory.getString("description");

                contentValues.put("Id", Id);
                contentValues.put("CategoryName", CategoryName);
                contentValues.put("Description", Description);

                //Insert the record
                database.insert("Category", null, contentValues);

                String categoryData = String.format("Id: %d, Name: %s, Description: %s", Id, CategoryName, Description);
                Log.i(TAG, "inserting " + categoryData);
            }
            database.close();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> getList()
    {
        SQLiteDatabase database = dbHelper.open();

        List<Category> categoryList = new ArrayList<>();
        String[] columns = new String[] { categoryTable.getColumnId(), categoryTable.getColumnCategoryName(), categoryTable.getColumnDescription()};

        //Create the cursor to loop through the data.
        Cursor cursor = database.query(categoryTable.getTableName(), columns, null, null, null, null, null);

        int categoryIdIndex = cursor.getColumnIndex(categoryTable.getColumnId());
        int categoryNameIndex = cursor.getColumnIndex(categoryTable.getColumnCategoryName());
        int categoryDescriptionIndex = cursor.getColumnIndex(categoryTable.getColumnDescription());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            categoryList.add(new Category(cursor.getInt(categoryIdIndex), cursor.getString(categoryNameIndex), cursor.getString(categoryDescriptionIndex)));
        }

        database.close();
        //Return List
        return categoryList;
    }

    @Override
    public Category findById(int id)
    {
        SQLiteDatabase database = dbHelper.open();
        List<Category> categoryList = new ArrayList<>(1);

        String query = "SELECT * FROM " + categoryTable.getTableName() + " WHERE " + categoryTable.getColumnId() + "= " + id + ";";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int categoryIdIndex = cursor.getColumnIndex(categoryTable.getColumnId());
        int categoryNameIndex = cursor.getColumnIndex(categoryTable.getColumnCategoryName());
        int categoryDescriptionIndex = cursor.getColumnIndex(categoryTable.getColumnDescription());

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            categoryList.add(new Category(cursor.getInt(categoryIdIndex), cursor.getString(categoryNameIndex), cursor.getString(categoryDescriptionIndex)));
        }

        database.close();
        return categoryList.get(0);
    }
}
