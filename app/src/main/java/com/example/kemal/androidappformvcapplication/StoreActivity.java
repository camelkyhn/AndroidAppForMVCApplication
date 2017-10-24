package com.example.kemal.androidappformvcapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CategoryRepository;
import com.example.kemal.androidappformvcapplication.Entities.Category;
import com.example.kemal.androidappformvcapplication.View.CategoryAdapter;
import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends BaseActivity
{
    private ListView listViewCategories;
    private TextView textViewCategoryTitle;
    private List<Category> categoryList = new ArrayList<>();
    private ArrayAdapter<Category> categoryArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        setNavigationView(R.id.storeLayout, this);
        navigationView.setNavigationItemSelectedListener(this);

        textViewCategoryTitle = (TextView) findViewById(R.id.textViewCategoryTitle);
        listViewCategories = (ListView) findViewById(R.id.listViewCategories);

        populateListViewCategories();
    }

    public void populateListViewCategories()
    {
        CategoryRepository categoryRepository = new CategoryRepository(StoreActivity.this);
        categoryList = categoryRepository.getList();
        //Create a new adapter has type categoryArrayAdapter
        categoryArrayAdapter = new CategoryAdapter(StoreActivity.this, categoryList);
        //Now set the list views
        listViewCategories.setAdapter(categoryArrayAdapter);
    }
}
