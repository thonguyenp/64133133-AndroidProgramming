package com.example.ex10_recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> rvData;
    RecyclerView recyclerViewLandScape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //3
        rvData = getDataForRecyclerView();
        //4
        recyclerViewLandScape = findViewById(R.id.recyclerLand);
        //5
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
//        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
//        RecyclerView.LayoutManager layoutLinear = new GridLayoutManager(this,2);
        recyclerViewLandScape.setLayoutManager(layoutLinear);
        //6
        landScapeAdapter = new LandScapeAdapter(this,rvData);
        //7
        recyclerViewLandScape.setAdapter(landScapeAdapter);
    }

    ArrayList<LandScape> getDataForRecyclerView ()
    {
        ArrayList<LandScape> dsDuLieu = new ArrayList<>();
        LandScape landScape1 = new LandScape("hanoiflagtower","Cột cờ Hà Nội");
        LandScape landScape2 = new LandScape("eiffel", "Tháp Eiffel");
        LandScape landScape3 = new LandScape("statueofliberty", "Tượng nữ  thần tự do");
        dsDuLieu.add(landScape1);
        dsDuLieu.add(landScape2);
        dsDuLieu.add(landScape3);
        return dsDuLieu;
    }
}