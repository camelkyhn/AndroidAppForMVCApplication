package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CategoryRepository;
import com.example.kemal.androidappformvcapplication.Entities.Category;
import com.example.kemal.androidappformvcapplication.Entities.Product;

public class ProductActivity extends BaseActivity
{
    private TextView textViewProductName, textViewProductCategoryName, textViewProductDescription, textViewProductStock, textViewProductPrice;
    private Button buttonAddToCart;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setNavigationView(R.id.productLayout, this);
        navigationView.setNavigationItemSelectedListener(this);

        textViewProductName = (TextView) findViewById(R.id.textViewProductName);
        textViewProductCategoryName = (TextView) findViewById(R.id.textViewProductCategoryName);
        textViewProductDescription = (TextView) findViewById(R.id.textViewProductDescription);
        textViewProductStock = (TextView) findViewById(R.id.textViewProductStock);
        textViewProductPrice = (TextView) findViewById(R.id.textViewProductPrice);
        buttonAddToCart = (Button) findViewById(R.id.buttonAddToCart);

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

        product = new Product(Id, ProductName, ProductDescription, Stock, ImagePath, Price, CategoryId);

        textViewProductName.setText(ProductName);
        String textCategoryName = "Category: " + CategoryName;
        textViewProductCategoryName.setText(textCategoryName);
        String textDescription = "Description: " + ProductDescription;
        textViewProductDescription.setText(textDescription);
        String textStock = "Stock: " + Stock;
        textViewProductStock.setText(textStock);
        String textPrice = "Price: " + Price;
        textViewProductPrice.setText(textPrice);

        buttonAddToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Add this product to user's shopping cart
                cartRepository.addToCart(product);
                startActivity(new Intent(ProductActivity.this, CartActivity.class));
            }
        });
    }
}
