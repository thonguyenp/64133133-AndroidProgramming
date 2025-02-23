package com.example.intentvd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Nhap_QuayVe (View view)
    {
        EditText edHT = findViewById(R.id.edHT);
        EditText edNS = findViewById(R.id.edNS);

        String hoTen = edHT.getText().toString();
        int namSinh = Integer.parseInt(edNS.getText().toString());

        Intent iKQNhapLieu = new Intent();
        iKQNhapLieu.putExtra("HT",hoTen);
        iKQNhapLieu.putExtra("NS",namSinh);
        setResult(RESULT_OK,iKQNhapLieu);
        finish();
    }
    public void Huy (View view)
    {
        Intent iQuayVe = new Intent(this, MainActivity.class);
        startActivity(iQuayVe);
    }
}