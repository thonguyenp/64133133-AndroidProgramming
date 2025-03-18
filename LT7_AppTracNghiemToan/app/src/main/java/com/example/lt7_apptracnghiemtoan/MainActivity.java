package com.example.lt7_apptracnghiemtoan;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView txtA, txtB;
    EditText editKQ;
    int kq;
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
    }
    void TimView ()
    {
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
        btnKiemTra = findViewById(R.id.btnKiemTra);
    }

    void XuLyBtn ()
    {
        for (var btn : listBtn)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}