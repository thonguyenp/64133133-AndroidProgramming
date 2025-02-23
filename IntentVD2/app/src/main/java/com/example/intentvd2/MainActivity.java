package com.example.intentvd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
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

    public void NhapLieu (View view)
    {
        Intent iNhap = new Intent(this, SubActivity.class);
        startActivityForResult(iNhap, 8000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 8000)
        {
            if (resultCode == RESULT_OK)
            {
                String hoTen = data.getStringExtra("HT");
                int namSinh = data.getIntExtra("NS",2020);

                EditText editHT = findViewById(R.id.editHT);
                EditText editNS = findViewById(R.id.editNS);

                editHT.setText(hoTen + "");
                editNS.setText(namSinh + "");
            }
            else
                Toast.makeText(this, "Trả về thất bại", Toast.LENGTH_LONG).show();
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

}