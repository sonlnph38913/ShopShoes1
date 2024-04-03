package binhpdph44989.group1.group1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.Giay;

public class GiayDao {
    DbHelper dbhelper;
    public GiayDao(Context context){dbhelper = new DbHelper(context);}
    public ArrayList<Giay>getDSGiay(){
        ArrayList<Giay>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT g.magiay,g.tengiay,g.hinhanh,g.size,g.giaban,g.soluong,lo.tenloai FROM GIAY g,LOAIGIAY lo WHERE g.maloai = lo.maloai ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String imgName = cursor.getString(2);
            int size = cursor.getInt(3);
            int gia = cursor.getInt(4);
            int soluong = cursor.getInt(5);
            int maloai = cursor.getInt(6);
            Giay g = new Giay(id,name,imgName,size,gia,soluong,maloai);
            list.add(g);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
