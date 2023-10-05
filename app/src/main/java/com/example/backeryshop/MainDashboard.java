package com.example.backeryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.backeryshop.ItemAdapter.FoodItemAdapter;
import com.example.backeryshop.Model.ItemDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainDashboard extends AppCompatActivity {

    private RecyclerView offerView;
    List<ItemDetails> datalist;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        //Set Layout Horizontal View
        offerView = findViewById(R.id.recyclerView);
        offerView.setHasFixedSize(true);
        offerView.setLayoutManager(new LinearLayoutManager(MainDashboard.this,LinearLayoutManager.HORIZONTAL,false));

        datalist = new ArrayList<>();
        FoodItemAdapter offerAdapter = new FoodItemAdapter(MainDashboard.this,datalist);
        offerView.setAdapter(offerAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("foodStore").child("ShanthaBeakry");

        valueEventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String na =snapshot.getValue().toString();
                datalist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String itemName = dataSnapshot.child("productName").getValue().toString();
                    String imageUrl = dataSnapshot.child("imageURL").getValue().toString();

                    //object Create itemDetails class
                    ItemDetails offer =new ItemDetails();

                    offer.setItemName(itemName);
                    offer.setDataImage(imageUrl);
                    datalist.add(offer);

                }
                offerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainDashboard.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}