package com.example.backeryshop;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.backeryshop.InterFaces.Cart_Recycler_View;
import com.example.backeryshop.ItemAdapter.FoodItemAdapter;
import com.example.backeryshop.Model.ItemDetails;
import com.example.backeryshop.Model.Testing;
import com.google.android.datatransport.BuildConfig;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainDashboard extends AppCompatActivity {

    private RecyclerView offerView;
    List<ItemDetails> datalist;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    FoodItemAdapter offerAdapter;
    String uPhoneNumber,UserID,latitude,longitude;
    Context context;

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingRequest;
    private LocationCallback mLocationCallBack;
    private Location lastLocation;
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


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        uPhoneNumber = extras.getString("phone");
        UserID = extras.getString("userID");


        context = getApplicationContext();


        //get Current Location user
        checkPermission();
        init();

    }
    public void showItemOnDatabase(){
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
                    String productID = dataSnapshot.child("productId").getValue().toString();

                    //object Create itemDetails class
                    ItemDetails itemDetails =new ItemDetails();

                    itemDetails.setItemName(itemName);
                    itemDetails.setDataImage(imageUrl);
                    itemDetails.setShopName(sName);
                    itemDetails.setProductDsc(description);
                    itemDetails.setItemPrice(price);
                    itemDetails.setProductId(productID);
                    itemDetails.setUserID(UserID);
                    datalist.add(itemDetails);

                }
                offerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainDashboard.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  void  showCart(View view){
        stopLocationUpdate();

        Bundle extras1 = new Bundle();
        extras1.putString("V1",latitude);
        extras1.putString("V2",longitude);
        extras1.putString("V3",uPhoneNumber);
        extras1.putString("V4",UserID);

        Intent intent = new Intent(getApplicationContext(), Cart_Recycler_View.class);
        intent.putExtras(extras1);
        startActivity(intent);

    }

    //Bellow location method
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainDashboard.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainDashboard.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MainDashboard.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(MainDashboard.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
    //step2 //

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission((MainDashboard.this), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show();
                        init();
                    }
                }
                return;
        }
    }

    //step3 //
    @SuppressLint("MissingPermission")
    public void startLocationUpdate() {
        mSettingsClient.checkLocationSettings(mLocationSettingRequest).addOnSuccessListener(locationSettingsResponse -> {
            //setting are ok
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallBack, Looper.myLooper());
        }).addOnFailureListener(e->{
            int status = ((ApiException) e).getStatusCode();

        });
    }

    //step4 //
    private void stopLocationUpdate(){
        mFusedLocationClient.removeLocationUpdates(mLocationCallBack).addOnCompleteListener(this,task ->Log.i(TAG,"Location update stop") );

    }
    //step5 //
    private void receiveLocation(LocationResult locationResult){
        lastLocation = locationResult.getLastLocation();


        latitude =Double.toString(lastLocation.getLatitude());
        longitude =Double.toString(lastLocation.getLongitude());

        showItemOnDatabase();

    }

    //step6 //
    public void init(){
        mFusedLocationClient  = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);
        mLocationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                receiveLocation(locationResult);
            }
        };
        mLocationRequest = LocationRequest.create().setInterval(5000).setFastestInterval(500).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(100);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingRequest = builder.build();
        startLocationUpdate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdate();
    }
}