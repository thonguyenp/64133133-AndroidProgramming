package com.example.ex5_addsubmuldiv_var;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editA;
    EditText editB;
    Button btnCong, btnTru, btnNhan, btnChia;
    TextView txtKQ;

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
        btnCong.setOnClickListener(boLangNghe_XuLyCong);
        btnTru.setOnClickListener(boLangNghe_XuLyTru);
        btnNhan.setOnClickListener(boLangNghe_XuLyNhan);
        btnChia.setOnClickListener(boLangNghe_XuLyChia);
    }

    View.OnClickListener boLangNghe_XuLyCong = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float a = Float.parseFloat(editA.getText().toString());
            float b = Float.parseFloat(editB.getText().toString());
            float kq = a + b;
            txtKQ.setText(kq + "");
        }
    };
    View.OnClickListener boLangNghe_XuLyTru = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float a = Float.parseFloat(editA.getText().toString());
            float b = Float.parseFloat(editB.getText().toString());
            float kq = a - b;
            txtKQ.setText(kq + "");
        }
    };

    View.OnClickListener boLangNghe_XuLyNhan = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float a = Float.parseFloat(editA.getText().toString());
            float b = Float.parseFloat(editB.getText().toString());
            float kq = a * b;
            txtKQ.setText(kq + "");
        }
    };
    View.OnClickListener boLangNghe_XuLyChia = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float a = Float.parseFloat(editA.getText().toString());
            float b = Float.parseFloat(editB.getText().toString());
            if (b == 0)
            {
                txtKQ.setText("Không thể chia cho 0");
            }
            else
            {
                float kq = a / b;
                txtKQ.setText(kq + "");
            }
        }
    };
    void TimView ()
    {
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        txtKQ = findViewById(R.id.txtKQ);
    }
}