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

public class TuGiacActivity extends AppCompatActivity {

    EditText editDai, editRong;

    Button btnChuVi, btnDienTich;
    TextView txtResChuVi, txtResDienTich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tu_giac);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimView();
    }

    public void TimView()
    {
        editDai = findViewById(R.id.ediDai);
        editRong = findViewById(R.id.editRong);
        btnChuVi = findViewById(R.id.btnChuVi);
        btnDienTich = findViewById(R.id.btnDienTich);
        txtResChuVi = findViewById(R.id.txtResChuVi);
        txtResDienTich = findViewById(R.id.txtResDienTich);
    }

    public void TinhChuVi (View v)
    {
        float d = Float.parseFloat(editDai.getText().toString());
        float r = Float.parseFloat(editRong.getText().toString());
        float cv = (d + r) * 2;
        txtResChuVi.setText("Chu Vi: " + cv);
    }

    public void TinhDienTich (View v)
    {
        float d = Float.parseFloat(editDai.getText().toString());
        float r = Float.parseFloat(editRong.getText().toString());
        float s = d * r;
        txtResDienTich.setText("Diện Tích: " + s);

    }

}