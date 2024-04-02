package com.example.shopsneaker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopsneaker.dao.KhachhangDAO;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText edTenDangNhap, edMatKhau;
    Button btnDangNhap, btnDangKy;
    TextView tvDangNhapAd;
    KhachhangDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dao = new KhachhangDAO(this);
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        tvDangNhapAd = findViewById(R.id.tvDangNhapAd);

        btnDangNhap.setOnClickListener(view -> {
            String name = edTenDangNhap.getText().toString();
            String pass = edMatKhau.getText().toString();
            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
            } else {
                int valid = dao.checkLoginKhachHang(name, pass);
                if (valid > 0) {
                    Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Đăng Nhập Thất Bại!!! Vui lòng kiểm tra lại !!!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        btnDangKy.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));
        });

    }
}