package com.example.tuonthigklan1;

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

public class Bai1Activity extends AppCompatActivity {

    EditText editA;
    EditText editB;
    TextView txtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TimDieuKhien();
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XULY_CONG();
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XULY_TRU();
            }
        });

        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XULY_NHAN();
            }
        });

        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XULY_CHIA();
            }
        });

    }

    void TimDieuKhien()
    {
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        txtKQ = findViewById(R.id.txtKQ);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
    }

    void XULY_CONG()
    {
        String soA = editA.getText().toString();
        String soB = editB.getText().toString();
        float a = Float.parseFloat(soA);
        float b = Float.parseFloat(soB);
        float kq = a + b;
        txtKQ.setText(kq + "");
    }

    void XULY_TRU()
    {
        String soA = editA.getText().toString();
        String soB = editB.getText().toString();
        float a = Float.parseFloat(soA);
        float b = Float.parseFloat(soB);
        float kq = a - b;
        txtKQ.setText(kq + "");
    }
    void XULY_NHAN ()
    {
        String soA = editA.getText().toString();
        String soB = editB.getText().toString();
        float a = Float.parseFloat(soA);
        float b = Float.parseFloat(soB);
        float kq = a * b;
        txtKQ.setText(kq + "");
    }
    void XULY_CHIA ()
    {
        String soA = editA.getText().toString();
        String soB = editB.getText().toString();
        float a = Float.parseFloat(soA);
        float b = Float.parseFloat(soB);
        if (b == 0)
            txtKQ.setText("Không thể chia cho 0");
        else
        {
            float kq = a / b;
            txtKQ.setText(kq + "");
        }
    }
}