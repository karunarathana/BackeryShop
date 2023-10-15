package com.example.backeryshop.InterFaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.backeryshop.Codes.Cart_Item_Details;
import com.example.backeryshop.Codes.Order_Item_Details;
import com.example.backeryshop.ItemAdapter.CartItemAdapter;
import com.example.backeryshop.ItemAdapter.OrderItemAdapter;
import com.example.backeryshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Order_Recycler_View extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Order_Item_Details> dataList;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    OrderItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_recyler_view);


        recyclerView = findViewById(R.id.OrderRecyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Order_Recycler_View.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        dataList = new ArrayList<>();

        adapter = new OrderItemAdapter(Order_Recycler_View.this,dataList);
        recyclerView.setAdapter(adapter);

        getCartDetails();
    }
    public void getCartDetails(){
        databaseReference = FirebaseDatabase.getInstance().getReference("OrderDetails");
        valueEventListener = databaseReference.child("Kushan Bakery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String address = dataSnapshot.child("customerAddress").getValue().toString();
                    String itemName = dataSnapshot.child("customerBuyItemName").getValue().toString() ;
                    String itemTotal =  dataSnapshot.child("customerBuyItemTotal").getValue().toString();
                    String Lat = dataSnapshot.child("customerLatitude").getValue().toString();
                    String Long = dataSnapshot.child("customerLongitude").getValue().toString();
                    String name = dataSnapshot.child("customerName").getValue().toString();
                    String phoneNumber = dataSnapshot.child("customerPhoneNumber").getValue().toString();
                    String userID = dataSnapshot.child("userID").getValue().toString();
                    String productID = dataSnapshot.child("productId").getValue().toString();


                    Order_Item_Details orderItemDetails = new Order_Item_Details();

                    orderItemDetails.setCustomerAddress(address);
                    orderItemDetails.setCustomerBuyItemName(itemName);
                    orderItemDetails.setCustomerBuyItemTotal(itemTotal);
                    orderItemDetails.setCustomerLatitude(Lat);
                    orderItemDetails.setCustomerLongitude(Long);
                    orderItemDetails.setCustomerName(name);
                    orderItemDetails.setCustomerPhoneNumber(phoneNumber);
                    orderItemDetails.setUserID(userID);
                    orderItemDetails.setProductId(productID);

                    dataList.add(orderItemDetails);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Order_Recycler_View.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}