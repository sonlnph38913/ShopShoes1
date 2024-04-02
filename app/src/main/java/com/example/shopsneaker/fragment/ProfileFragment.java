package com.example.shopsneaker.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shopsneaker.Login;
import com.example.shopsneaker.R;
import com.example.shopsneaker.database.Dbhelper;


public class ProfileFragment extends Fragment {
    Button btnlichsudonhang, btnthongtincanhan, btnDoiMatKhau, btnThongtinLienHe, btnDangXuat;
    TextView tvuser1;
    ImageButton btnVeHome;
    private SQLiteDatabase mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnVeHome = view.findViewById(R.id.btnVeHome);
        btnlichsudonhang = view.findViewById(R.id.btnlichsudonhang);
        btnthongtincanhan = view.findViewById(R.id.btnthongtincanhan);
        btnDoiMatKhau = view.findViewById(R.id.btnDoiMatKhau);
        btnThongtinLienHe = view.findViewById(R.id.btnthongtinlienhe);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        tvuser1 = view.findViewById(R.id.tvuser1);

        //hienthi user
        Dbhelper dbHelper = new Dbhelper(getContext());
        mDatabase = dbHelper.getReadableDatabase();

        String userName = getUserName();
        tvuser1.setText(userName);


        //veHome
        btnVeHome.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            replaceFrg(homeFragment);
        });
        //lichsudonhang
        btnlichsudonhang.setOnClickListener(v -> {

        });
        //thongtincanhan
        btnthongtincanhan.setOnClickListener(v -> {
            ProfileInfoFragment profileInfoFragment = new ProfileInfoFragment();
            replaceFrg(profileInfoFragment);
        });

        //doimatkhau
        btnDoiMatKhau.setOnClickListener(v -> {
            DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
            replaceFrg(doiMatKhauFragment);
        });

        //dangxuat
        btnDangXuat.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
        });

        //thongtinlienhe
        btnThongtinLienHe.setOnClickListener(v -> {
            SupportFragment supportFragment = new SupportFragment();
            replaceFrg(supportFragment);
        });


        return view;
    }


    public void replaceFrg(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framelayout, fragment).commit();
    }

    private String getUserName() {
        String userName = "";
        // Thực hiện truy vấn SELECT để lấy tên người dùng từ cơ sở dữ liệu
        Cursor cursor = mDatabase.rawQuery("SELECT hoten FROM KHACHHANG", null);
        if (cursor.moveToFirst()) {
            // Lấy tên người dùng từ cột "hoten"
            userName = cursor.getString(cursor.getColumnIndex("hoten"));
        }
        cursor.close();
        return userName;
    }
}