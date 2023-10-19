package com.example.backeryshop.InterFaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.backeryshop.R;

public class Shop_Owner_Main_Dashboard extends AppCompatActivity {
    TextView companyEmail,companyName;
    String cName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_owner_main_dashboard);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        cName = extras.getString("V1");
        String cEmail = extras.getString("V2");

        companyEmail = findViewById(R.id.companyEmail);
        companyName = findViewById(R.id.companyName);

        companyEmail.setText(cEmail);
        companyName.setText(cName);

    }

    public void showProductPage(View view){

        Bundle extras = new Bundle();
        extras.putString("V1",cName);

        Intent intent = new Intent(getApplicationContext(), Add_Product_Page.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void showOrders(View view){
//        Bundle extras = new Bundle();
//        extras.putString("V1",cName);

        Intent intent = new Intent(getApplicationContext(), Order_Recycler_View.class);
//      intent.putExtras(extras);
        startActivity(intent);
    }

    public void showDailyRoute(View view){
        Intent intent = new Intent(getApplicationContext(), Shop_Owner_Daily_Root.class);
        startActivity(intent);
    }
}