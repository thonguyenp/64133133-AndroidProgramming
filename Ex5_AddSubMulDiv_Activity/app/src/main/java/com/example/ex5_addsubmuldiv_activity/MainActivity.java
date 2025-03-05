package com.example.ex5_addsubmuldiv_activity;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCong, btnTru, btnNhan,btnChia;
    EditText editA, editB;
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
        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
    }

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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCong)
        {
            Float a = Float.parseFloat(editA.getText().toString());
            Float b = Float.parseFloat(editB.getText().toString());
            float kq = a + b;
            txtKQ.setText(kq + "");
        }
        else if (view.getId() == R.id.btnTru)
        {
            Float a = Float.parseFloat(editA.getText().toString());
            Float b = Float.parseFloat(editB.getText().toString());
            float kq = a - b;
            txtKQ.setText(kq + "");
        }
        else if (view.getId() == R.id.btnNhan)
        {
            Float a = Float.parseFloat(editA.getText().toString());
            Float b = Float.parseFloat(editB.getText().toString());
            float kq = a * b;
            txtKQ.setText(kq + "");
        }
        else if (view.getId() == R.id.btnChia)
        {
            Float a = Float.parseFloat(editA.getText().toString());
            Float b = Float.parseFloat(editB.getText().toString());
            if (b == 0)
            {
                txtKQ.setText("Lỗi chia cho 0");
            }
            else
            {
                float kq = a / b;
                txtKQ.setText(kq + "");
            }
        }
    }
}