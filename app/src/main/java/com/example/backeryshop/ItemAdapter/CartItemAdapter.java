package com.example.backeryshop.ItemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.backeryshop.Codes.Cart_Item_Details;
import com.example.backeryshop.R;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<MyViewCart> {
    private Context context;
    private List<Cart_Item_Details> dataList;

    public CartItemAdapter(Context context, List<Cart_Item_Details> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new MyViewCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewCart holder, int position) {
        Glide.with(context).load(dataList.get(position).getCartImageUrl()).into(holder.recImage);
        holder.itemName.setText(dataList.get(position).getCartItemName());
        holder.itemPrice.setText(dataList.get(position).getCartItemPrice());
        holder.shopName.setText(dataList.get(position).getCartItemShopName());
        holder.qty.setText(dataList.get(position).getCartItemQuantity());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
class MyViewCart extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView itemPrice,itemName,shopName,qty;
    CardView recCard;
    public MyViewCart(@NonNull View itemView) {
        super(itemView);

        recCard =itemView.findViewById(R.id.recCard);
        itemPrice = itemView.findViewById(R.id.cartItemPrice);
        itemName = itemView.findViewById(R.id.cartItemName);
        shopName = itemView.findViewById(R.id.cartShopName);
        recImage = itemView.findViewById(R.id.cartRecImage);
        qty = itemView.findViewById(R.id.cartItemQty);
    }
}
