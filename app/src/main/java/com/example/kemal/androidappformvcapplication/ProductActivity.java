package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CategoryRepository;
import com.example.kemal.androidappformvcapplication.Entities.Category;

public class ProductActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        final TextView textViewProductName = (TextView) findViewById(R.id.textViewProductName);
        final TextView textViewProductCategoryName = (TextView) findViewById(R.id.textViewProductCategoryName);
        final TextView textViewProductDescription = (TextView) findViewById(R.id.textViewProductDescription);
        final TextView textViewProductStock = (TextView) findViewById(R.id.textViewProductStock);
        final TextView textViewProductPrice = (TextView) findViewById(R.id.textViewProductPrice);
        final TextView textViewBackToStore = (TextView) findViewById(R.id.textViewBackToStore);
        Button buttonAddToCart = (Button) findViewById(R.id.buttonAddToCart);

        Intent intent = getIntent();
        int Id = intent.getIntExtra("Id", 0);
        String ProductName = intent.getStringExtra("ProductName");
        String ProductDescription = intent.getStringExtra("ProductDescription");
        int Stock = intent.getIntExtra("Stock", 0);
        String ImagePath = intent.getStringExtra("ImagePath");
        Double Price = intent.getDoubleExtra("Price", 0);

        int CategoryId = intent.getIntExtra("CategoryId", 0);
        CategoryRepository categoryRepository = new CategoryRepository(ProductActivity.this);
        Category category = categoryRepository.findById(CategoryId);
        String CategoryName = category.getCategoryName();

        textViewProductName.setText(ProductName);
        String textCategoryName = "Category: " + CategoryName;
        textViewProductCategoryName.setText(textCategoryName);
        String textDescription = "Description: " + ProductDescription;
        textViewProductDescription.setText(textDescription);
        String textStock = "Stock: " + Stock + "";
        textViewProductStock.setText(textStock);
        String textPrice = "Price: " + Price + "";
        textViewProductPrice.setText(textPrice);

        buttonAddToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Add this product to user's shopping cart
            }
        });

        textViewBackToStore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //This will lead you to store again.
                Intent storeIntent = new Intent(ProductActivity.this, StoreActivity.class);
                ProductActivity.this.startActivity(storeIntent);
            }
        });
    }
}
