package com.example.lt9_lvnangcao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class SubActivity extends AppCompatActivity {
    TextView txtTen, txtSubPrice, txtSubQuantity;
    ImageView imgSubPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimView();
        Intent myItent = getIntent();
        String name = myItent.getStringExtra("name");
        txtTen.setText(name);
        int img = myItent.getIntExtra("img",99);
        imgSubPhone.setImageResource(img);
        int price = myItent.getIntExtra("price",99);
        String formattedPrice = formatCurrency(price);
        txtSubPrice.setText("Giá: "+ formattedPrice);
        int quantity = myItent.getIntExtra("quantity",99);
        txtSubQuantity.setText("Số lượng: "+ quantity);


    }

    void TimView()
    {
        txtTen = findViewById(R.id.txtTen);
        txtSubPrice = findViewById(R.id.txtSubPrice);
        txtSubQuantity = findViewById(R.id.txtSubQuantity);
        imgSubPhone = findViewById(R.id.imgSubPhone);

    }

    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(amount) + " VND";
    }


}