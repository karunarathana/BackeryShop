package com.example.backeryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.backeryshop.InterFaces.ShopOwner_Sign_up;

public class SelectUser extends AppCompatActivity {
    Button customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        customer = findViewById(R.id.Selectcustomer);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserSign_In.class);
                startActivity(intent);
            }
        });


    }
    public void showSign_inPage(View view){
        Intent intent = new Intent(getApplicationContext(), ShopOwner_Sign_up.class);
        startActivity(intent);
    }
}