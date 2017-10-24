package com.example.kemal.androidappformvcapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.ApplicationUserService;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CartRepository;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CategoryRepository;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.OrderRepository;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.ProductRepository;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.TokenService;
import com.example.kemal.androidappformvcapplication.BLL.DatabaseInitializer;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle toggle;
    protected NavigationView navigationView;
    protected DatabaseInitializer initializer;
    protected ApplicationUserService userService;
    protected CartRepository cartRepository;
    protected CategoryRepository categoryRepository;
    protected OrderRepository orderRepository;
    protected ProductRepository productRepository;
    protected TokenService tokenService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initializer = new DatabaseInitializer(this);
        userService = new ApplicationUserService(this);
        cartRepository = new CartRepository(this);
        categoryRepository = new CategoryRepository(this);
        orderRepository = new OrderRepository(this);
        productRepository = new ProductRepository(this);
        tokenService  = new TokenService(this);
        initializer.initialize();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.store:
                startActivity(new Intent(this, StoreActivity.class));
                return true;
            case R.id.shopping_cart:
                startActivity(new Intent(this, CartActivity.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this, UserAreaActivity.class));
                return true;
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            case R.id.logout:
                return true;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                return true;
            case R.id.app_settings:
                return true;
            default:
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
        }
    }

    protected void setNavigationView(int layout, Activity activity)
    {
        drawerLayout = (DrawerLayout) findViewById(layout);
        toggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.getMenu().clear();
        if (isLoggedIn())
        {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navigation_for_logged_user);
        }
        else
        {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navigation_for_default_user);
        }
    }

    protected Boolean isLoggedIn()
    {
        return userService.getUser() != null && tokenService.getToken() != null;
    }
}
