package com.example.backeryshop.ItemAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.backeryshop.Codes.Cart_Item_Details;
import com.example.backeryshop.Codes.Order_Item_Details;
import com.example.backeryshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;

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
        String itemStatus = dataList.get(holder.getAdapterPosition()).getCartItemStatus();
        if(itemStatus.equals("Confirm")){
            holder.btn.setEnabled(false);
            holder.btn.setText("Confirm");
            holder.btn.setTextColor(Color.parseColor("#f50707"));

        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemName = dataList.get(holder.getAdapterPosition()).getCartItemName();
                String shopName =dataList.get(holder.getAdapterPosition()).getCartItemShopName();
                String total =dataList.get(holder.getAdapterPosition()).getCartItemPrice();
                String cusAddress = "Kudawewa road Polpithigama";
                String lat = dataList.get(holder.getAdapterPosition()).getUserLatitude();
                String lon = dataList.get(holder.getAdapterPosition()).getUserLongitude();
                String cusNumber = dataList.get(holder.getAdapterPosition()).getUserPhoneNumber();
                String productId =dataList.get(holder.getAdapterPosition()).getpID();
                String userID =dataList.get(holder.getAdapterPosition()).getUserID();

                saveData(itemName,shopName,total,lat,lon,cusNumber,cusAddress,productId,userID);
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void saveData(String obj1,String obj2,String obj3,String obj4,String obj5,String obj6,String obj7,String obj8,String obj9){

        Random rand = new Random();
        int rand_int2 = rand.nextInt(10000000);

        String code = Integer.toString(rand_int2);

        Order_Item_Details orderDetails = new Order_Item_Details("Sandeepa",obj1,obj3,obj5,obj4,obj6,obj7,obj8,obj9);
        FirebaseDatabase.getInstance().getReference("OrderDetails").child(obj2).child(code).setValue(orderDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context.getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context.getApplicationContext(), "Hellow", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
class MyViewCart extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView itemPrice,itemName,shopName,qty;
    CardView recCard;
    Button btn;
    public MyViewCart(@NonNull View itemView) {
        super(itemView);

        recCard =itemView.findViewById(R.id.recCard);
        itemPrice = itemView.findViewById(R.id.cartItemPrice);
        itemName = itemView.findViewById(R.id.cartItemName);
        shopName = itemView.findViewById(R.id.cartShopName);
        recImage = itemView.findViewById(R.id.cartRecImage);
        qty = itemView.findViewById(R.id.cartItemQty);
        btn = itemView.findViewById(R.id.btn);
    }

}
