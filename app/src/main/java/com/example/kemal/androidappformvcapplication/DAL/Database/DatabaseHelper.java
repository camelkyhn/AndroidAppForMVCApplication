package com.example.kemal.androidappformvcapplication.DAL.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.kemal.androidappformvcapplication.DAL.Tables.ApplicationUserTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.CartTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.CategoryTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.OrderDetailTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.OrderTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.ProductTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.TokenTable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DatabaseName = "Project.db";
    private static final int DatabaseVersion = 1; //Need to change the version whenever you want to make change the structure
    private final String TAG = "Database Helper";

    private CategoryTable categoryTable;
    private ProductTable productTable;
    private TokenTable tokenTable;
    private ApplicationUserTable userTable;
    private CartTable cartTable;
    private OrderTable orderTable;
    private OrderDetailTable orderDetailTable;

    public DatabaseHelper(Context context)
    {
        super(context, DatabaseName, null, DatabaseVersion);
        this.categoryTable = new CategoryTable();
        this.productTable = new ProductTable();
        this.tokenTable = new TokenTable();
        this.userTable = new ApplicationUserTable();
        this.cartTable = new CartTable();
        this.orderTable = new OrderTable();
        this.orderDetailTable = new OrderDetailTable();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(categoryTable.createQuery());
        db.execSQL(productTable.createQuery());
        db.execSQL(tokenTable.createQuery());
        db.execSQL(userTable.createQuery());
        db.execSQL(cartTable.createQuery());
        db.execSQL(orderTable.createQuery());
        db.execSQL(orderDetailTable.createQuery());
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
        db.execSQL(cartTable.dropQuery());
        db.execSQL(orderTable.dropQuery());
        db.execSQL(orderDetailTable.dropQuery());

        Log.i(TAG, " creating new tables.");
        //Create new tables
        onCreate(db);
    }

    public SQLiteDatabase open()
    {
        return getWritableDatabase();
    }
}
