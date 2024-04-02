package com.example.shopsneaker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopsneaker.dao.KhachhangDAO;
import com.example.shopsneaker.model.khachhang;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    MaterialToolbar toolbar;
    TextInputLayout edTenDangKy, edEmailDangKy, edSdtDangKy, edPassDangKy, edHovatenDangKy, edDiaChiDangKy;
    Button btnDangKy;
    KhachhangDAO dao;

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolBarSignup);
        edTenDangKy = findViewById(R.id.edTenDangKy);
        edPassDangKy = findViewById(R.id.edPassDangKy);
        edHovatenDangKy = findViewById(R.id.edHovaTenDangKy);
        edEmailDangKy = findViewById(R.id.edEmailDangKy);
        edSdtDangKy = findViewById(R.id.edSdtDangKy);
        edDiaChiDangKy = findViewById(R.id.edDiaChiDangKy);
        btnDangKy = findViewById(R.id.btnDangKy);
        dao = new KhachhangDAO(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setTitle("Đăng ký tài khoản");

        btnDangKy.setOnClickListener(View -> {
            String taikhoan = edTenDangKy.getEditText().getText().toString();
            String matkhau = edPassDangKy.getEditText().getText().toString();
            String name = edHovatenDangKy.getEditText().getText().toString();
            String email = edEmailDangKy.getEditText().getText().toString();
            String sdt = edSdtDangKy.getEditText().getText().toString();
            String diachi = edDiaChiDangKy.getEditText().getText().toString();
            if (taikhoan.isEmpty() || matkhau.isEmpty() || name.isEmpty() || email.isEmpty() || sdt.isEmpty() || diachi.isEmpty()) {
                Toast.makeText(this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
            } else {
                if (validate(email)) {
                    khachhang kh = new khachhang();
                    kh.setTaikhoan(taikhoan);
                    kh.setMatkhau(matkhau);
                    kh.setHoten(name);
                    kh.setEmail(email);
                    kh.setSdt(sdt);
                    kh.setDiachi(diachi);
                    if (dao.insertKhachHang(kh) > 0) {
                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, Login.class));

                    }
                }


            }
        });

    }
}