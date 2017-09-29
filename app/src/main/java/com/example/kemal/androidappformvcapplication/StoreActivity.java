package com.example.kemal.androidappformvcapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CategoryRepository;
import com.example.kemal.androidappformvcapplication.BLL.StoreItemService;
import com.example.kemal.androidappformvcapplication.Entities.Category;
import com.example.kemal.androidappformvcapplication.View.CategoryAdapter;
import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity
{
    ListView listViewCategories;
    TextView textViewCategoryTitle;
    List<Category> categoryList = new ArrayList<>();
    ArrayAdapter<Category> categoryArrayAdapter;
    private StoreItemService storeItemService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        textViewCategoryTitle = (TextView) findViewById(R.id.textViewCategoryTitle);
        listViewCategories = (ListView) findViewById(R.id.listViewCategories);
        //This actions should be in the main activity's onCreate() method where the application starts from.
        storeItemService = new StoreItemService(StoreActivity.this);
        storeItemService.getItemsAndInsert();

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
