package com.example.lt1_bmiapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBtnNam, imgBtnNu;
    EditText editHeight, editWeight;
    Button btnTinh;
    TextView txtRes, txtResString;

    String gender = "";
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
        imgBtnNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "Nam";
                imgBtnNam.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
                imgBtnNu.clearColorFilter();
            }
        });
        imgBtnNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "Nữ";
                imgBtnNu.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
                imgBtnNam.clearColorFilter();

            }
        });
    }
    public void TimView()
    {
        imgBtnNam = findViewById(R.id.imgBtnNam);
        imgBtnNu = findViewById(R.id.imgBtnNu);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        btnTinh = findViewById(R.id.btnTinh);
        txtRes = findViewById(R.id.txtRes);
        txtResString = findViewById(R.id.txtResString);
    }

    public void TinhToan(View v)
    {
        float weight = Float.parseFloat(editWeight.getText().toString());
        float height = Float.parseFloat(editHeight.getText().toString()) / 100;

        float kq =  weight / (height * height);
        txtRes.setText("Giới tính: " + gender + " Chỉ số BMI" +kq);
        TinhBMI(kq);
    }

    public float TinhBMI (float kq)
    {
        if (kq <= 18.5)
        {
            txtResString.setTextColor(Color.YELLOW);
            txtResString.setText("Gầy, Cần bổ sung thêm chất");
        }
        else if (kq <= 22.9)
        {
            txtResString.setTextColor(Color.GREEN);
            txtResString.setText("Bình thường");
        }
        else if (kq == 23)
        {
            txtResString.setTextColor(Color.YELLOW);
            txtResString.setText("Thừa cân, cần điều chỉnh chế độ ăn uống");
        }
        else if (kq <= 24.9)
        {
            txtResString.setTextColor(Color.YELLOW);
            txtResString.setText("Tiền béo phì, cần điều chỉnh chế độ ăn uống");
        }
        else if (kq <= 29.9)
        {
            txtResString.setTextColor(Color.RED);
            txtResString.setText("Béo phì độ I, cần điều chỉnh chế độ ăn uống");
        }
        else if (kq == 30)
        {
            txtResString.setTextColor(Color.RED);
            txtResString.setText("Béo phì độ II, cần điều chỉnh chế độ ăn uống");
        }
        return kq;
    }
}