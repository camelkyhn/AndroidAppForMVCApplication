package com.example.kemal.androidappformvcapplication.BLL;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.CategoryRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.ProductRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.CategoryListenerService;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.ProductListenerService;
import com.example.kemal.androidappformvcapplication.DAL.Database.DatabaseHelper;

public class DatabaseInitializer
{
    private DatabaseHelper databaseHelper;
    private CategoryRequest categoryRequest;
    private ProductRequest productRequest;
    private CategoryListenerService categoryListenerService;
    private ProductListenerService productListenerService;
    private Context Context;

    public DatabaseInitializer(Context context)
    {
        this.databaseHelper = new DatabaseHelper(context);
        this.Context = context;
        this.categoryListenerService = new CategoryListenerService(context);
        this.productListenerService = new ProductListenerService(context);

        this.categoryRequest = new CategoryRequest(categoryListenerService.getListener(),categoryListenerService.getErrorListener());
        this.productRequest = new ProductRequest(productListenerService.getListener(), productListenerService.getErrorListener());
    }

    //This function will create all the data tables in the SQLite.
    public void initialize()
    {
        databaseHelper.open();
        databaseHelper.close();
    }

    //This function creates the requests to get categories and products from server.
    public void getStoreItemsAndInsert()
    {
        RequestQueue queue = Volley.newRequestQueue(this.Context);
        queue.add(categoryRequest);
        queue.add(productRequest);
    }
}
