package com.example.tuonthigklan1;

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

public class Bai3Activity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> dsHoa;
    ArrayAdapter<String> adapterHoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.lv);
        dsHoa = new ArrayList<>();
        dsHoa.add("Hoa Dao");
        dsHoa.add("Hoa Cuc");
        dsHoa.add("Hoa Mai");
        adapterHoa = new ArrayAdapter<>(Bai3Activity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsHoa);
        lv.setAdapter(adapterHoa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Bai3Activity.this, "Bạn đã chọn: " + dsHoa.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}