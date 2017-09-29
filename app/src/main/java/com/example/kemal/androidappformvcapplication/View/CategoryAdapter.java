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
import com.example.kemal.androidappformvcapplication.Entities.Category;
import com.example.kemal.androidappformvcapplication.ProductListActivity;
import com.example.kemal.androidappformvcapplication.R;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category>
{
    private final Activity Context;
    private final java.util.List<Category> List;
    protected View ConvertView;

    //This class will be the adapter view for the list view that will be populating
    //Doing it this way enables you to create your own view with clickable images per list item
    //and having checkboxes, buttons etc.

    //Expose the class with reference to the activity you are using from.
    public CategoryAdapter(Activity context, List<Category> list)
    {
        super(context, R.layout.category_items, list);
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
        protected TextView CategoryName;
        protected TextView CategoryDescription;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;

        ConvertView = convertView;

        if (convertView == null)
        {
            //here We are creating a new layout and inflating our layout into it.
            LayoutInflater inflater = Context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.category_items, null);
            //This contains the types we declared above.
            viewHolder = new ViewHolder();

            //This is the reference to the text view on the layout we created before.
            viewHolder.CategoryName = convertView.findViewById(R.id.textViewCategoryName);
            //The Description...
            viewHolder.CategoryDescription = convertView.findViewById(R.id.textViewCategoryDescription);

            //Now we need to create TAG's that will hold a reference to the individual items within
            //the view. (Very important. Won't work without it when tapping on an item to reference it)
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.textViewCategoryName, viewHolder.CategoryName);
            convertView.setTag(R.id.textViewCategoryDescription, viewHolder.CategoryDescription);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Text view for the category name
        viewHolder.CategoryName.setTag(position);
        //Here reference of the category class
        viewHolder.CategoryName.setText(List.get(position).getCategoryName());
        //If user click on Category Name then it will lead to another page which shows the products from that category.
        viewHolder.CategoryName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Category category = List.get(position);
                Intent productListIntent = new Intent(getContext(), ProductListActivity.class);
                productListIntent.putExtra("Id", category.getId());
                productListIntent.putExtra("CategoryName", category.getCategoryName());
                productListIntent.putExtra("Description", category.getDescription());

                getContext().startActivity(productListIntent);
            }
        });

        //Text view for the category description
        viewHolder.CategoryDescription.setTag(position);
        //Here reference of the category class
        viewHolder.CategoryDescription.setText(List.get(position).getDescription());

        //Return the view
        return convertView;
    }
}
