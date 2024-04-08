package binhpdph44989.group1.group1;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import binhpdph44989.group1.group1.fragmentUser.DonHangFragment;
import binhpdph44989.group1.group1.fragmentUser.GioHangFragment;
import binhpdph44989.group1.group1.fragmentUser.HomeFragmentUser;
import binhpdph44989.group1.group1.fragmentUser.SettingFragment;

public class MainActivityUser extends AppCompatActivity {
    private CartViewModel cartViewModel;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_user);
        bottomNavigationView = findViewById(R.id.bottomView);
        frameLayout = findViewById(R.id.framelayout);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                if (menuId == R.id.mnHome) {
                    loadFragment(new HomeFragmentUser(), false);
                }

                else if (menuId == R.id.mnCart) {
                    loadFragment(new GioHangFragment(), false);
                } else if (menuId == R.id.mnOrder) {
                    loadFragment(new DonHangFragment(), false);
                } else if (menuId == R.id.mnSetting) {
                    loadFragment(new SettingFragment(), false);
                }

                return true;
            }
        });
        loadFragment(new HomeFragmentUser(),true);

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
    public CartViewModel getCartViewModel() {
        return cartViewModel;
    }
    }
