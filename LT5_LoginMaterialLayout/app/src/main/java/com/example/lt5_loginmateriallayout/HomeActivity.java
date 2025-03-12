package com.example.lt5_loginmateriallayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {

    TextView txtUsername;
    MaterialButton btnProfile, btnDSHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimView();

        Intent iHome = getIntent();
        String username = iHome.getStringExtra("username");
        txtUsername.setText(username);
        btnDSHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iDSHP = new Intent(HomeActivity.this, ListActivity.class);
                startActivity(iDSHP);
            }
        });
    }

    void TimView ()
    {
        txtUsername = findViewById(R.id.txtUsername);
        btnProfile = findViewById(R.id.btnProfile);
        btnDSHP = findViewById(R.id.btnDSHP);
    }
}