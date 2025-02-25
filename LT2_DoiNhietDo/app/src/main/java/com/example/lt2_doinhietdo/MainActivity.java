package com.example.lt2_doinhietdo;

import android.os.Build;
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

    EditText editCDeg, editFDeg;
    Button btnTinh;
    TextView txtError;
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

    public void TimView()
    {
        editCDeg = findViewById(R.id.editCDeg);
        editFDeg = findViewById(R.id.editFDeg);
        btnTinh = findViewById(R.id.btnTinh);
        txtError = findViewById(R.id.txtError);
    }

    public void TinhToan (View v)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            if (!editCDeg.getText().toString().isEmpty() && !editFDeg.getText().toString().isEmpty())
            {
                txtError.setText("Không được điền vào cả hai ô");
            }
            else if (editCDeg.getText().toString().isEmpty())
            {
                DoiFSangC();
            }
            else
                DoiCSangF();
        }
    }

    public void DoiCSangF()
    {
        float c = Float.parseFloat(editCDeg.getText().toString());
        float f = (float) (c * 1.8 + 32);
        editFDeg.setText(f + "");
    }

    public void DoiFSangC()
    {
        float f = Float.parseFloat(editFDeg.getText().toString());
        float c = (float) ((f - 32)/ 1.8);
        editCDeg.setText(c + "");
    }
}