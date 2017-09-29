package com.example.kemal.androidappformvcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.ProductRepository;
import com.example.kemal.androidappformvcapplication.Entities.Product;
import com.example.kemal.androidappformvcapplication.View.ProductAdapter;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity
{
    ListView listViewProducts;
    TextView textViewProductCategoryTitle;
    List<Product> productList = new ArrayList<>();
    ArrayAdapter<Product> productArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        textViewProductCategoryTitle = (TextView) findViewById(R.id.textViewProductsCategoryName);
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("Id", 0);
        String categoryName = intent.getStringExtra("CategoryName");
        textViewProductCategoryTitle.setText(categoryName);
        String description = intent.getStringExtra("Description");

        populateListViewProducts(categoryId);
    }

    public void populateListViewProducts(int categoryId)
    {
        ProductRepository productRepository = new ProductRepository(ProductListActivity.this);
        productList = productRepository.getListByCategoryId(categoryId);
        //Create a new adapter has type productArrayAdapter
        productArrayAdapter = new ProductAdapter(ProductListActivity.this, productList);
        //Now set the list views
        listViewProducts.setAdapter(productArrayAdapter);
    }
}
