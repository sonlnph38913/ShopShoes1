package binhpdph44989.group1.group1.model;

public class DonHang {
    private int maDH;
    private String hoTen;
    private String tenGiay;
    private String ngayDat;
    private int tongTien;

    public DonHang(int maDH, String hoTen, String tenGiay, String ngayDat, int tongTien) {
        this.maDH = maDH;
        this.hoTen = hoTen;
        this.tenGiay = tenGiay;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
