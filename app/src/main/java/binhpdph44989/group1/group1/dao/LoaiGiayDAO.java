package binhpdph44989.group1.group1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.LoaiGiay;

public class LoaiGiayDAO {
    DbHelper dbHelper;

    public LoaiGiayDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiGiay> getDSLoaiGiay() {
        ArrayList<LoaiGiay> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAIGIAY", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiGiay(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoaiGiay(String tenloai, int trangthailoai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);
        contentValues.put("trangthailoai", trangthailoai);
        long check = sqLiteDatabase.insert("LOAIGIAY", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }


    public boolean kiemTraTenLoaiGiayTonTai(String tenLoai) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"maloai"};
        String selection = "tenloai = ?";
        String[] selectionArgs = {tenLoai};

        Cursor cursor = db.query(
                "LOAIGIAY",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean capNhatLoaiGiay(int maloai, String tenLoai, int trangThaiLoai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenLoai);
        contentValues.put("trangthailoai", trangThaiLoai);
        long check = sqLiteDatabase.update("LOAIGIAY", contentValues, "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return false;
        return true;
    }
}
