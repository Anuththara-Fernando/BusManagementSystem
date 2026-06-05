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

public class LocationActivity extends AppCompatActivity {

    Button btnLoadBus;

    RecyclerView recyclerViewBus;

    List<Bus> busList;

    BusAdapter adapter;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location);

        btnLoadBus = findViewById(R.id.btnLoadBus);

        recyclerViewBus = findViewById(R.id.recyclerViewBus);

        recyclerViewBus.setLayoutManager(
                new LinearLayoutManager(this)
        );

        busList = new ArrayList<>();


        adapter = new BusAdapter(busList);

        recyclerViewBus.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        btnLoadBus.setOnClickListener(v -> {

            Log.d("FIREBASE_TEST", "BUTTON WORKS");

            busList.clear();

            db.collection("buses")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {

                        Log.d("FIREBASE_TEST", "Firebase Success");

                        queryDocumentSnapshots.forEach(document -> {

                            Bus bus = document.toObject(Bus.class);

                            busList.add(bus);
                        });

                        adapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> {

                        Log.d("FIREBASE_TEST",
                                "Firebase Error: " + e.getMessage());
                    });


            btnLoadBus.setText("CLICKED");
        });
    }
}