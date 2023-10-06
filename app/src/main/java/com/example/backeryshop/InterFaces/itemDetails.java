package com.example.backeryshop.InterFaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.backeryshop.R;

public class itemDetails extends AppCompatActivity {

    TextView itemName,itemPrice,itemDescription,shopName;
    ImageView obj1;
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
           itemName.setText(bundle.getString("itemName"));
            itemPrice.setText(bundle.getString("itemPrice"));
            itemDescription.setText(bundle.getString("itemDescription"));
            shopName.setText(bundle.getString("shopName"));
            Glide.with(this).load(bundle.getString("Image")).into(obj1);
        }
    }
}