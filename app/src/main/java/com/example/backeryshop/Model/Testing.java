package com.example.backeryshop.Model;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.backeryshop.MainDashboard;
import com.example.backeryshop.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;

import java.util.ArrayList;
//extends FragmentActivity implements OnMapReadyCallback
public class Testing extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap gMap;

    DatabaseReference databaseReference;
    FusedLocationProviderClient fusedLocationProviderClient;
    ValueEventListener valueEventListener;
    FrameLayout map;

    ArrayList<LatLng>googleMapData = new ArrayList<>();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        map = findViewById(R.id.map);
        textView = findViewById(R.id.text);

        showItemOnDatabase();

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
          this.gMap = googleMap;

          for(int i = 0 ; i<googleMapData.size();i++){
              MarkerOptions markerOptions = new MarkerOptions().position(googleMapData.get(i)).title("sandeepa");
              gMap.addMarker(markerOptions);
              gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(googleMapData.get(i),15));
          }


    }


    public void showItemOnDatabase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("OrderDetails");
        valueEventListener =databaseReference.child("Mahiya Bakery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    String latitude = dataSnapshot.child("customerLatitude").getValue().toString();
                    String Longitude = dataSnapshot.child("customerLongitude").getValue().toString();
                    String cusName = dataSnapshot.child("customerName").getValue().toString();

                    textView.setText(latitude);

                    double x = Double.parseDouble(latitude);
                    double y = Double.parseDouble(Longitude);

                    LatLng latLng = new LatLng(x,y);

                    googleMapData.add(latLng);
                }
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(Testing.this);


                fusedLocationProviderClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(Testing.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Testing.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}