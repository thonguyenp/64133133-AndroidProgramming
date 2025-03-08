package com.example.ex6_intentdongian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MH3Activity extends AppCompatActivity {
    Button btnMH1, btnMH2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mh3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimView();
        btnMH1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMH1 = new Intent(MH3Activity.this, MainActivity.class);
                startActivity(intentMH1);

            }
        });
        btnMH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMH2 = new Intent(MH3Activity.this, MH2Activity.class);
                startActivity(intentMH2);

            }
        });
    }

    void TimView ()
    {
        btnMH1 = findViewById(R.id.btnMH1);
        btnMH2 = findViewById(R.id.btnMH2);
    }
}