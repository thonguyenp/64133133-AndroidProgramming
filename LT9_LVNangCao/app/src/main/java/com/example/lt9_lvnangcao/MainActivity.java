package com.example.lt9_lvnangcao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int img[] = {R.drawable.googlepixel, R.drawable.oppo, R.drawable.iphone,R.drawable.samsung};
    String name[] = {"Điện thoại Google Pixel", "Điện thoại Oppo", "Điện thoại Iphone", "Điện thoại Samsung"};
    int price[] = {3000000, 4000000, 10000000, 7000000};
    int quantity[] = {10,5,4,9};

    ArrayList<Phone> list;
    MyArrayAdapter myArrayAdapter;  //sử dụng arrayadapter do mình tự định nghĩa
    ListView lv;
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
        //Tham chiếu lv và khởi tạo các biến
        lv = findViewById(R.id.lv);
        list = new ArrayList<Phone>();
        //Ép 4 mảng con vào 1 mảng chính
        for (int i = 0 ; i < name.length; i++)
        {
            String formattedPrice = formatCurrency(price[i]);
            //Thêm phần tử vào mảng dựa trên các phần tử trong mảng con
            list.add(new Phone(img[i], name[i], price[i], quantity[i]));
        }
        //Tạo mới adapter bằng cách đưa các tham số lần lượt: activity -> layout -> list data
        myArrayAdapter = new MyArrayAdapter(MainActivity.this,R.layout.item_layout,list);
        lv.setAdapter(myArrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent iItem = new Intent(MainActivity.this, SubActivity.class);
                iItem.putExtra("name",name[i]);
                iItem.putExtra("img",img[i]);
                iItem.putExtra("price",price[i]);
                iItem.putExtra("quantity",quantity[i]);
                startActivity(iItem);
            }
        });
    }

    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(amount) + " VND";
    }

}