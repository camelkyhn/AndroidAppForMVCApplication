package com.example.kemal.androidappformvcapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.Entities.Order;
import java.util.Calendar;
import java.util.Date;

public class OrderActivity extends BaseActivity
{
    private Order order;
    private TextView firstName, lastName, address, email;
    private EditText phoneNumber, companyName, shippingName;
    private TextView TotalPrice;
    private Button buttonOrderNow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setNavigationView(R.id.orderLayout, this);
        navigationView.setNavigationItemSelectedListener(this);

        final String FirstName = "First Name: " + userService.getUser().getFirstName();
        final String LastName = "Last Name: " + userService.getUser().getLastName();
        final String Address = "Address: " + userService.getUser().getAddress();
        final String Email = "Email: " + userService.getUser().getEmail();
        firstName = (TextView) findViewById(R.id.textViewOrderFirstName);
        lastName = (TextView) findViewById(R.id.textViewOrderLastName);
        address = (TextView) findViewById(R.id.textViewOrderAddress);
        email = (TextView) findViewById(R.id.textViewOrderEmail);
        phoneNumber = (EditText) findViewById(R.id.editTextOrderPhoneNumber);
        companyName = (EditText) findViewById(R.id.editTextOrderCompanyName);
        shippingName = (EditText) findViewById(R.id.editTextOrderShippingName);
        buttonOrderNow = (Button) findViewById(R.id.buttonOrderNow);

        TotalPrice = (TextView) findViewById(R.id.textViewOrderTotalPrice);
        String totalPrice = "Total Price: " + cartRepository.getTotalPrice().toString();
        TotalPrice.setText(totalPrice);
        firstName.setText(FirstName);
        lastName.setText(LastName);
        address.setText(Address);
        email.setText(Email);

        buttonOrderNow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Order
                String PhoneNumber = phoneNumber.getText().toString();
                String CompanyName = companyName.getText().toString();
                String ShippingName = shippingName.getText().toString();
                Date Now = Calendar.getInstance().getTime();
                Double TotalPrice = cartRepository.getTotalPrice();

                order = new Order(Email, FirstName, LastName, Address, Email, PhoneNumber, CompanyName, ShippingName, Now.toString(), TotalPrice);

                cartRepository.createOrder(order);
            }
        });
    }
}
