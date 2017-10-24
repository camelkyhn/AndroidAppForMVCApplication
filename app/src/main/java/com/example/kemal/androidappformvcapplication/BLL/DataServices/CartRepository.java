package com.example.kemal.androidappformvcapplication.BLL.DataServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.OrderRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.OrderListenerService;
import com.example.kemal.androidappformvcapplication.DAL.Database.DatabaseHelper;
import com.example.kemal.androidappformvcapplication.DAL.Tables.CartTable;
import com.example.kemal.androidappformvcapplication.Entities.Cart;
import com.example.kemal.androidappformvcapplication.Entities.Order;
import com.example.kemal.androidappformvcapplication.Entities.OrderDetail;
import com.example.kemal.androidappformvcapplication.Entities.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

//I assume that application will have only one cart everytime a user logs in.
//That means shopping cart will have always only one owner.
//When a user logs in, application will request to retrieve his/her shopping cart from server.
public class CartRepository
{
    private String TAG = "Cart Repository: ";
    private DatabaseHelper dbHelper;
    private Context Context;
    private CartTable cartTable;
    private OrderRepository orderRepository;

    public CartRepository(Context context)
    {
        this.Context = context;
        this.dbHelper = new DatabaseHelper(Context);
        this.cartTable = new CartTable();
        this.orderRepository = new OrderRepository(context);
    }

    public void addToCart(Product product)
    {
        SQLiteDatabase database = dbHelper.open();
        ContentValues contentValues = new ContentValues();

        Cart cartItem = findCartItemByProductId(product.getId());

        if (cartItem == null)
        {
            //Create a new cart item if no cart item exist
            contentValues.put(cartTable.getColumnCartId(), getCartOwner());
            contentValues.put(cartTable.getColumnItemCount(), 1);
            contentValues.put(cartTable.getColumnProductId(), product.getId());
            contentValues.put(cartTable.getColumnProductName(), product.getProductName());
            contentValues.put(cartTable.getColumnProductPrice(), product.getPrice());

            database.insert(cartTable.getTableName(), null, contentValues);
        }
        else
        {
            // If the item does exist in the cart,
            // then add one to the quantity
            contentValues.put(cartTable.getColumnItemCount(), cartItem.getItemCount() + 1);
            database.update(cartTable.getTableName(), contentValues, cartTable.getColumnProductId()+ "=?", new String[]{String.valueOf(product.getId())});
        }
        database.close();
    }

    public void removeFromCart(Product product)
    {
        SQLiteDatabase database = dbHelper.open();
        ContentValues contentValues = new ContentValues();

        Cart cartItem = findCartItemByProductId(product.getId());

        if (cartItem != null)
        {
            if (cartItem.getItemCount()>1)
            {
                contentValues.put(cartTable.getColumnItemCount(), cartItem.getItemCount() - 1);
                database.update(cartTable.getTableName(), contentValues, cartTable.getColumnProductId()+ "=?", new String[]{String.valueOf(product.getId())});
            }
            else
            {
                database.delete(cartTable.getTableName(), cartTable.getColumnProductId() + "=?", new String[]{ String.valueOf(product.getId())});
            }
        }
        database.close();
    }

    public void remove(Cart cartItem)
    {
        SQLiteDatabase database = dbHelper.open();

        if (cartItem != null)
        {
            database.delete(cartTable.getTableName(), cartTable.getColumnProductId() + "=?", new String[]{ String.valueOf(cartItem.getProductId())});
        }
        database.close();
    }

    public void emptyCart()
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records if there are any.
        database.delete("Cart", null, null);

