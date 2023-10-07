package com.example.backeryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.backeryshop.ItemAdapter.FoodItemAdapter;
import com.example.backeryshop.Model.ItemDetails;
import com.example.backeryshop.Model.Testing;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
    FoodItemAdapter offerAdapter;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        //Set Layout Horizontal View
        offerView = findViewById(R.id.recyclerView);
        offerView.setHasFixedSize(true);
        offerView.setLayoutManager(new LinearLayoutManager(MainDashboard.this,LinearLayoutManager.HORIZONTAL,false));

        datalist = new ArrayList<>();
        offerAdapter = new FoodItemAdapter(MainDashboard.this,datalist);
        offerView.setAdapter(offerAdapter);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String userName = acct.getDisplayName();
        }

        getShopName();

    }
    public void getShopName(){
        List<String> shopName = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("ShopOwner");
        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String shop = dataSnapshot.child("userCompanyName").getValue().toString();
                    shopName.add(shop);
                }
                System.out.println(shopName);

                for(String i :shopName){
                    databaseReference = FirebaseDatabase.getInstance().getReference("foodAllStore");
                    valueEventListener =databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            datalist.clear();
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                String itemName = dataSnapshot.child("productName").getValue().toString();
                                String imageUrl = dataSnapshot.child("imageURL").getValue().toString();
                                String description = dataSnapshot.child("productDescription").getValue().toString();
                                String price = dataSnapshot.child("productPrice").getValue().toString();
                                String sName = dataSnapshot.child("shopName").getValue().toString();

                                //object Create itemDetails class
                                ItemDetails offer =new ItemDetails();

                                offer.setItemName(itemName);
                                offer.setDataImage(imageUrl);
                                offer.setShopName(sName);
                                offer.setProductDsc(description);
                                offer.setItemPrice(price);
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
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainDashboard.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}