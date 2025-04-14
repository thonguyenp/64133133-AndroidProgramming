package com.example.tuonthigklan1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bai2SubActivity extends AppCompatActivity {

    TextView txtTen;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtTen = findViewById(R.id.txtTen);
        img = findViewById(R.id.img);
        Intent ibai2sub = getIntent();
        String ten = ibai2sub.getStringExtra("ten");
        txtTen.setText(ten);
        int imgId = ibai2sub.getIntExtra("imgId",0);
        img.setImageResource(imgId);

    }
}