package com.example.appcong;

import android.os.Bundle;
import android.view.View;
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
    //Bộ lắng nghe và xử lí sự kiện click lên btn
    public void XuLyCong(View view)
    {
        //mapping sang file java
        EditText editA = findViewById(R.id.editA);
        EditText editB = findViewById(R.id.editB);
        EditText editKQ = findViewById(R.id.editKQ);

        //lấy dữ liệu và lưu vào biến
        String strA = editA.getText().toString();
        String strB = editB.getText().toString();

        //Chuyển dữ liệu
        int a = Integer.parseInt(strA);
        int b = Integer.parseInt(strB);
        int kq = a + b;
        editKQ.setText(kq + "");
    }
}