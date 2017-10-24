package com.example.kemal.androidappformvcapplication.View;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.Entities.Product;
import com.example.kemal.androidappformvcapplication.ProductActivity;
import com.example.kemal.androidappformvcapplication.R;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product>
{
    private final Activity Context;
    private final List<Product> List;
    private View ConvertView;

    public ProductAdapter(Activity context, List<Product> list)
    {
        super(context, R.layout.product_items, list);
        this.Context = context;
        this.List = list;
    }

    @NonNull
    @Override
    public Activity getContext() { return this.Context; }

    //This will hold the references or view items that live on our custom layout
    //That way we can reference them later for example when we click on an item from listView
    //we will be able to handle click events and able to retrieve the items values, also change  the color of
    //the selected item. (Changing the selected color of the listView is not a standart feature.)
    static class ViewHolder
    {
        protected TextView ProductName;
        protected TextView ProductDescription;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;
        ConvertView = convertView;

        if (ConvertView == null)
        {
            //here We are creating a new layout and inflating our layout into it.
            LayoutInflater layoutInflater = Context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.product_items, null);
            //This contains the types we declared above.
            viewHolder = new ViewHolder();

            //This is the reference to the text view on the layout we created before.
            viewHolder.ProductName = convertView.findViewById(R.id.textViewProductName);
            //The Description...
            viewHolder.ProductDescription = convertView.findViewById(R.id.textViewProductDescription);

            //Now we need to create TAG's that will hold a reference to the individual items within
            //the view. (Very important. Won't work without it when tapping on an item to reference it)
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.textViewProductName, viewHolder.ProductName);
            convertView.setTag(R.id.textViewProductDescription, viewHolder.ProductDescription);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Text view for the Product name
        viewHolder.ProductName.setTag(position);
        //Here reference of the Product class
        viewHolder.ProductName.setText(List.get(position).getProductName());
        //If user click on Product Name then it will lead to another page which shows the information of that product.
        viewHolder.ProductName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Product product = List.get(position);
                Intent productIntent = new Intent(getContext(), ProductActivity.class);
                productIntent.putExtra("Id", product.getId());
                productIntent.putExtra("ProductName", product.getProductName());
                productIntent.putExtra("ProductDescription", product.getProductDescription());
                productIntent.putExtra("Stock", product.getStock());
                productIntent.putExtra("ImagePath", product.getImagePath());
                productIntent.putExtra("Price", product.getPrice());
                productIntent.putExtra("CategoryId", product.getCategoryId());

                getContext().startActivity(productIntent);
            }
        });

        //Text view for the Product description
        viewHolder.ProductDescription.setTag(position);
        //Here reference of the Product class
        viewHolder.ProductDescription.setText(List.get(position).getProductDescription());

        //Return the view
        return convertView;
    }
}
