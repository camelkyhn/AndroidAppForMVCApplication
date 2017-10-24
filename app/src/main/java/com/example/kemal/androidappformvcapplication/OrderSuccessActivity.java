package com.example.kemal.androidappformvcapplication;

import android.os.Bundle;
import android.widget.TextView;

public class OrderSuccessActivity extends BaseActivity
{
    private TextView textViewOrderResult, textViewOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        setNavigationView(R.id.orderSuccessLayout, this);
        navigationView.setNavigationItemSelectedListener(this);

        textViewOrderResult = (TextView) findViewById(R.id.textViewOrderResult);
        textViewOrderId = (TextView) findViewById(R.id.textViewOrderId);

        String OrderId = "Your order id number is: " + orderRepository.getOrder().getId();
        textViewOrderId.setText(OrderId);
    }
}
