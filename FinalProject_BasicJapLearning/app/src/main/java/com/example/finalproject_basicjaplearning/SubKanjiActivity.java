package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private boolean isTtsOnReady = false;
    private boolean isTtsKunReady = false;

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
            //đảm bảo text to speech được hỗ trợ trên điện thoại
            if (status == TextToSpeech.SUCCESS) {
                int result = ttsOn.setLanguage(Locale.JAPANESE);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Tiếng Nhật không được hỗ trợ trên thiết bị này", Toast.LENGTH_LONG).show();
                }
                else
                {
                    isTtsOnReady = true;    //nếu không có vấn đề gì thì để ready là true
                }
            }
        });

        ttsKun = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = ttsKun.setLanguage(Locale.JAPANESE);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Tiếng Nhật không được hỗ trợ trên thiết bị này", Toast.LENGTH_LONG).show();
                }
                else
                {
                    isTtsKunReady = true;
                }
            }
        });


        String onReading = intent.getStringExtra("onReading");
        String kunReading = intent.getStringExtra("kunReading");
        String kanji = intent.getStringExtra("kanji");

        btnSpeakOn.setOnClickListener(v -> {
            //Nếu ready == true
            if (isTtsOnReady && onReading != null && !onReading.isEmpty()) {
                //Chạy tts với reading đưa vào
                ttsOn.speak(onReading, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakKun.setOnClickListener(v -> {
            if (isTtsKunReady && kunReading != null && !kunReading.isEmpty()) {
                ttsKun.speak(kunReading, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        //Thứ tự thực thi gifUrl -> doInBackground() -> onPostExecute() -> chạy gif
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
                //Tải gif từ URL xuống trong nền, chuyển thành đối tượng URL
                URL url = new URL(urls[0]);
                //Mở kết nối HTTP đến GitHub và yêu cầu liên kết
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                //Lấy dữ liệu xuống nếu kết nối thành công
                InputStream input = connection.getInputStream();
                //Chuẩn bị dữ liệu byte và mảng byte để ghi dữ liệu
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int n;
                //đọc dữ liệu từ input -> mỗi lần là đọc 1 block 1024 byte từ input vào n
                while ((n = input.read(data, 0, data.length)) != -1) {
                    //ghi data đọc được vào buffer
                    buffer.write(data, 0, n);
                }
                //chuyển buffer thành mảng byte
                byte[] gifBytes = buffer.toByteArray();
                //trả về 1 GifDrawable từ mảng byte thông qua pl.droidsonroids.gif
                return new GifDrawable(gifBytes);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        //Kết quả được đọc từ hàm doInBackground()
        @Override
        protected void onPostExecute(GifDrawable drawable)
        {
            if (drawable != null) {
                //chạy 1 lần khi phát
                drawable.setLoopCount(1);
                //set hình gif bằng drawable đọc được ở trên
                gifImageView.setImageDrawable(drawable);
                //khi mới vào sẽ dừng lại
                drawable.stop();

                gifImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //khi bấm mới bắt đầu chạy
                        if (!drawable.isPlaying()) {
                            drawable.reset();
                            drawable.start();
                        }
                    }
                });
            }
        }
    }
}