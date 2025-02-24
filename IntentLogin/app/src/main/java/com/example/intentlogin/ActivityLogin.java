package com.example.intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLogin extends AppCompatActivity {

    EditText editUsername, editPass, editEmail;
    Button btnDangNhap;
    TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimView();


    }
    public void TimView()
    {
        editUsername = findViewById(R.id.editUsername);
        editPass = findViewById(R.id.editPass);
        editEmail = findViewById(R.id.editEmail);
        txtError = findViewById(R.id.txtError);
    }


    public void DangNhap (View view)
    {
        String userName = editUsername.getText().toString();
        String pass = editPass.getText().toString();

        if (userName.equals("thonguyenp") && pass.equals("tho123"))
        {
            Intent iHome = new Intent(this, ActivityHome.class);
            iHome.putExtra("USERNAME",userName);
            startActivity(iHome);
        }
        else if (!userName.equals("thonguyenp"))
            txtError.setText("Sai tên đăng nhập");
        else if (!pass.equals("tho123")) {
            txtError.setText("Sai mật khẩu");
        }


    }
}