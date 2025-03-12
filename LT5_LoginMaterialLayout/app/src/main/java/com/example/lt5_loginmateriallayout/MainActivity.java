package com.example.lt5_loginmateriallayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText editUserName, editPassword;
    MaterialButton btnLogin;
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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editUserName.getText().toString().equalsIgnoreCase("thonguyenp") &&
                editPassword.getText().toString().equalsIgnoreCase("123"))
                {
                    Intent iHome = new Intent(MainActivity.this, HomeActivity.class);
                    iHome.putExtra("username",editUserName.getText().toString());
                    startActivity(iHome);
                }
                else
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void TimView ()
    {
        editPassword = findViewById(R.id.editPassword);
        editUserName = findViewById(R.id.editUsername);
        btnLogin = findViewById(R.id.btnLogin);
    }
}