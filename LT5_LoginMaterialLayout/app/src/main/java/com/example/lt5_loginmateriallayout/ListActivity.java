package com.example.lt5_loginmateriallayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {  // Đảm bảo có khai báo class

    ListView lvDSHP;
    ArrayList<String> dshp;
    ArrayAdapter<String> adapter;
    ArrayList<Double> diemSo;

    Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnReturn = findViewById(R.id.btnReturn);
        lvDSHP = findViewById(R.id.lvDSHP);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iHome = new Intent(ListActivity.this, HomeActivity.class);
                startActivity(iHome);
            }
        });
        dshp = new ArrayList<String>();
        dshp.add("Phân tích thiết kế hệ thống thông tin");
        dshp.add("Lập trình Python");
        dshp.add("Mạng máy tính");
        dshp.add("Phát triển ứng dụng Web");
        dshp.add("Thực tập cơ sở");

        diemSo = new ArrayList<Double>();
        diemSo.add(8.6);
        diemSo.add(9.0);
        diemSo.add(8.9);
        diemSo.add(8.8);
        diemSo.add(8.8);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,dshp);
        lvDSHP.setAdapter(adapter);
        lvDSHP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Double diem = diemSo.get(i);
                String tenMH = dshp.get(i);
                Snackbar.make(view, "Môn: " + tenMH + "\nĐiểm số: " + diem, Snackbar.LENGTH_LONG).show();
                //Dùng Snackbar vì vùng hiển thị rộng hơn Toast
            }
        });

    }
}
