package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class KanjiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KanjiAdapter adapter;
    private List<KanjiBank> kanjiList;
    private TextToSpeech tts;
    private SearchView searchView;
    private Button btnDrawKanji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kanji);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewKanji);
        kanjiList = Arrays.asList(KanjiBank.values());
        searchView = findViewById(R.id.searchViewKanji);
        btnDrawKanji = findViewById(R.id.btnDrawKanji);
        // Xử lý tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterKanji(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterKanji(newText);
                return true;
            }
        });

        btnDrawKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iDraw = new Intent(KanjiActivity.this,KanjiRecognition.class);
                startActivity(iDraw);
            }
        });

        adapter = new KanjiAdapter(this, kanjiList, tts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void filterKanji(String keyword) {
        keyword = keyword.toLowerCase();
        List<KanjiBank> filteredList = new java.util.ArrayList<>();

        for (KanjiBank item : kanjiList) {
            if (
                    item.kanji.contains(keyword) ||
                            item.hanViet.toLowerCase().contains(keyword) ||
                            item.onReading.toLowerCase().contains(keyword) ||
                            item.kunReading.toLowerCase().contains(keyword)
            ) {
                filteredList.add(item);
            }
        }
        adapter.updateData(filteredList);
    }
}