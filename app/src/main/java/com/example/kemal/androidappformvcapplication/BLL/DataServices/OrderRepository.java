package com.example.kemal.androidappformvcapplication.BLL.DataServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.kemal.androidappformvcapplication.DAL.Database.DatabaseHelper;
import com.example.kemal.androidappformvcapplication.DAL.Tables.OrderDetailTable;
import com.example.kemal.androidappformvcapplication.DAL.Tables.OrderTable;
import com.example.kemal.androidappformvcapplication.Entities.Order;
import com.example.kemal.androidappformvcapplication.Entities.OrderDetail;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository
{
    private String TAG = "Order Repository: ";
    private DatabaseHelper dbHelper;
    private OrderTable orderTable;
    private OrderDetailTable orderDetailTable;

    public OrderRepository(Context context)
    {
        this.dbHelper = new DatabaseHelper(context);
        this.orderTable = new OrderTable();
        this.orderDetailTable = new OrderDetailTable();
    }

    public void insertJsonOrder(JSONObject jsonObject)
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records if there are any. It will drop the table but we just delete it for now.
        database.delete("'Order'", null, null);
        ContentValues contentValues = new ContentValues();

        try
        {
            int Id = jsonObject.getInt("id");
            String UserName = jsonObject.getString("userName");
            String FirstName = jsonObject.getString("firstName");
            String LastName = jsonObject.getString("lastName");
            String Address = jsonObject.getString("address");
            String Email = jsonObject.getString("email");
            String PhoneNumber = jsonObject.getString("phoneNumber");
            String CompanyName = jsonObject.getString("companyName");
            String ShippingName = jsonObject.getString("shippingName");
            String OrderDate = jsonObject.getString("orderDate");
            Double TotalPrice = jsonObject.getDouble("totalPrice");

            contentValues.put(orderTable.getColumnId(), Id);
            contentValues.put(orderTable.getColumnUserName(), UserName);
            contentValues.put(orderTable.getColumnFirstName(), FirstName);
            contentValues.put(orderTable.getColumnLastName(), LastName);
            contentValues.put(orderTable.getColumnAddress(), Address);
            contentValues.put(orderTable.getColumnEmail(), Email);
            contentValues.put(orderTable.getColumnPhoneNumber(), PhoneNumber);
            contentValues.put(orderTable.getColumnCompanyName(), CompanyName);
            contentValues.put(orderTable.getColumnShippingName(), ShippingName);
            contentValues.put(orderTable.getColumnOrderDate(), OrderDate);
            contentValues.put(orderTable.getColumnTotalPrice(), TotalPrice);

            //Insert the record
            database.insert("'Order'", null, contentValues);

            database.close();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void insertOrderDetail(OrderDetail orderDetail)
    {
        SQLiteDatabase database = dbHelper.open();
        /*//Delete the existing records if there are any. It will drop the table but we just delete it for now.
        database.delete("OrderDetail", null, null);*/
        ContentValues contentValues = new ContentValues();

        contentValues.put("OrderId", orderDetail.getOrderID());
        contentValues.put("ProductId", orderDetail.getProductID());
        contentValues.put("Quantity", orderDetail.getQuantity());
        contentValues.put("UnitPrice", orderDetail.getPrice());

        //Insert the record
        database.insert("OrderDetail", null, contentValues);

        String orderDetailData = String.format("OrderId: %d, ProductId: %d Quantity: %d, UnitPrice: %f", orderDetail.getOrderID(), orderDetail.getProductID(), orderDetail.getQuantity(), orderDetail.getPrice());
        Log.i(TAG, "Inserting " + orderDetailData);

        database.close();
    }

    public Order getOrder()
    {
        SQLiteDatabase database = dbHelper.open();
        List<Order> orderList = new ArrayList<>(1);

        String query = "SELECT * FROM '" + orderTable.getTableName() + "';";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);
        int orderIdIndex = cursor.getColumnIndex(orderTable.getColumnId());
        int orderUserNameIndex = cursor.getColumnIndex(orderTable.getColumnUserName());
        int orderFirstNameIndex = cursor.getColumnIndex(orderTable.getColumnFirstName());
        int orderLastNameIndex = cursor.getColumnIndex(orderTable.getColumnLastName());
        int orderAddressIndex = cursor.getColumnIndex(orderTable.getColumnAddress());
        int orderEmailIndex = cursor.getColumnIndex(orderTable.getColumnEmail());
        int orderPhoneNumberIndex = cursor.getColumnIndex(orderTable.getColumnPhoneNumber());
        int orderCompanyNameIndex = cursor.getColumnIndex(orderTable.getColumnCompanyName());
        int orderShippingNameIndex = cursor.getColumnIndex(orderTable.getColumnShippingName());
        int orderOrderDateIndex = cursor.getColumnIndex(orderTable.getColumnOrderDate());
        int orderTotalPriceIndex = cursor.getColumnIndex(orderTable.getColumnTotalPrice());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            orderList.add(new Order(
                    cursor.getInt(orderIdIndex),
                    cursor.getString(orderUserNameIndex),
                    cursor.getString(orderFirstNameIndex),
                    cursor.getString(orderLastNameIndex),
                    cursor.getString(orderAddressIndex),
                    cursor.getString(orderEmailIndex),
                    cursor.getString(orderPhoneNumberIndex),
                    cursor.getString(orderCompanyNameIndex),
                    cursor.getString(orderShippingNameIndex),
                    cursor.getString(orderOrderDateIndex),
                    cursor.getDouble(orderTotalPriceIndex)
            ));
        }
        orderList.get(0).setOrderDetails(getOrderDetailsByOrderId(orderList.get(0).getId()));
        database.close();
        //Return the order
        return orderList.get(0);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId)
    {
        SQLiteDatabase database = dbHelper.open();
        List<OrderDetail> orderDetailList = new ArrayList<>();

        String query = "SELECT * FROM '" + orderDetailTable.getTableName() + "' WHERE " + orderDetailTable.getColumnOrderId() + "= " + orderId + ";";
        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int orderDetailIdIndex = cursor.getColumnIndex(orderDetailTable.getColumnId());
        int orderDetailOrderIdIndex = cursor.getColumnIndex(orderDetailTable.getColumnOrderId());
        int orderDetailProductIdIndex = cursor.getColumnIndex(orderDetailTable.getColumnProductId());
        int orderDetailQuantityIndex = cursor.getColumnIndex(orderDetailTable.getColumnQuantity());
        int orderDetailUnitPriceIndex = cursor.getColumnIndex(orderDetailTable.getColumnUnitPrice());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            orderDetailList.add(new OrderDetail(
                    cursor.getInt(orderDetailIdIndex),
                    cursor.getInt(orderDetailOrderIdIndex),
                    cursor.getInt(orderDetailProductIdIndex),
                    cursor.getInt(orderDetailQuantityIndex),
                    cursor.getDouble(orderDetailUnitPriceIndex)
            ));
        }
        return orderDetailList;
    }
}
