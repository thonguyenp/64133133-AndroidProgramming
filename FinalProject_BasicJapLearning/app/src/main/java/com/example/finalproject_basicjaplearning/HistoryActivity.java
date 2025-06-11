package com.example.finalproject_basicjaplearning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private HistoryAdapter adapter;

    private SharedPreferencesHelper prefsHelper;
    Button btnClearHistory, btnSort;
    private List<Map.Entry<String, KanaStats>> kanaStatsList;
    private boolean ascending = true; // dùng để luân phiên sắp xếp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerHistory);
        btnClearHistory = findViewById(R.id.btnClearHistory);
        btnSort = findViewById(R.id.btnSort);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        prefsHelper = new SharedPreferencesHelper(this);

        Map<String, KanaStats> kanaStats = prefsHelper.getAllKanaStats();
        if (kanaStats != null) {
            kanaStatsList = new ArrayList<>(kanaStats.entrySet());
        } else {
            kanaStatsList = new ArrayList<>();
        }

        if (kanaStats.isEmpty()) {
            Toast.makeText(this, "Chưa có dữ liệu lịch sử.", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new HistoryAdapter(kanaStats);
            recyclerView.setAdapter(adapter);
        }

        btnClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(HistoryActivity.this)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc muốn xóa toàn bộ lịch sử?")
                        .setPositiveButton("Xóa", (dialog, which) -> {
                            prefsHelper.clearKanaStats();
                            kanaStats.clear(); // nếu dùng Map cũ
                            Toast.makeText(HistoryActivity.this, "Đã xóa lịch sử", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Hủy", null)
                        .show();


            }
        });
        // Nút sắp xếp
        btnSort.setOnClickListener(v -> {
            if (kanaStatsList == null || kanaStatsList.isEmpty()) {
                Toast.makeText(this, "Không có dữ liệu để sắp xếp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sắp xếp danh sách
            Collections.sort(kanaStatsList, (e1, e2) -> {
                double r1 = e1.getValue().getCorrectRate();
                double r2 = e2.getValue().getCorrectRate();
                return ascending ? Double.compare(r1, r2) : Double.compare(r2, r1);
            });
            ascending = !ascending;

            // Tạo LinkedHashMap mới từ danh sách đã sắp
            Map<String, KanaStats> sortedMap = new LinkedHashMap<>();
            for (Map.Entry<String, KanaStats> entry : kanaStatsList) {
                //Duyệt hết kanaStatsList và thêm từng cặp key:value vào sortedMap mới tạo
                sortedMap.put(entry.getKey(), entry.getValue());
            }

            // Gán lại adapter với dữ liệu mới
            adapter = new HistoryAdapter(sortedMap);
            recyclerView.setAdapter(adapter);
        });

    }
}

