package com.example.backeryshop.InterFaces;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.backeryshop.Codes.Upload_Item_Details_Cart;
import com.example.backeryshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class itemDetails extends AppCompatActivity {

    TextView itemName,itemPrice,itemDescription,shopName;
    ImageView obj1;

    ArrayList<String>itemData= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        obj1 = findViewById(R.id.itemImage);
        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemTotal);
        itemDescription = findViewById(R.id.itemDesc);
        shopName = findViewById(R.id.shopName);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            itemData.add(bundle.getString("shopName"));
            itemData.add(bundle.getString("itemName"));
            itemData.add(bundle.getString("itemPrice"));
            itemData.add(bundle.getString("Image"));
            itemData.add(bundle.getString("itemCode"));
            itemData.add(bundle.getString("userID"));

            itemName.setText(bundle.getString("itemName"));
            itemPrice.setText(bundle.getString("itemPrice"));
            itemDescription.setText(bundle.getString("itemDescription"));
            shopName.setText(bundle.getString("shopName"));
            Glide.with(this).load(bundle.getString("Image")).into(obj1);
        }
    }
    public void addToCart(View view){
        uploadData();
    }
    public void uploadData(){
        String shopName,productName,productPrice,imageUrl,productId,productQon,userID;
        shopName = itemData.get(0);
        productName = itemData.get(1);
        productPrice = itemData.get(2);
        imageUrl = itemData.get(3);
        productId= itemData.get(4);
        userID = itemData.get(5);

        Upload_Item_Details_Cart userDetails = new Upload_Item_Details_Cart(shopName,productName,productPrice,imageUrl,productId,"2");
        FirebaseDatabase.getInstance().getReference("CartDetails").child(userID).child(productId).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(itemDetails.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(itemDetails.this, "Hellow", Toast.LENGTH_SHORT).show();
            }
        });
    }
}