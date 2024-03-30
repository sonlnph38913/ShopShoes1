package com.example.shopsneaker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context) {
        super(context, "NIKE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbQuanLy = "CREATE TABLE QUANLY(maql text primary key,hoten text,matkhau text,loaitaikhoan text)";
        db.execSQL(dbQuanLy);
        String dbKhachHang = "CREATE TABLE KHACHHANG(makh integer primary key autoincrement, hoten text,taikhoan text,matkhau text, sdt text, diachi text )";
        db.execSQL(dbKhachHang);
        String dbLoai = "CREATE TABLE LOAIGIAY(maloai integer primary key autoincrement,  tenloai text)";
        db.execSQL(dbLoai);
        String dbGiay = "CREATE TABLE GIAY(magiay integer primary key autoincrement,tengiay text,hinhanh text,giaban integer,soluong integer,maloai integer references LOAIGIAY(maloai))";
        db.execSQL(dbGiay);
        String dbCTDonHang = "CREATE TABLE CTDONHANG(madh integer primary key autoincrement,makh integer references KHACHHANG(makh),magiay integer references GIAY(magiay),ngaydat text,trangthai integer,tongtien interger)";
        db.execSQL(dbCTDonHang);
        String dbDonHang = "CREATE TABLE DONHANG(madh integer primary key autoincrement,makh integer references THANHVIEN(makh),magiay integer references GIAY(magiay))";
        db.execSQL(dbDonHang);
        String dbCTSanPham = "CREATE TABLE CTSANPHAM(mactsp integer primary key autoincrement,magiay integer references GIAY(magiay),tengiay text,hinhanh text, mausac text, size integer,giaban integer,motasp text)";
        db.execSQL(dbCTSanPham);

        db.execSQL("INSERT INTO GIAY VALUES (1,'AF1','avatar1',200,10,1),(2,'AIRMAX','avatar2',100,10,2),(3,'JORDAN1','avatar3',250,10,1),(4,'JORDAN','avatar4',300,10,1),(5,'AF2','avatar5',200,10,2),(6,'AF1','avatar6',200,10,1)");
        db.execSQL("INSERT INTO LOAIGIAY VALUES (1,'JORDAN'),(2,'AF1'),(3,'NEW')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS QUANLY");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS GIAY");
            db.execSQL("DROP TABLE IF EXISTS CTSANPHAM");
            db.execSQL("DROP TABLE IF EXISTS LOAIGIAY");
            db.execSQL("DROP TABLE IF EXISTS DONHANG");
            db.execSQL("DROP TABLE IF EXISTS CTDONHANG");
        }
    }
}
