package com.example.finalproject_basicjaplearning;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.googlecode.tesseract.android.ResultIterator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class KanjiRecognition extends AppCompatActivity {
    private TessBaseAPI tessBaseAPI;
    private String dataPath;
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_recognition);

        // Khởi tạo TessBaseAPI
        tessBaseAPI = new TessBaseAPI();

        // Đặt dataPath tới thư mục cha của tessdata
        dataPath = getFilesDir().getAbsolutePath();

        // Tìm DrawingView
        drawingView = findViewById(R.id.drawingView);
        if (drawingView == null) {
            Log.e("UI Error", "DrawingView not found in layout. Please check activity_main.xml.");
            Toast.makeText(this, "Drawing area not found.", Toast.LENGTH_LONG).show();
        }

        // Sao chép file traineddata bất đồng bộ
        new CopyTrainedDataTask().execute();

        // Gắn sự kiện cho nút nhận diện
        Button recognizeButton = findViewById(R.id.recognizeButton);
        if (recognizeButton != null) {
            recognizeButton.setOnClickListener(v -> {
                Bitmap bitmap = getBitmapFromCanvas();
                recognizeText(bitmap);
            });
        } else {
            Log.w("UI Warning", "recognizeButton not found in layout. Please check activity_main.xml.");
            Toast.makeText(this, "Recognize button not found.", Toast.LENGTH_LONG).show();
        }

        // Gắn sự kiện cho nút xóa canvas
        Button clearButton = findViewById(R.id.clearButton);
        if (clearButton != null) {
            clearButton.setOnClickListener(v -> {
                if (drawingView != null) {
                    drawingView.clear();
                    Toast.makeText(this, "Canvas cleared.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Drawing area not initialized.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.w("UI Warning", "clearButton not found in layout. Please check activity_main.xml.");
            Toast.makeText(this, "Clear button not found.", Toast.LENGTH_LONG).show();
        }
    }

    // AsyncTask để sao chép traineddata
    private class CopyTrainedDataTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                copyTrainedDataIfNeeded();
                return true;
            } catch (Exception e) {
                Log.e("OCR Error", "Failed to copy trained data: " + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                try {
                    tessBaseAPI.init(dataPath, "jpn");
                    Log.d("OCR Debug", "Tesseract initialized successfully.");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("OCR Error", "Failed to initialize Tesseract: " + e.getMessage());
                    Toast.makeText(KanjiRecognition.this, "Failed to initialize OCR engine.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(KanjiRecognition.this, "Failed to prepare OCR data.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Phương thức sao chép file traineddata nếu chưa có
    private void copyTrainedDataIfNeeded() {
        File tessDataDir = new File(dataPath, "tessdata");
        if (!tessDataDir.exists()) {
            if (tessDataDir.mkdirs()) {
                Log.d("OCR Debug", "tessdata directory created at: " + tessDataDir.getAbsolutePath());
            } else {
                Log.e("OCR Error", "Failed to create tessdata directory.");
            }
        }

        File trainedDataFile = new File(tessDataDir, "jpn.traineddata");
        if (!trainedDataFile.exists()) {
            try {
                // Kiểm tra file trong assets
                String[] assets = getAssets().list("tessdata");
                if (assets != null) {
                    Log.d("OCR Debug", "Assets in tessdata: " + java.util.Arrays.toString(assets));
                }

                // Sao chép file từ assets
                InputStream in = getAssets().open("tessdata/jpn.traineddata");
                OutputStream out = new FileOutputStream(trainedDataFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.close();
                Log.d("OCR Debug", "Trained data copied to: " + trainedDataFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("OCR Error", "Failed to copy trained data: " + e.getMessage());
            }
        } else {
            Log.d("OCR Debug", "Trained data already exists at: " + trainedDataFile.getAbsolutePath());
        }
    }

    // Lớp để lưu trữ ký tự và độ tin cậy
    private static class RecognitionResult {
        String text;
        float confidence;

        RecognitionResult(String text, float confidence) {
            this.text = text;
            this.confidence = confidence;
        }
    }

    // Tiền xử lý Bitmap để tăng độ tương phản
    private Bitmap preprocessBitmap(Bitmap bitmap) {
        // Resize Bitmap về 300x300
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
        Bitmap processedBitmap = Bitmap.createBitmap(resizedBitmap.getWidth(), resizedBitmap.getHeight(), resizedBitmap.getConfig());
        android.graphics.Canvas canvas = new android.graphics.Canvas(processedBitmap);
        android.graphics.Paint paint = new android.graphics.Paint();

        // Tăng độ tương phản mạnh hơn
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                5, 0, 0, 0, -200, // Red
                0, 5, 0, 0, -200, // Green
                0, 0, 5, 0, -200, // Blue
                0, 0, 0, 1, 0     // Alpha
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(resizedBitmap, 0, 0, paint);

        // Lưu Bitmap để debug
        try {
            File file = new File(getExternalFilesDir(null), "debug_bitmap.png");
            FileOutputStream out = new FileOutputStream(file);
            processedBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
            Log.d("OCR Debug", "Processed bitmap saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e("OCR Error", "Failed to save bitmap: " + e.getMessage());
        }

        return processedBitmap;
    }

    // Phương thức nhận diện văn bản từ ảnh vẽ
    private void recognizeText(Bitmap bitmap) {
        if (tessBaseAPI == null) {
            Toast.makeText(this, "OCR engine not initialized.", Toast.LENGTH_SHORT).show();
            return;
        }



        if (bitmap != null && !bitmap.isRecycled()) {
            // Debug kích thước Bitmap
            Log.d("OCR Debug", "Original bitmap size: " + bitmap.getWidth() + "x" + bitmap.getHeight());

            // Tiền xử lý Bitmap
            Bitmap processedBitmap = preprocessBitmap(bitmap);
            Log.d("OCR Debug", "Processed bitmap size: " + processedBitmap.getWidth() + "x" + processedBitmap.getHeight());

            // Thử PSM_SINGLE_BLOCK trước
            tessBaseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_BLOCK);
            tessBaseAPI.setImage(processedBitmap);

            // Lấy kết quả từ getUTF8Text để debug
            String utf8Text = tessBaseAPI.getUTF8Text();
            Log.d("OCR Debug", "getUTF8Text result (PSM_SINGLE_BLOCK): " + (utf8Text != null ? utf8Text : "null"));
            if (utf8Text != null && !utf8Text.isEmpty()) {
                Toast.makeText(this, "Simple OCR result: " + utf8Text, Toast.LENGTH_LONG).show();
            }

            // Lấy ResultIterator để duyệt các ký tự
            ResultIterator iterator = tessBaseAPI.getResultIterator();
            ArrayList<RecognitionResult> results = new ArrayList<>();

            if (iterator != null) {
                try {
                    iterator.begin();
                    do {
                        String text = iterator.getUTF8Text(TessBaseAPI.PageIteratorLevel.RIL_SYMBOL);
                        float confidence = iterator.confidence(TessBaseAPI.PageIteratorLevel.RIL_SYMBOL);
                        // Lọc ký tự rỗng và độ tin cậy thấp
                        if (text != null && !text.trim().isEmpty() && confidence > 30.0f) {
                            results.add(new RecognitionResult(text, confidence));
                            Log.d("OCR Result", "Symbol: " + text + ", Confidence: " + confidence);
                        } else {
                            Log.d("OCR Debug", "Skipped symbol: " + (text == null ? "null" : text) + ", Confidence: " + confidence);
                        }
                    } while (iterator.next(TessBaseAPI.PageIteratorLevel.RIL_SYMBOL));
                } finally {
                    iterator.delete();
                }
            } else {
                Log.e("OCR Error", "ResultIterator is null (PSM_SINGLE_BLOCK).");
            }

            // Nếu không có kết quả, thử PSM_AUTO
            if (results.isEmpty()) {
                Log.d("OCR Debug", "No results with PSM_SINGLE_BLOCK, trying PSM_AUTO...");
                tessBaseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_AUTO);
                tessBaseAPI.setImage(processedBitmap);

                utf8Text = tessBaseAPI.getUTF8Text();
                Log.d("OCR Debug", "getUTF8Text result (PSM_AUTO): " + (utf8Text != null ? utf8Text : "null"));
                if (utf8Text != null && !utf8Text.isEmpty()) {
                    Toast.makeText(this, "Simple OCR result (PSM_AUTO): " + utf8Text, Toast.LENGTH_LONG).show();
                }

                iterator = tessBaseAPI.getResultIterator();
                if (iterator != null) {
                    try {
                        iterator.begin();
                        do {
                            String text = iterator.getUTF8Text(TessBaseAPI.PageIteratorLevel.RIL_SYMBOL);
                            float confidence = iterator.confidence(TessBaseAPI.PageIteratorLevel.RIL_SYMBOL);
                            // Lọc ký tự rỗng và độ tin cậy thấp
                            if (text != null && !text.trim().isEmpty() && confidence > 50.0f) {
                                results.add(new RecognitionResult(text, confidence));
                                Log.d("OCR Result", "Symbol: " + text + ", Confidence: " + confidence);
                            } else {
                                Log.d("OCR Debug", "Skipped symbol: " + (text == null ? "null" : text) + ", Confidence: " + confidence);
                            }
                        } while (iterator.next(TessBaseAPI.PageIteratorLevel.RIL_SYMBOL));
                    } finally {
                        iterator.delete();
                    }
                } else {
                    Log.e("OCR Error", "ResultIterator is null (PSM_AUTO).");
                }
            }

            // Sắp xếp theo độ tin cậy giảm dần
            Collections.sort(results, (r1, r2) -> Float.compare(r2.confidence, r1.confidence));

            // Lấy 5 ký tự đầu tiên (hoặc ít hơn nếu không đủ)
            StringBuilder displayText = new StringBuilder("Top 5 Recognized Kanji:\n");
            int maxResults = Math.min(5, results.size());
            if (maxResults > 0) {
                for (int i = 0; i < maxResults; i++) {
                    RecognitionResult result = results.get(i);
                    displayText.append(i + 1)
                            .append(". ")
                            .append(result.text)
                            .append(" (Confidence: ")
                            .append(String.format("%.2f%%", result.confidence))
                            .append(")\n");
                }
                Toast.makeText(this, displayText.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No valid kanji recognized.", Toast.LENGTH_SHORT).show();
                Log.e("OCR Error", "No valid results from ResultIterator after trying PSM_SINGLE_BLOCK and PSM_AUTO.");
            }
            if (!results.isEmpty()) {
                // Lấy ký tự đầu tiên (độ tin cậy cao nhất)
                String topKanji = results.get(0).text;

                // Sao chép vào clipboard
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Recognized Kanji", topKanji);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(this, "Copied '" + topKanji + "' to clipboard!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid or empty image.", Toast.LENGTH_SHORT).show();
            Log.e("OCR Error", "Bitmap is null or recycled.");
        }

    }

    // Lấy Bitmap từ DrawingView
    private Bitmap getBitmapFromCanvas() {
        if (drawingView != null) {
            //Lấy bitmap, nếu khác null thì trả về Bitmap
            Bitmap bitmap = drawingView.getBitmap();
            if (bitmap != null) {
                return bitmap;
            } else {
                Toast.makeText(this, "Failed to get drawing.", Toast.LENGTH_SHORT).show();
                Log.e("OCR Error", "Bitmap from DrawingView is null.");
            }
        } else {
            Toast.makeText(this, "Drawing area not initialized.", Toast.LENGTH_SHORT).show();
            Log.e("UI Error", "DrawingView is null.");
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        if (tessBaseAPI != null) {
            tessBaseAPI.end();
            tessBaseAPI = null;
        }
        super.onDestroy();
    }
}