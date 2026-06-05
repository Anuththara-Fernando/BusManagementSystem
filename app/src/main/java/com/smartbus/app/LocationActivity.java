package com.smartbus.app;

import android.os.Bundle;
import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;

import android.widget.Button;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationActivity extends AppCompatActivity {


    RecyclerView recyclerViewBus;

    List<Bus> busList;

    BusAdapter adapter;

    FirebaseFirestore db;

    FusedLocationProviderClient fusedLocationClient;

    String userCity = "Colombo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location);


        recyclerViewBus = findViewById(R.id.recyclerViewBus);

        recyclerViewBus.setLayoutManager(
                new LinearLayoutManager(this)
        );

        busList = new ArrayList<>();


        adapter = new BusAdapter(busList);

        recyclerViewBus.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);

        db.collection("buses")
                .addSnapshotListener((value, error) -> {

                    if (error != null) {

                        Log.d("FIREBASE_TEST",
                                "Listener Error: " + error.getMessage());

                        return;
                    }

                    Log.d("FIREBASE_TEST", "Realtime Update");

                    busList.clear();

                    if (value != null) {

                        value.forEach(document -> {

                            Bus bus = document.toObject(Bus.class);

                            if (bus.getCity().equals(userCity)) {

                                busList.add(bus);
                            }
                        });

                        adapter.notifyDataSetChanged();
                    }
                });
        getUserLocation();
    }
    private void getUserLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    1
            );

            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {

                    Log.d("FIREBASE_TEST",
                            "Location request completed");

                    if (location != null) {

                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        Log.d("FIREBASE_TEST",
                                "Lat: " + latitude);

                        Log.d("FIREBASE_TEST",
                                "Lng: " + longitude);

                    } else {

                        Log.d("FIREBASE_TEST",
                                "Location is NULL");
                    }
                });
    }

}