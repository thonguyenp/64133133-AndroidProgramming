package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class SubKanjiActivity extends AppCompatActivity {

    private TextView tvKanji, tvhanViet, tvMeaning, tvOnReading, tvKunReading;
    private ImageButton btnSpeakOn;
    private ImageButton btnSpeakKun;
    private GifImageView gifImageView;

    private TextToSpeech ttsOn, ttsKun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_kanji);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setContentView(R.layout.activity_sub_kanji);

        tvKanji = findViewById(R.id.tvKanji);
        tvhanViet = findViewById(R.id.tvhanViet);
        tvMeaning = findViewById(R.id.tvMeaning);
        tvOnReading = findViewById(R.id.tvOnReading);
        tvKunReading = findViewById(R.id.tvKunReading);
        btnSpeakOn = findViewById(R.id.btnSpeakOn);
        btnSpeakKun = findViewById(R.id.btnSpeakKun);
        gifImageView = findViewById(R.id.gifImageView);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        tvKanji.setText(intent.getStringExtra("kanji"));
        tvhanViet.setText("Nghĩa Hán: " + intent.getStringExtra("hanViet"));
        tvMeaning.setText("Nghĩa: " + intent.getStringExtra("meaning"));
        tvOnReading.setText("Âm On: " + intent.getStringExtra("onReading"));
        tvKunReading.setText("Âm Kun: " + intent.getStringExtra("kunReading"));
        // Khởi tạo TextToSpeech
        ttsOn = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                ttsOn.setLanguage(Locale.JAPANESE);
            }
        });

        ttsKun = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                ttsKun.setLanguage(Locale.JAPANESE);
            }
        });


        String onReading = intent.getStringExtra("onReading");
        String kunReading = intent.getStringExtra("kunReading");
        String kanji = intent.getStringExtra("kanji");

        btnSpeakOn.setOnClickListener(v -> {
            if (onReading != null && !onReading.isEmpty()) {
                ttsOn.speak(onReading, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakKun.setOnClickListener(v -> {
            if (kunReading != null && !kunReading.isEmpty()) {
                ttsKun.speak(kunReading, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        String gifUrl = "https://raw.githubusercontent.com/thonguyenp/KanjiGifs/main/" + kanji + ".GIF";
        new LoadGifTask(gifImageView).execute(gifUrl);

    }

    @Override
    protected void onDestroy() {
        if (ttsOn != null) {
            ttsOn.stop();
            ttsOn.shutdown();
        }
        if (ttsKun != null) {
            ttsKun.stop();
            ttsKun.shutdown();
        }

        super.onDestroy();
    }
    private static class LoadGifTask extends AsyncTask<String, Void, GifDrawable> {
        private final GifImageView gifImageView;

        LoadGifTask(GifImageView gifImageView) {
            this.gifImageView = gifImageView;
        }

        @Override
        protected GifDrawable doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream input = connection.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int n;
                while ((n = input.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, n);
                }

                byte[] gifBytes = buffer.toByteArray();
                return new GifDrawable(gifBytes);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(GifDrawable drawable) {
            if (drawable != null) {
                drawable.setLoopCount(1);

                gifImageView.setImageDrawable(drawable);
                drawable.stop();

                gifImageView.setOnClickListener(v -> {
                    if (!drawable.isPlaying()) {
                        drawable.reset();
                        drawable.start();
                    }
                });
            }
        }
    }

}