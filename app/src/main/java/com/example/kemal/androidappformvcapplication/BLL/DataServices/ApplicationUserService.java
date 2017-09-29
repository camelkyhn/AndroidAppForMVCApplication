package com.example.kemal.androidappformvcapplication.BLL.DataServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.kemal.androidappformvcapplication.DAL.Database.DatabaseHelper;
import com.example.kemal.androidappformvcapplication.DAL.Tables.ApplicationUserTable;
import com.example.kemal.androidappformvcapplication.Entities.ApplicationUser;

public class ApplicationUserService
{
    private String TAG = "User Service: ";
    private DatabaseHelper dbHelper;
    private ApplicationUserTable userTable;

    public ApplicationUserService(Context context)
    {
        this.dbHelper = new DatabaseHelper(context);
        this.userTable = new ApplicationUserTable();
    }

    public void insert(ApplicationUser user)
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records if there are any. It will drop the table but we just delete it for now.
        database.delete(userTable.getTableName(), null, null);
        ContentValues contentValues = new ContentValues();

        contentValues.put(userTable.getColumnId(), user.getId());
        contentValues.put(userTable.getColumnFirstName(), user.getFirstName());
        contentValues.put(userTable.getColumnLastName(), user.getLastName());
        contentValues.put(userTable.getColumnAddress(), user.getAddress());
        contentValues.put(userTable.getColumnEmail(), user.getEmail());
        contentValues.put(userTable.getColumnUserName(), user.getEmail());

        //Insert the record
        database.insert(userTable.getTableName(), null, contentValues);
        Log.i(TAG, "inserted the user.");
        database.close();
    }

    public ApplicationUser findUserByUserName(String userName)
    {
        SQLiteDatabase database = dbHelper.open();

        String query = "SELECT * FROM " + userTable.getTableName() + " WHERE " + userTable.getColumnUserName() + "= " + userName + ";";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int IdIndex = cursor.getColumnIndex(userTable.getColumnId());
        int FirstNameIndex = cursor.getColumnIndex(userTable.getColumnFirstName());
        int LastNameIndex = cursor.getColumnIndex(userTable.getColumnLastName());
        int AddressIndex = cursor.getColumnIndex(userTable.getColumnAddress());
        int EmailIndex = cursor.getColumnIndex(userTable.getColumnEmail());
        int UserNameIndex = cursor.getColumnIndex(userTable.getColumnUserName());
        ApplicationUser user = new ApplicationUser(
                cursor.getString(IdIndex),
                cursor.getString(FirstNameIndex),
                cursor.getString(LastNameIndex),
                cursor.getString(AddressIndex),
                cursor.getString(EmailIndex),
                cursor.getString(UserNameIndex)
        );

        database.close();
        return user;
    }
}
