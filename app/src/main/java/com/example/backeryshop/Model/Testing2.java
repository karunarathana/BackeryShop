package com.example.backeryshop.Model;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backeryshop.MainDashboard;
import com.example.backeryshop.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Testing2 extends AppCompatActivity {

    Context context;

    TextView obj1,obj2,obj3;

    String fetch_Address;

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingRequest;
    private LocationCallback mLocationCallBack;
    private Location lastLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing2);

        context = getApplicationContext();

        obj1 = findViewById(R.id.textView2);
        obj2 = findViewById(R.id.textView3);
        obj3 = findViewById(R.id.textView4);


        //get Current Location user
        checkPermission();
        init();
    }
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(Testing2.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Testing2.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(Testing2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(Testing2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
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
                    if (ContextCompat.checkSelfPermission((Testing2.this), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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
        mFusedLocationClient.removeLocationUpdates(mLocationCallBack).addOnCompleteListener(this,task -> Log.i(TAG,"Location update stop") );

    }
    //step5 //
    private void receiveLocation(LocationResult locationResult){
        lastLocation = locationResult.getLastLocation();

        double x = lastLocation.getLatitude();
        double y = lastLocation.getLongitude();
        String latitude =Double.toString(lastLocation.getLatitude());
        String longitude =Double.toString(lastLocation.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(x,y,1);

            fetch_Address = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        obj1.setText(latitude);
        obj2.setText(longitude);
        obj3.setText(fetch_Address);

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