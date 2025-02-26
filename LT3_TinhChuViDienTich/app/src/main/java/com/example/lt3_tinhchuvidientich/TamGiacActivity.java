package com.example.lt3_tinhchuvidientich;

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

public class TamGiacActivity extends AppCompatActivity {

    EditText editA, editB, editC;
    Button btnChuVi, btnDienTich;
    TextView txtResChuVi, txtResDientich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tam_giac);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimView();
    }

    public void TimView()
    {
        editA = findViewById(R.id.ediDai);
        editB = findViewById(R.id.editRong);
        editC = findViewById(R.id.editC);
        btnChuVi = findViewById(R.id.btnChuVi);
        btnDienTich = findViewById(R.id.btnDienTich);
        txtResChuVi = findViewById(R.id.txtResChuVi);
        txtResDientich = findViewById(R.id.txtResDienTich);
    }

    public void TinhChuVi(View v)
    {

        float a = Float.parseFloat(editA.getText().toString());
        float b = Float.parseFloat(editB.getText().toString());
        float c = Float.parseFloat(editC.getText().toString());
        if ((a + b > c) && (a + c > b) && (b + c > a) &&
                (a > 0) && (b > 0) && (c > 0))
        {
            float cv = a + b + c;
            txtResChuVi.setText("Chu Vi: " +cv);

        }
        else
        {
            txtResChuVi.setText("Đây không phải là 1 tam giác");
        }
    }

    public void TinhDienTich(View v)
    {
        float a = Float.parseFloat(editA.getText().toString());
        float b = Float.parseFloat(editB.getText().toString());
        float c = Float.parseFloat(editC.getText().toString());
        if ((a + b > c) && (a + c > b) && (b + c > a) &&
                (a > 0) && (b > 0) && (c > 0))
        {
            float ncv = (a + b + c) / 2;
            double s = Math.sqrt(ncv * (ncv -a) * (ncv - b) * (ncv - c));
            txtResDientich.setText("Diện Tích: " +s);
        }
        else
        {
            txtResDientich.setText("Đây không phải là 1 tam giác");
        }

    }

}