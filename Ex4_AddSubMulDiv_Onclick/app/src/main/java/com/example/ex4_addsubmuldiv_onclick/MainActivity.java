package com.example.ex4_addsubmuldiv_onclick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    public void XuLyCong(View view)
    {
        Button btnCong = findViewById(R.id.btnCong);
        EditText editA = findViewById(R.id.editA);
        EditText editB = findViewById(R.id.editB);
        EditText editKQ = findViewById(R.id.editKQ);

        int a = Integer.parseInt(editA.getText().toString());
        int b = Integer.parseInt(editB.getText().toString());

        int kq = a + b;
        editKQ.setText(kq + "");
    }

    public void XuLyTru(View view)
    {
        Button btnTru = findViewById(R.id.btnTru);
        EditText editA = findViewById(R.id.editA);
        EditText editB = findViewById(R.id.editB);
        EditText editKQ = findViewById(R.id.editKQ);

        int a = Integer.parseInt(editA.getText().toString());
        int b = Integer.parseInt(editB.getText().toString());

        int kq = a - b;
        editKQ.setText(kq + "");
    }
}