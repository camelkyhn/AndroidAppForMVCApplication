package com.example.kemal.androidappformvcapplication.DAL.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.kemal.androidappformvcapplication.DAL.Tables.ApplicationUserTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.CategoryTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.ProductTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.TokenTable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static String DatabaseName = "Project.db";
    private static int DatabaseVersion = 1; //Need to change the version whenever you want to make change the structure
    private String TAG = "Database Helper";

    private CategoryTable categoryTable;
    private ProductTable productTable;
    private TokenTable tokenTable;
    private ApplicationUserTable userTable;

    public DatabaseHelper(Context context)
    {
        super(context, DatabaseName, null, DatabaseVersion);
        this.categoryTable = new CategoryTable();
        this.productTable = new ProductTable();
        this.tokenTable = new TokenTable();
        this.userTable = new ApplicationUserTable();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(categoryTable.createQuery());
        db.execSQL(productTable.createQuery());
        db.execSQL(tokenTable.createQuery());
        db.execSQL(userTable.createQuery());
        Log.i(TAG, " created tables.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //On upgrade drop older tables
        Log.i(TAG, " dropping old tables.");
        db.execSQL(categoryTable.dropQuery());
        db.execSQL(productTable.dropQuery());
        db.execSQL(tokenTable.dropQuery());
        db.execSQL(userTable.dropQuery());

        Log.i(TAG, " creating new tables.");
        //Create new tables
        onCreate(db);
    }

    public SQLiteDatabase open()
    {
        SQLiteDatabase db = getWritableDatabase();
        return db;
    }
}
