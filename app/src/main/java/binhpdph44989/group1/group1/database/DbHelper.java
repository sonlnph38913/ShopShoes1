package binhpdph44989.group1.group1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"NIKE",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbQuanLy = "CREATE TABLE QUANLY (maql INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT, matkhau TEXT, loaitaikhoan TEXT)";
        db.execSQL(dbQuanLy);

        String dbKhachHang = "CREATE TABLE KHACHHANG (makh INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT, taikhoan TEXT, matkhau TEXT, sdt TEXT, diachi TEXT)";
        db.execSQL(dbKhachHang);

        String dbLoaiGiay = "CREATE TABLE LOAIGIAY (maloai INTEGER PRIMARY KEY AUTOINCREMENT, tenloai TEXT, trangthailoai INTEGER)";
        db.execSQL(dbLoaiGiay);

        String dbGiay = "CREATE TABLE GIAY (magiay INTEGER PRIMARY KEY AUTOINCREMENT, tengiay TEXT, hinhanh TEXT, size INTEGER, giaban INTEGER,soluongkho INTEGER, soluong INTEGER, maloai INTEGER, FOREIGN KEY(maloai) REFERENCES LOAIGIAY(maloai))";
        db.execSQL(dbGiay);

        String dbCTDonHang = "CREATE TABLE CTDONHANG (madh INTEGER PRIMARY KEY AUTOINCREMENT, makh INTEGER, magiay INTEGER, ngaydat TEXT, trangthaidonhang INTEGER, tongtien INTEGER, FOREIGN KEY(makh) REFERENCES KHACHHANG(makh), FOREIGN KEY(magiay) REFERENCES GIAY(magiay))";
        db.execSQL(dbCTDonHang);

        String dbDonHang = "CREATE TABLE DONHANG (madh INTEGER PRIMARY KEY AUTOINCREMENT, makh INTEGER, ngaydat TEXT, FOREIGN KEY(makh) REFERENCES KHACHHANG(makh))";
        db.execSQL(dbDonHang);

        String dbCTSanPham = "CREATE TABLE CTSANPHAM (mactsp INTEGER PRIMARY KEY AUTOINCREMENT, magiay INTEGER, tengiay TEXT, hinhanh TEXT, size INTEGER, giaban INTEGER, motasp TEXT, FOREIGN KEY(magiay) REFERENCES GIAY(magiay))";
        db.execSQL(dbCTSanPham);
        String dbGioHang = "CREATE TABLE GIOHANG (magiohang INTEGER PRIMARY KEY AUTOINCREMENT, magiay INTEGER,hinhanh TEXT, Size INTERGER, soluong INTEGER, FOREIGN KEY(magiay) REFERENCES GIAY(magiay))";
        db.execSQL(dbGioHang);
        db.execSQL("INSERT INTO LOAIGIAY VALUES (1,'JORDAN',0),(2,'AF1',0),(3,'NEW',1)");
        db.execSQL("INSERT INTO GIAY VALUES (1,'AF1','avatar1',42,200,10,1,1),(2,'AIRMAX','avatar2',37,100,11,1,2),(3,'JORDAN1','avatar3',40,250,12,1,1),(4,'JORDAN','avatar4',40,300,15,1,1),(5,'AF2','avatar5',39,200,19,1,2),(6,'AF1','avatar6',44,200,21,1,1),(7,'AF12','avatar7',43,200,9,1,1),(8,'NIKE1','avatar8',42,200,7,1,2),(9,'AIRMIN','avatar9',38,900,17,1,2),(10,'AF123','avatar10',41,200,6,1,1),(11,'TRUNK','avatar11',40,400,23,1,1),(12,'JORDAN!','avatar12',39,200,13,1,2)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS QUANLY");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS GIAY");
            db.execSQL("DROP TABLE IF EXISTS CTSANPHAM");
            db.execSQL("DROP TABLE IF EXISTS LOAIGIAY");
            db.execSQL("DROP TABLE IF EXISTS DONHANG");
            db.execSQL("DROP TABLE IF EXISTS CTDONHANG");
            onCreate(db);
        }
    }

}
