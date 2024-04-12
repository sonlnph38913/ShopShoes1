package binhpdph44989.group1.group1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.Giay;

public class DonHangDAO {
    DbHelper dbHelper;

    public DonHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<DonHang> getDSDonHang(){
        ArrayList<DonHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT dh.madh, kh.hoten, g.tengiay, ct.ngaydat, ct.tongtien FROM DONHANG dh INNER JOIN KHACHHANG kh ON dh.makh = kh.makh INNER JOIN CTDONHANG ct ON dh.madh = ct.madh INNER JOIN GIAY g ON ct.magiay = g.magiay", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new DonHang());
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void insert(DonHang donHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", donHang.getHoTen());
        values.put("diaChi", donHang.getDiaChi());
        // Thêm các giá trị khác cần thiết nếu có

        // Chèn dữ liệu vào bảng đơn hàng
        db.insert("DONHANG", null, values);
        // Đóng kết nối cơ sở dữ liệu
        db.close();
    }
}
