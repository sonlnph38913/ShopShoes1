package binhpdph44989.group1.group1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProductRepository {
    private SQLiteDatabase database;

    public ProductRepository() {
        this.database = database;
    }

    // Thêm dữ liệu vào bảng LOAIGIAY
    public void insertLoaiGiay(String tenLoai, int trangThaiLoai) {
        ContentValues values = new ContentValues();
        values.put("tenloai", tenLoai);
        values.put("trangthailoai", trangThaiLoai);
        database.insert("LOAIGIAY", null, values);
    }

    // Thêm dữ liệu vào bảng GIAY
    public void insertGiay(String tenGiay, String hinhAnh, int size, int giaBan, int soLuongKho, int soLuong, int maLoai) {
        ContentValues values = new ContentValues();
        values.put("tengiay", tenGiay);
        values.put("hinhanh", hinhAnh);
        values.put("size", size);
        values.put("giaban", giaBan);
        values.put("soluongkho", soLuongKho);
        values.put("soluong", soLuong);
        values.put("maloai", maLoai);
        database.insert("GIAY", null, values);
    }

    // Truy vấn danh sách sản phẩm theo loại sản phẩm
    public Cursor getProductsByCategory(int maLoai) {
        String query = "SELECT * FROM GIAY WHERE maloai = ?";
        return database.rawQuery(query, new String[]{String.valueOf(maLoai)});
    }
}
