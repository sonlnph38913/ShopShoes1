package com.example.shopsneaker.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shopsneaker.database.Dbhelper;
import com.example.shopsneaker.model.Giay;

import java.util.ArrayList;

public class GiayDao {
    Dbhelper dbhelper;

    public GiayDao(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<Giay> getDSGiay() {
        ArrayList<Giay> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT g.magiay,g.tengiay,g.hinhanh,g.giaban,g.soluong,lo.tenloai FROM GIAY g,LOAIGIAY lo WHERE g.maloai = lo.maloai ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String imgName = cursor.getString(2);
            int gia = cursor.getInt(3);
            int soluong = cursor.getInt(4);
            int maloai = cursor.getInt(5);
            Giay g = new Giay(id, name, imgName, gia, soluong, maloai);
            list.add(g);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
