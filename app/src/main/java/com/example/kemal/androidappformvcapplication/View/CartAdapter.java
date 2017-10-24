package com.example.kemal.androidappformvcapplication.View;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.kemal.androidappformvcapplication.BLL.DataServices.CartRepository;
import com.example.kemal.androidappformvcapplication.Entities.Cart;
import com.example.kemal.androidappformvcapplication.R;
import java.util.List;

public class CartAdapter extends ArrayAdapter<Cart>
{
    private final Activity Context;
    private final String TAG = "Cart Adapter ";
    private final List<Cart> List;

    public CartAdapter(Activity context, List<Cart> list)
    {
        super(context, R.layout.cart_items, list);
        this.Context = context;
        this.List = list;
    }

    @NonNull
    @Override
    public Activity getContext() { return this.Context; }


    static class ViewHolder
    {
        protected TextView CartItemProductName;
        protected TextView CartItemPrice;
        protected TextView CartItemCount;
        protected Button ButtonRemove;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;

        if (convertView == null) {
            //here We are creating a new layout and inflating our layout into it.
            LayoutInflater layoutInflater = Context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.cart_items, null);
            //This contains the types we declared above.
            viewHolder = new ViewHolder();

            //This is the reference to the text view on the layout we created before.
            viewHolder.CartItemProductName = convertView.findViewById(R.id.textViewCartItemProductName);
            //The Price...
            viewHolder.CartItemPrice = convertView.findViewById(R.id.textViewCartItemPrice);
            //The item count.
            viewHolder.CartItemCount = convertView.findViewById(R.id.textViewCartItemCount);
            //The remove button.
            viewHolder.ButtonRemove = convertView.findViewById(R.id.buttonRemove);

            //Now we need to create TAG's that will hold a reference to the individual items within
            //the view. (Very important. Won't work without it when tapping on an item to reference it)
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.textViewCartItemProductName, viewHolder.CartItemProductName);
            convertView.setTag(R.id.textViewCartItemPrice, viewHolder.CartItemPrice);
            convertView.setTag(R.id.textViewCartItemCount, viewHolder.CartItemCount);
            convertView.setTag(R.id.buttonRemove, viewHolder.ButtonRemove);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Text view for cart product name
        viewHolder.CartItemProductName.setTag(position);
        viewHolder.CartItemProductName.setText("Name: " + List.get(position).getProductName()+"");
        //Text view for cart product price
        viewHolder.CartItemPrice.setTag(position);
        viewHolder.CartItemPrice.setText("Price: " + List.get(position).getPrice()+"");
        //Text view for item count
        viewHolder.CartItemCount.setTag(position);
        viewHolder.CartItemCount.setText("Count: " + List.get(position).getItemCount()+"");

        //Button for removing
        viewHolder.ButtonRemove.setTag(position);
        viewHolder.ButtonRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Cart cartItem = List.get(position);
                CartRepository cartRepo = new CartRepository(getContext());
                cartRepo.remove(cartItem);
                List.remove(position);
                notifyDataSetChanged();
            }
        });
        //Return the view
        return convertView;
    }
}
