package com.example.kemal.androidappformvcapplication.BLL;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.CategoryRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.Requests.ProductRequest;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.CategoryListenerService;
import com.example.kemal.androidappformvcapplication.ApiServices.ResponseListeners.ProductListenerService;

public class StoreItemService
{
    private CategoryRequest categoryRequest;
    private ProductRequest productRequest;
    private CategoryListenerService categoryListenerService;
    private ProductListenerService productListenerService;
    private android.content.Context Context;

    public StoreItemService(Context context)
    {
        this.Context = context;
        this.categoryListenerService = new CategoryListenerService(context);
        this.productListenerService = new ProductListenerService(context);

        this.categoryRequest = new CategoryRequest(categoryListenerService.getListener(),categoryListenerService.getErrorListener());
        this.productRequest = new ProductRequest(productListenerService.getListener(), productListenerService.getErrorListener());
    }

    public void getItemsAndInsert()
    {
        RequestQueue queue = Volley.newRequestQueue(this.Context);
        queue.add(categoryRequest);
        queue.add(productRequest);
    }
}
