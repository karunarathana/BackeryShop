package com.example.backeryshop.Model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.backeryshop.MainDashboard;
import com.example.backeryshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Testing extends AppCompatActivity {

    DatabaseReference databaseReference;

    ArrayList<String> shopName = new ArrayList<>();
    ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

//        databaseReference = FirebaseDatabase.getInstance().getReference("ShopOwner");
//
//        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    String shop = dataSnapshot.child("userCompanyName").getValue().toString();
//                    shopName.add(shop);
//                    System.out.println(shopName);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Testing.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void getdata(View view){
        databaseReference = FirebaseDatabase.getInstance().getReference("ShopOwner");

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String shop = dataSnapshot.child("userCompanyName").getValue().toString();
                    shopName.add(shop);
                    System.out.println(shopName);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Testing.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}