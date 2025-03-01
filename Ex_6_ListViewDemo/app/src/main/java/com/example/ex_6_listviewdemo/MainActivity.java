package com.example.ex_6_listviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>dsTinhThanh;
    ArrayAdapter<String> adapter;
    ListView lvTinhThanh;

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
        dsTinhThanh = new ArrayList<String>();
        dsTinhThanh.add("Nha Trang");
        dsTinhThanh.add("Đà Nẵng");
        dsTinhThanh.add("Bình Định");
        dsTinhThanh.add("Quy Nhơn");
        dsTinhThanh.add("Phú Yên");
        dsTinhThanh.add("TP Hồ Chí Minh");
        dsTinhThanh.add("Hà Nội");
        dsTinhThanh.add("Thừa Thiên Huế");
        dsTinhThanh.add("Hải Phòng");
        dsTinhThanh.add("Cao Bằng");
        dsTinhThanh.add("Yên Bái");
        dsTinhThanh.add("Lạng Sơn");
        dsTinhThanh.add("Đồng Nai");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dsTinhThanh);
        lvTinhThanh.setAdapter(adapter);
        lvTinhThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tenTinhThanh = dsTinhThanh.get(i);
                Toast.makeText(MainActivity.this, "Bạn vừa chọn: " + tenTinhThanh, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void TimView()
    {
        lvTinhThanh = findViewById(R.id.lvTinhThanh);
    }
}