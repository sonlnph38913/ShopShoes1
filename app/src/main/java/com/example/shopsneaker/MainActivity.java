package com.example.shopsneaker;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopsneaker.fragment.DonHangFragment;
import com.example.shopsneaker.fragment.HomeFragment;
import com.example.shopsneaker.fragment.SettingFragment;
import com.example.shopsneaker.fragment.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomView);
        frameLayout = findViewById(R.id.framelayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                if (menuId == R.id.mnHome) {
                    loadFragment(new HomeFragment(), false);
                }
//                else if (menuId == R.id.mnProduct) {
//                    loadFragment(new SanPhamFragment(), false);
//                }
                else if (menuId == R.id.mnCart) {
                    loadFragment(new DonHangFragment(), false);
                } else if (menuId == R.id.mnSupport) {
                    loadFragment(new SupportFragment(), false);
                } else if (menuId == R.id.mnSetting) {
                    loadFragment(new SettingFragment(), false);
                }

                return true;
            }
        });
        loadFragment(new HomeFragment(), true);

    }

    private void loadFragment(Fragment fragment, boolean selectMenu) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (selectMenu) {
            fragmentTransaction.add(R.id.framelayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.framelayout, fragment);
        }


        fragmentTransaction.commit();
    }
}