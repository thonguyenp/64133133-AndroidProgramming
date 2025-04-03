package com.example.tuonthigklan1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Bai2Activity extends AppCompatActivity {

    LandScapeAdapter landScapeAdapter;

    ArrayList<LandScape> rvData;

    RecyclerView recyclerViewLandScape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvData = getDataForRecylerView();
        recyclerViewLandScape = findViewById(R.id.rv);
        recyclerViewLandScape.setLayoutManager(new LinearLayoutManager(this));
        landScapeAdapter = new LandScapeAdapter(this,rvData);
        recyclerViewLandScape.setAdapter(landScapeAdapter);

    }

    ArrayList<LandScape> getDataForRecylerView ()
    {
        ArrayList<LandScape> dsData = new ArrayList<>();
        LandScape ls1 = new LandScape("hanoiflagtower","Cot co HN");
        LandScape ls2 = new LandScape("eiffel","Thap Eiffel");
        dsData.add(ls1);
        dsData.add(ls2);
        return dsData;
    }
}