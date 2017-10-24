package com.example.kemal.androidappformvcapplication.BLL.DataServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.kemal.androidappformvcapplication.DAL.Database.DatabaseHelper;
import com.example.kemal.androidappformvcapplication.DAL.Tables.TokenTable;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class TokenService
{
    private String TAG = "Token Service: ";
    private DatabaseHelper dbHelper;
    private TokenTable tokenTable;

    public TokenService(Context context)
    {
        this.dbHelper = new DatabaseHelper(context);
        this.tokenTable = new TokenTable();
    }

    public void insert(JSONObject jsonObject)
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records if there are any. It will drop the table but we just delete it for now.
        database.delete(tokenTable.getTableName(), null, null);
        ContentValues contentValues = new ContentValues();

        try
        {
            String access_token = jsonObject.getString("access_token");
            String token_type = jsonObject.getString("token_type");
            int expires_in = jsonObject.getInt("expires_in");
            String userName = jsonObject.getString("userName");
            String issued = jsonObject.getString(".issued");
            String expires = jsonObject.getString(".expires");

            contentValues.put("access_token", access_token);
            contentValues.put("token_type", token_type);
            contentValues.put("expires_in", expires_in);
            contentValues.put("userName", userName);
            contentValues.put("issued", issued);
            contentValues.put("expires", expires);

            //Insert the record
            database.insert(tokenTable.getTableName(), null, contentValues);
            Log.i(TAG, "inserted a token.");
            database.close();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public String findTokenByUserName(String userName)
    {
        SQLiteDatabase database = dbHelper.open();
        List<String> stringList = new ArrayList<>(1);
        String query = "SELECT * FROM " + tokenTable.getTableName() + " WHERE " + tokenTable.getUserName() + "= " + userName + ";";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int access_tokenIndex = cursor.getColumnIndex(tokenTable.getAccess_token());
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            stringList.add(cursor.getString(access_tokenIndex));
        }
        database.close();
        if (stringList.isEmpty())
        {
            return null;
        }
        else
        {
            return stringList.get(0);
        }
    }

    public String getToken()
    {
        SQLiteDatabase database = dbHelper.open();
        List<String> stringList = new ArrayList<>(1);
        String query = "SELECT * FROM '" + tokenTable.getTableName() + "';";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int access_tokenIndex = cursor.getColumnIndex(tokenTable.getAccess_token());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            stringList.add(cursor.getString(access_tokenIndex));
        }

        database.close();
        if (stringList.isEmpty())
        {
            return null;
        }
        else
        {
            return stringList.get(0);
        }
    }
}
