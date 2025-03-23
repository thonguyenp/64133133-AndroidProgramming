package com.example.ex4_addsubmuldiv_onclick;

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
    TextView txtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;
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
        TimDieuKhien();
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

    public void XuLyCong(View view)
    {
        float a = Float.parseFloat(editA.getText().toString());
        float b = Float.parseFloat(editB.getText().toString());

        float kq = a + b;
        txtKQ.setText(kq + "");
    }

    public void XuLyTru(View view)
    {
        float a = Float.parseFloat(editA.getText().toString());
        float b = Float.parseFloat(editB.getText().toString());

        float kq = a - b;
        txtKQ.setText(kq + "");
    }

    public void XuLyNhan(View view)
    {
        float a = Float.parseFloat(editA.getText().toString());
        float b = Float.parseFloat(editB.getText().toString());

        float kq = a * b;
        txtKQ.setText(kq + "");
    }

    public void XuLyChia(View view)
    {
        float a = Float.parseFloat(editA.getText().toString());
        float b = Float.parseFloat(editB.getText().toString());

        if (b == 0)
        {
            txtKQ.setText("Không thể chia cho 0");
        }
        else
        {
            float kq = a/b;
            txtKQ.setText(kq + "");
        }
    }
}