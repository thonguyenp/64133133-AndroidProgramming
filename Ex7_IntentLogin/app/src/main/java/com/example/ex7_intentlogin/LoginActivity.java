package com.example.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText editUserName, editPass, editUserMail;
    TextView txtError;
    Button btnOk;
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
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((editUserName.getText().toString().equalsIgnoreCase("thonguyenp"))
                && (editPass.getText().toString().equalsIgnoreCase("123"))
                && (editUserMail.getText().toString().equalsIgnoreCase("tho@gmail.com")))
                {
                    Intent iHome = new Intent(LoginActivity.this, HomeActivity.class);
                    iHome.putExtra("username",editUserName.getText().toString());
                    iHome.putExtra("pass",editPass.getText().toString());
                    startActivity(iHome);
                }
                else
                {
                    txtError.setText("Lỗi đăng nhập");
                }
            }
        });
    }
    void TimView ()
    {
        txtError = findViewById(R.id.txtError);
        editUserName = findViewById(R.id.editUserName);
        editPass = findViewById(R.id.editPass);
        editUserMail = findViewById(R.id.editUserEmail);
        btnOk = findViewById(R.id.btnOk);
    }
}