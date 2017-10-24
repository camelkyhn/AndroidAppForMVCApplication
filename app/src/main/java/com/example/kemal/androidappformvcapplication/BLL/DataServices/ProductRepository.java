package com.example.kemal.androidappformvcapplication.BLL.DataServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.kemal.androidappformvcapplication.BLL.IRepository;
import com.example.kemal.androidappformvcapplication.DAL.Database.DatabaseHelper;
import com.example.kemal.androidappformvcapplication.DAL.Tables.ProductTable;
import com.example.kemal.androidappformvcapplication.Entities.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IRepository<Product>
{
    private String TAG = "Product Repository: ";
    private DatabaseHelper dbHelper;
    private ProductTable productTable;

    public ProductRepository(Context context)
    {
        this.dbHelper = new DatabaseHelper(context);
        this.productTable = new ProductTable();
    }

    @Override
    public void insertAll(JSONArray jsonArray) throws JSONException
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records if there are any. It will drop the table but we just delete it for now.
        database.delete("Product", null, null);
        ContentValues contentValues = new ContentValues();

        JSONObject jsonProduct;
        try
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                jsonProduct = jsonArray.getJSONObject(i);
                int Id = jsonProduct.getInt("id");
                String ProductName = jsonProduct.getString("productName");
                String ProductDescription = jsonProduct.getString("productDescription");
                int Stock = jsonProduct.getInt("stock");
                String ImagePath = jsonProduct.getString("imageURL");
                Double Price = jsonProduct.getDouble("unitPrice");
                int CategoryId = jsonProduct.getInt("categoryID");

                contentValues.put("Id", Id);
                contentValues.put("ProductName", ProductName);
                contentValues.put("ProductDescription", ProductDescription);
                contentValues.put("Stock", Stock);
                contentValues.put("ImagePath", ImagePath);
                contentValues.put("Price", Price);
                contentValues.put("CategoryId", CategoryId);

                //Insert the record
                database.insert("Product", null, contentValues);

                String productData = String.format("Id: %d, Name: %s, Description: %s, Stock: %d, ImagePath: %s, Price: %f, CategoryId: %d"
                        ,Id,ProductName,ProductDescription,Stock,ImagePath,Price,CategoryId);
                Log.i(TAG, "Inserting : " + productData);
            }
            database.close();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getList()
    {
        SQLiteDatabase database = dbHelper.open();

        List<Product> productList = new ArrayList<>();
        String[] columns = new String[]
                {
                        productTable.getColumnId(),
                        productTable.getColumnProductName(),
                        productTable.getColumnProductDescription(),
                        productTable.getColumnStock(),
                        productTable.getColumnImagePath(),
                        productTable.getColumnPrice(),
                        productTable.getColumnCategoryId()
                };

        //Create the cursor to loop through the data.
        Cursor cursor = database.query(productTable.getTableName(), columns, null, null, null, null, null);

        int productIdIndex = cursor.getColumnIndex(productTable.getColumnId());
        int productNameIndex = cursor.getColumnIndex(productTable.getColumnProductName());
        int productDescriptionIndex = cursor.getColumnIndex(productTable.getColumnProductDescription());
        int productStockIndex = cursor.getColumnIndex(productTable.getColumnStock());
        int productImagePathIndex = cursor.getColumnIndex(productTable.getColumnImagePath());
        int productPriceIndex = cursor.getColumnIndex(productTable.getColumnPrice());
        int productCategoryIdIndex = cursor.getColumnIndex(productTable.getColumnCategoryId());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            productList.add(new Product(
                    cursor.getInt(productIdIndex),
                    cursor.getString(productNameIndex),
                    cursor.getString(productDescriptionIndex),
                    cursor.getInt(productStockIndex),
                    cursor.getString(productImagePathIndex),
                    cursor.getDouble(productPriceIndex),
                    cursor.getInt(productCategoryIdIndex)));
        }

        database.close();
        //Return List
        return productList;
    }

    public List<Product> getListByCategoryId(int categoryId)
    {
        SQLiteDatabase database = dbHelper.open();

        List<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM " + productTable.getTableName() + " WHERE " + productTable.getColumnCategoryId() + "= " + categoryId + ";";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int productIdIndex = cursor.getColumnIndex(productTable.getColumnId());
        int productNameIndex = cursor.getColumnIndex(productTable.getColumnProductName());
        int productDescriptionIndex = cursor.getColumnIndex(productTable.getColumnProductDescription());
        int productStockIndex = cursor.getColumnIndex(productTable.getColumnStock());
        int productImagePathIndex = cursor.getColumnIndex(productTable.getColumnImagePath());
        int productPriceIndex = cursor.getColumnIndex(productTable.getColumnPrice());
        int productCategoryIdIndex = cursor.getColumnIndex(productTable.getColumnCategoryId());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            productList.add(new Product(
                    cursor.getInt(productIdIndex),
                    cursor.getString(productNameIndex),
                    cursor.getString(productDescriptionIndex),
                    cursor.getInt(productStockIndex),
                    cursor.getString(productImagePathIndex),
                    cursor.getDouble(productPriceIndex),
                    cursor.getInt(productCategoryIdIndex)));
        }
        database.close();
        //Return List
        return productList;
    }

    @Override
    public Product findById(int id)
    {
        SQLiteDatabase database = dbHelper.open();
        List<Product> productList = new ArrayList<>(1);

        String query = "SELECT * FROM " + productTable.getTableName() + " WHERE " + productTable.getColumnId() + "= " + id + ";";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int productIdIndex = cursor.getColumnIndex(productTable.getColumnId());
        int productNameIndex = cursor.getColumnIndex(productTable.getColumnProductName());
        int productDescriptionIndex = cursor.getColumnIndex(productTable.getColumnProductDescription());
        int productStockIndex = cursor.getColumnIndex(productTable.getColumnStock());
        int productImagePathIndex = cursor.getColumnIndex(productTable.getColumnImagePath());
        int productPriceIndex = cursor.getColumnIndex(productTable.getColumnPrice());
        int productCategoryIdIndex = cursor.getColumnIndex(productTable.getColumnCategoryId());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            productList.add(new Product(
                    cursor.getInt(productIdIndex),
                    cursor.getString(productNameIndex),
                    cursor.getString(productDescriptionIndex),
                    cursor.getInt(productStockIndex),
                    cursor.getString(productImagePathIndex),
                    cursor.getDouble(productPriceIndex),
                    cursor.getInt(productCategoryIdIndex)
            ));
        }
        database.close();
        return productList.get(0);
    }
}
