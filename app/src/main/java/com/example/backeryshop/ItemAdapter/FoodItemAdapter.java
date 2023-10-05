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
import com.example.backeryshop.Model.ItemDetails;
import com.example.backeryshop.R;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;

    private List<ItemDetails> datalist;

    public FoodItemAdapter(Context context,List<ItemDetails>list) {
        this.context = context;
        this.datalist = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getDataImage()).into(holder.recImage);
        holder.itemName.setText(datalist.get(position).getItemName());

//        holder.recCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,Login.class);//methana ballna
//                intent.putExtra("Image",list.get(holder.getAdapterPosition()).getDataImage());
//                intent.putExtra("UserName",list.get(holder.getAdapterPosition()).getUserName());
//
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView itemName;
    CardView recCard;
    ImageView recImage;
    public MyViewHolder(@NonNull View itemView){
        super(itemView);
        recCard =itemView.findViewById(R.id.recCard);
        itemName = itemView.findViewById(R.id.itemName);
        recImage = itemView.findViewById(R.id.recImage);

    }
}
