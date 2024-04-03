package binhpdph44989.group1.group1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.DonHang;

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
                list.add(new DonHang(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
