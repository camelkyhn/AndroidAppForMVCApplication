package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kemal.androidappformvcapplication.Entities.Cart;
import com.example.kemal.androidappformvcapplication.View.CartAdapter;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity
{
    private ListView listViewCartProducts;
    private TextView textViewShoppingCart;
    private Button buttonOrder;
    private List<Cart> cartProductList = new ArrayList<>();
    private CartAdapter cartProductArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setNavigationView(R.id.cartLayout, this);
        navigationView.setNavigationItemSelectedListener(this);

        listViewCartProducts = (ListView) findViewById(R.id.listViewCartProducts);
        populateListViewCartProducts();
        textViewShoppingCart = (TextView) findViewById(R.id.textViewShoppingCart);
        buttonOrder = (Button) findViewById(R.id.buttonOrder);

        buttonOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (userService.getUser() == null || tokenService.getToken() == null)
                {
                    Toast.makeText(CartActivity.this, "Not logged in or you don't have the permission to do it!", Toast.LENGTH_SHORT).show();
                }
                else if (cartRepository.getTotalPrice()<1)
                {
                    Toast.makeText(CartActivity.this, "Your shopping cart is empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Go order now!..
                    Intent orderIntent = new Intent(CartActivity.this, OrderActivity.class);
                    CartActivity.this.startActivity(orderIntent);
                }
            }
        });
    }

    private void populateListViewCartProducts()
    {
        cartProductList = cartRepository.getCarts();
        //Create a new adapter has type Cart Array adapter
        cartProductArrayAdapter = new CartAdapter(CartActivity.this, cartProductList);
        //Now set the list view
        listViewCartProducts.setAdapter(cartProductArrayAdapter);
        cartProductArrayAdapter.notifyDataSetChanged();
    }
}
