package com.example.backeryshop.InterFaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.backeryshop.Codes.Cart_Item_Details;
import com.example.backeryshop.ItemAdapter.CartItemAdapter;
import com.example.backeryshop.MainDashboard;
import com.example.backeryshop.Model.ItemDetails;
import com.example.backeryshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart_Recycler_View extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Cart_Item_Details> dataList;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    CartItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_recycler_view);

        recyclerView = findViewById(R.id.CartRecyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Cart_Recycler_View.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        dataList = new ArrayList<>();

        adapter = new CartItemAdapter(Cart_Recycler_View.this,dataList);
        recyclerView.setAdapter(adapter);

        getCartDetails();

    }

    public void getCartDetails(){
        databaseReference = FirebaseDatabase.getInstance().getReference("CartDetails");
        valueEventListener = databaseReference.child("2067").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String itemName = dataSnapshot.child("productName").getValue().toString();
                    String itemPrice = dataSnapshot.child("productPrice").getValue().toString() ;
                    String qty =  dataSnapshot.child("quantity").getValue().toString();
                    String shopName = dataSnapshot.child("shopName").getValue().toString();
                    String imageUrl = dataSnapshot.child("imageURL").getValue().toString();

                    String userID = dataSnapshot.child("userId").getValue().toString();
                    String productID = dataSnapshot.child("productId").getValue().toString();
                    String itemStatus = dataSnapshot.child("itemStatus").getValue().toString();

                    Cart_Item_Details cartItemDetails = new Cart_Item_Details();

                    cartItemDetails.setCartImageUrl(imageUrl);
                    cartItemDetails.setCartItemName(itemName);
                    cartItemDetails.setCartItemPrice(itemPrice);
                    cartItemDetails.setCartItemQuantity(qty);
                    cartItemDetails.setCartItemShopName(shopName);
                    cartItemDetails.setpID(productID);
                    cartItemDetails.setUserID(userID);
                    cartItemDetails.setCartItemStatus(itemStatus);

                    dataList.add(cartItemDetails);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cart_Recycler_View.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}