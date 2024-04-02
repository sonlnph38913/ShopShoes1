package com.example.shopsneaker.fragment;

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

import com.example.shopsneaker.R;
import com.example.shopsneaker.database.Dbhelper;
import com.example.shopsneaker.model.khachhang;


public class ProfileInfoFragment extends Fragment {
    TextView tvtendangnhaptt, tvhovatentt, tvemailtt, tvsdttt, tvdiachitt;
    Button btnsuathongtt;
    ImageButton btnveprofile;
    private SQLiteDatabase mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);
        btnveprofile = view.findViewById(R.id.btnVefrofile);
        tvtendangnhaptt = view.findViewById(R.id.tvtendangnhaptt);
        tvhovatentt = view.findViewById(R.id.tvhovatentt);
        tvemailtt = view.findViewById(R.id.tvemailtt);
        tvsdttt = view.findViewById(R.id.tvsdttt);
        tvdiachitt = view.findViewById(R.id.tvdiachitv);
        btnsuathongtt = view.findViewById(R.id.btnsuathongtintt);
        //suathongtin
        btnsuathongtt.setOnClickListener(v -> {
            Suathongtinuser suathongtinuser = new Suathongtinuser();
            replaceFrg(suathongtinuser);
        });
        //vefrofile
        btnveprofile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            replaceFrg(profileFragment);
        });
        //thông tin
        Dbhelper dbHelper = new Dbhelper(getContext());
        mDatabase = dbHelper.getReadableDatabase();
        khachhang userInfo = getUserInfo();
        tvtendangnhaptt.setText(userInfo.getTaikhoan());
        tvhovatentt.setText(userInfo.getHoten());
        tvemailtt.setText(userInfo.getEmail());
        tvsdttt.setText(userInfo.getSdt());
        tvdiachitt.setText(userInfo.getDiachi());


        return view;
    }

    private khachhang getUserInfo() {
        khachhang userInfo = new khachhang();
        // Thực hiện truy vấn SELECT để lấy thông tin tài khoản từ cơ sở dữ liệu
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM KHACHHANG", null);
        if (cursor.moveToFirst()) {
            // Lấy thông tin tài khoản từ các cột tương ứng
            userInfo.setMakh(cursor.getInt(cursor.getColumnIndex("makh")));
            userInfo.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
            userInfo.setTaikhoan(cursor.getString(cursor.getColumnIndex("taikhoan")));
            userInfo.setMatkhau(cursor.getString(cursor.getColumnIndex("matkhau")));
            userInfo.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
            userInfo.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            userInfo.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
        }
        cursor.close();
        return userInfo;
    }

    public void replaceFrg(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framelayout, fragment).commit();
    }
}