        database.close();
    }

    public void createOrder(Order order)
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records
        database.delete("'Order'", "1", null);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<Cart> cartItems = getCarts();
        Double orderTotal = 0.0;
        // Iterate over the items in the cart,
        // adding the order details for each
        for (Cart cartItem : cartItems)
        {
            OrderDetail orderDetail = new OrderDetail(
                    order.getId(),
                    cartItem.getProductId(),
                    cartItem.getItemCount(),
                    cartItem.getPrice());
            //Save the order details to database.
            orderRepository.insertOrderDetail(orderDetail);
            orderDetailList.add(orderDetail);
            // Set the order total of the shopping cart
            orderTotal += (cartItem.getItemCount() * cartItem.getPrice());
        }
        order.setTotalPrice(orderTotal);
        order.setOrderDetails(orderDetailList);

        OrderListenerService orderListenerService = new OrderListenerService(this.Context);
        OrderRequest orderRequest = new OrderRequest(order, orderListenerService.getListener(), orderListenerService.getErrorListener());

        RequestQueue requestQueue = Volley.newRequestQueue(this.Context);
        requestQueue.add(orderRequest);

        emptyCart();
        database.close();
    }

    public void insertAll(JSONArray jsonArray) throws JSONException
    {
        SQLiteDatabase database = dbHelper.open();
        //Delete the existing records if there are any. It will drop the table but we just delete it for now.
        database.delete("Cart", null, null);
        ContentValues contentValues = new ContentValues();

        JSONObject jsonCart;
        try
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                jsonCart = jsonArray.getJSONObject(i);
                int Id = jsonCart.getInt("id");
                String CartId = jsonCart.getString("cartID");
                int ItemCount = jsonCart.getInt("itemCount");
                int ProductId = jsonCart.getInt("productID");
                String ProductName = jsonCart.getString("productName");
                Double ProductPrice = jsonCart.getDouble("productPrice");

                contentValues.put(cartTable.getColumnId(), Id);
                contentValues.put(cartTable.getColumnCartId(), CartId);
                contentValues.put(cartTable.getColumnItemCount(), ItemCount);
                contentValues.put(cartTable.getColumnProductId(), ProductId);
                contentValues.put(cartTable.getColumnProductName(), ProductName);
                contentValues.put(cartTable.getColumnProductPrice(), ProductPrice);

                //Insert the record
                database.insert(cartTable.getTableName(), null, contentValues);

                String cartData = String.format("Id: %d, CartId: %s, ItemCount: %d, ProductId: %d, ProductName: %s, ProductPrice: %f",
                        Id, CartId, ItemCount, ProductId, ProductName, ProductPrice);
                Log.i(TAG, "Inserting " + cartData);
            }
            database.close();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public List<Cart> getCarts()
    {
        SQLiteDatabase database = dbHelper.open();
        List<Cart> cartList = new ArrayList<>();

        String query = "SELECT * FROM '" + cartTable.getTableName() +"';";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int cartIdIndex = cursor.getColumnIndex(cartTable.getColumnId());
        int cartCartIdIndex = cursor.getColumnIndex(cartTable.getColumnCartId());
        int cartItemCountIndex = cursor.getColumnIndex(cartTable.getColumnItemCount());
        int cartProductIdIndex = cursor.getColumnIndex(cartTable.getColumnProductId());
        int cartProductNameIndex = cursor.getColumnIndex(cartTable.getColumnProductName());
        int cartProductPriceIndex = cursor.getColumnIndex(cartTable.getColumnProductPrice());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            cartList.add(new Cart(
                    cursor.getInt(cartIdIndex),
                    cursor.getString(cartCartIdIndex),
                    cursor.getInt(cartItemCountIndex),
                    cursor.getInt(cartProductIdIndex),
                    cursor.getString(cartProductNameIndex),
                    cursor.getDouble(cartProductPriceIndex)
            ));
        }
        database.close();
        //Return List
        return cartList;
    }

    public Double getTotalPrice()
    {
        Double totalPrice = 0.0;
        List<Cart> cartItems = getCarts();

        for (int i=0; i<cartItems.size(); i++)
        {
            totalPrice += cartItems.get(i).getPrice()*cartItems.get(i).getItemCount();
        }
        return totalPrice;
    }

    public Cart findCartItemByProductId(int productId)
    {
        SQLiteDatabase database = dbHelper.open();
        List<Cart> cartList = new ArrayList<>(1);

        String query = "SELECT * FROM '" + cartTable.getTableName() + "' WHERE " + cartTable.getColumnProductId() + " = " + productId + ";";

        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);

        int cartIdIndex = cursor.getColumnIndex(cartTable.getColumnId());
        int cartCartIdIndex = cursor.getColumnIndex(cartTable.getColumnCartId());
        int cartItemCountIndex = cursor.getColumnIndex(cartTable.getColumnItemCount());
        int cartProductIdIndex = cursor.getColumnIndex(cartTable.getColumnProductId());
        int cartProductNameIndex = cursor.getColumnIndex(cartTable.getColumnProductName());
        int cartProductPriceIndex = cursor.getColumnIndex(cartTable.getColumnProductPrice());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            cartList.add(new Cart(
                    cursor.getInt(cartIdIndex),
                    cursor.getString(cartCartIdIndex),
                    cursor.getInt(cartItemCountIndex),
                    cursor.getInt(cartProductIdIndex),
                    cursor.getString(cartProductNameIndex),
                    cursor.getDouble(cartProductPriceIndex)
            ));
        }
/*        database.close();*/
        //Return the cart
        if (cartList.isEmpty())
            return null;
        else
            return cartList.get(0);
    }

    public int getCartId(int productId)
    {
        SQLiteDatabase database = dbHelper.open();
        List<Integer> idList = new ArrayList<>(1);
        String query = "SELECT " + cartTable.getColumnId() + " FROM '" + cartTable.getTableName() + "' WHERE " + cartTable.getColumnProductId() + "= " + productId + ";";
        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);
        int cartIdIndex = cursor.getColumnIndex(cartTable.getColumnId());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            idList.add(cursor.getInt(cartIdIndex));
        }
        if (idList.isEmpty())
            return 0;
        else
            return idList.get(0);
    }

    public String getCartOwner()
    {
        SQLiteDatabase database = dbHelper.open();
        List<String> stringList = new ArrayList<>(1);
        String query = "SELECT * FROM '" +cartTable.getTableName() + "';";
        //Cursor point to location in your results
        Cursor cursor = database.rawQuery(query, null);
        int cartOwnerIndex = cursor.getColumnIndex(cartTable.getColumnCartId());

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            stringList.add(cursor.getString(cartOwnerIndex));
            if (stringList.get(0) != null)
                return stringList.get(0);
        }
        return null;
    }
}
