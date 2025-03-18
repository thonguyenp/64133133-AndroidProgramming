package com.example.lt7_apptracnghiemtoan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtA, txtB, count;
    EditText editKQ;
    int kq, dem = 0;
    Button btnKiemTra;
    List<Button> listBtn = new ArrayList<Button>();
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
        XuLyBtn();
        TaoSoNgauNhien();
        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int editkq = Integer.parseInt(editKQ.getText().toString());
                    if (kq == editkq)
                    {
                        Toast.makeText(MainActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                        dem++;
                        count.setText("Điểm: " + dem);
                        TaoSoNgauNhien();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Sai! Kết quả đúng: "+kq, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    void TimView ()
    {
        listBtn.add(findViewById(R.id.btn0));
        listBtn.add(findViewById(R.id.btn1));
        listBtn.add(findViewById(R.id.btn2));
        listBtn.add(findViewById(R.id.btn3));
        listBtn.add(findViewById(R.id.btn4));
        listBtn.add(findViewById(R.id.btn5));
        listBtn.add(findViewById(R.id.btn6));
        listBtn.add(findViewById(R.id.btn7));
        listBtn.add(findViewById(R.id.btn8));
        listBtn.add(findViewById(R.id.btn9));
        editKQ = findViewById(R.id.editKQ);
        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        count = findViewById(R.id.count);
        btnKiemTra = findViewById(R.id.btnKiemTra);
    }

    void TaoSoNgauNhien() {
        Random random = new Random();
        int a = random.nextInt(5);
        int b = random.nextInt(5);
        kq = a + b;
        txtA.setText(a + "");
        txtB.setText(b + "");
        editKQ.setText(""); // Xóa kết quả cũ
    }

    void XuLyBtn ()
    {
        for (var btn : listBtn)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editKQ.setText(String.valueOf(listBtn.indexOf(btn)));
                }
            });
        }
    }
}