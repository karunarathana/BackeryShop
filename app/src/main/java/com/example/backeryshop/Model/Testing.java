package com.example.backeryshop.Model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.backeryshop.R;
import com.google.android.datatransport.BuildConfig;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class Testing extends AppCompatActivity {
    TextView textView;

    private static final int REQUEST_CHECK_SETTINGS = 100;
    private static final int UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final int FAST_UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    private static final String TAG = Testing.class.getSimpleName();

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingRequest;
    private LocationCallback mLocationCallBack;
    private Location mCurrentLocation;
    private boolean mRequestingLocationUpdates = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        mLocationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mCurrentLocation = locationResult.getLastLocation();

                double latitude = mCurrentLocation.getLatitude();
                double longitude = mCurrentLocation.getLongitude();

                String lat = Double.toString(latitude);
                String log = Double.toString(longitude);

                textView = findViewById(R.id.textView2);

                Log.i("output",lat+" "+log);

                textView.setText("Hellow"+lat+" "+log);

                stopLocationUpdate();// put here

            }
        };

        mLocationRequest = LocationRequest.create().setInterval(UPDATE_INTERVAL_IN_MILLISECONDS)
                .setFastestInterval(FAST_UPDATE_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingRequest = builder.build();

        Dexter.withActivity(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdate();
                    }
                   @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if(response.isPermanentlyDenied()){
                            openSetting();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
               }).check();
    }

    private void openSetting(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID,null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void startLocationUpdate(){
        mSettingsClient.checkLocationSettings(mLocationSettingRequest).addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @SuppressLint("MissingPermission")
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                mFusedLocationClient.requestLocationUpdates(mLocationRequest,mLocationCallBack, Looper.myLooper());
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                int statusCode = ((ApiException)e).getStatusCode();
                switch (statusCode){

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG,"Location setting are not satisfied.Attempting to upgrade location setting");

                        try {
                            ResolvableApiException rae = (ResolvableApiException) e;
                            rae.startResolutionForResult(Testing.this,REQUEST_CHECK_SETTINGS);
                        }catch (IntentSender.SendIntentException sie){
                            Log.i(TAG,"Pending unable to execute request");
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        String errorMessage = "Location setting are inadequate,and cannot be fixed here";
                        Log.i(TAG,errorMessage);
                        Toast.makeText(Testing.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void stopLocationUpdate(){
        mFusedLocationClient.removeLocationUpdates(mLocationCallBack).addOnCompleteListener(this,task ->Log.i(TAG,"Location update stop") );

    }
    private boolean checkPermissions(){
        int permissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mRequestingLocationUpdates && checkPermissions()){
            startLocationUpdate();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mRequestingLocationUpdates){
            stopLocationUpdate();
        }
    }
}