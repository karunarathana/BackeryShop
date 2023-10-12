package com.example.backeryshop.ItemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backeryshop.Codes.Order_Item_Details;
import com.example.backeryshop.R;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<MyViewOrder> {

    private Context context;
    private List<Order_Item_Details> dataList;

    public OrderItemAdapter(Context context, List<Order_Item_Details> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        return new MyViewOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewOrder holder, int position) {

        holder.itemName.setText(dataList.get(position).getCustomerBuyItemName());
        holder.name.setText(dataList.get(position).getCustomerName());
        holder.total.setText(dataList.get(position).getCustomerBuyItemTotal());
        holder.qty.setText(dataList.get(position).getCustomerBuyItemTotal());
        holder.address.setText(dataList.get(position).getCustomerAddress());
        holder.phone.setText(dataList.get(position).getCustomerPhoneNumber());

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
class MyViewOrder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView name,itemName,total,qty,address,phone;
    public MyViewOrder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.CusName);
        itemName = itemView.findViewById(R.id.itemName);
        total = itemView.findViewById(R.id.ItemPrice);
        qty = itemView.findViewById(R.id.itemQon);
        address = itemView.findViewById(R.id.address);;
        phone = itemView.findViewById(R.id.contact);;
    }

}
