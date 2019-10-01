package com.example.inclass03_amad;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingItemAdapter extends RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder> {
    static ArrayList<Product> ShoppingItemArrayList;
    static Product product;
    Context mContext;
    AddToCartInterface addToCartInterface;

    public ShoppingItemAdapter(Context context,ArrayList<Product> ShoppingItemArraylist1,AddToCartInterface addToCartInterface1)
    {
        this.ShoppingItemArrayList=ShoppingItemArraylist1;
        this.mContext = context;
        this.addToCartInterface = addToCartInterface1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item_row,parent,false);
        ShoppingItemAdapter.ViewHolder viewHolder=new ShoppingItemAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        product= ShoppingItemArrayList.get(position);
        if (product!=null){
            holder.addToCartInterface = addToCartInterface;
            holder.productName.setText(product.getName());
            holder.productPrice.setText("$"+product.getPrice());

            Picasso.get().load("drawable://" + product.getPhoto())
                    .config(Bitmap.Config.RGB_565)
                    .fit().centerCrop()
                    .into(holder.productImage);


        }

    }

    @Override
    public int getItemCount() {
        return ShoppingItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productPrice;
        TextView productName;
        ImageView productImage;
        Button addToCartBtn;
        AddToCartInterface addToCartInterface;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.itemNameTextView);
            productPrice = itemView.findViewById(R.id.itemPriceTextView);
            productImage = itemView.findViewById(R.id.itemImageView);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);


            addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: call Add to Cart API from here
                    Log.d("Button Clicked", "onClick: "+ShoppingItemArrayList.get(getAdapterPosition()).getName());
                    addToCartInterface.addToCart(ShoppingItemArrayList.get(getAdapterPosition()));
                }
            });

        }
    }
}
