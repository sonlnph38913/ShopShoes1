package binhpdph44989.group1.group1.fragmentUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterUser.GiayAdapterUser;
import binhpdph44989.group1.group1.dao.GiayDao;
import binhpdph44989.group1.group1.model.Giay;


public class HomeFragmentUser extends Fragment {
    GiayAdapterUser adapter;
    ArrayList<Giay> list;
    RecyclerView rcvGiay;
    GiayDao giayDao;
    ImageView imgInfo,GioHang;
    BottomNavigationView menuDanhMuc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);
        imgInfo = view.findViewById(R.id.imgInfo);
        GioHang = view.findViewById(R.id.GioHang);
        rcvGiay = view.findViewById(R.id.rcvGiay);
        menuDanhMuc = view.findViewById(R.id.menuDanhMuc);
        SearchView svGiay = view.findViewById(R.id.svGiay);
        giayDao = new GiayDao(getContext());
        loadData();

        menuDanhMuc.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                 if (menuId == R.id.mnDm1) {
                    loadFragment(new DanhMuc1Fragment(),false);
                } else if (menuId == R.id.mnDm2) {
                    loadFragment(new DanhMuc2Fragment(),false);
                } else if (menuId == R.id.mnAll) {
                     loadFragment(new HomeFragmentUser(),false);
                 }
                return false;
            }
        });
//       loadFragment(new HomeFragmentUser(),true);
        //seacrh
        svGiay.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Giay>listFilter = new ArrayList<>();
                for (Giay giay : giayDao.getDSGiay()){
                    if (giay.getTengiay().toLowerCase().contains(newText.toLowerCase())){
                        listFilter.add(giay);
                    }
                }
                loadSearch(listFilter);

                return false;
            }
        });

        //chuyển info
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragmentUser profileFragmentUser = new ProfileFragmentUser();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,profileFragmentUser)
                        .addToBackStack(null)
                        .commit();
            }
        });
        //chuyển giỏ hàng
        GioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHangFragment gioHangFragment = new GioHangFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,gioHangFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return view;
    }
    private void loadData(){
        list = giayDao.getDSGiay();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        adapter = new GiayAdapterUser(getContext(), list, giayDao);
        rcvGiay.setLayoutManager(gridLayoutManager);
        rcvGiay.setAdapter(adapter);
    }
    //data search
    private void loadSearch(ArrayList<Giay>listg){
        GiayAdapterUser adapter = new GiayAdapterUser(getContext(), listg, giayDao);
        rcvGiay.setAdapter(adapter);
    }
    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}