package com.example.ex12_bottomnavview;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView botNav;

    FragmentContainerView fragmentContainerView;

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

        botNav = findViewById(R.id.bot_nav);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int selectedItem = item.getItemId();
                if (selectedItem == R.id.menu_home)
                    //Thay fragment home
                {
                    Toast.makeText(MainActivity.this, "Thay Home", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new HomeFragment())
                            .commit();
                }
                else if (selectedItem == R.id.menu_search) {
                    Toast.makeText(MainActivity.this, "Thay Search", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new SearchFragment())
                            .commit();
                }
                else if (selectedItem == R.id.menu_profile) {
                    Toast.makeText(MainActivity.this, "Thay Search", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new ProfileFragment())
                            .commit();
                }
                else
                    return false;
                return true;
            }
        });
    }
}