package com.example.lt3_tinhchuvidientich;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnTamGiac, btnTuGiac;
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
        TimView();
        btnTamGiac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTamGiac = new Intent(MainActivity.this, TamGiacActivity.class);
                startActivity(iTamGiac);
            }
        });
        btnTuGiac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTuGiac = new Intent(MainActivity.this, TuGiacActivity.class);
                startActivity(iTuGiac);
            }
        });
    }

    public void TimView()
    {
        btnTamGiac = findViewById(R.id.btnTamGiac);
        btnTuGiac = findViewById(R.id.btnTuGiac);
    }

}