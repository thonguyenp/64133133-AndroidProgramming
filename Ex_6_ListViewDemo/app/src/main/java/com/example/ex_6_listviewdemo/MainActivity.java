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

    ArrayList<String>dsHuyen;
    ArrayAdapter<String> adapter;
    ListView lvHuyen;

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
        dsHuyen = new ArrayList<String>();
        dsHuyen.add("Nha Trang");
        dsHuyen.add("Cam Ranh");
        dsHuyen.add("Ninh Hòa");
        dsHuyen.add("Vạn Ninh");
        dsHuyen.add("Phú Yên");
        dsHuyen.add("Diên Khánh");
        dsHuyen.add("Khánh Sơn");
        dsHuyen.add("Khánh Vĩnh");
        dsHuyen.add("Cam Lâm");
        dsHuyen.add("huyện đảo Trường Sa");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dsHuyen);
        lvHuyen.setAdapter(adapter);
        lvHuyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tenTinhThanh = dsHuyen.get(i);
                Toast.makeText(MainActivity.this, "Bạn vừa chọn: " + tenTinhThanh, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void TimView()
    {
        lvHuyen = findViewById(R.id.lvHuyen);
    }
}