package binhpdph44989.group1.group1.model;

import android.os.Build;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class DonHang {
    private static int nextMaDH = 1;
        private int maDH;
        private String hoTen;
        private String soDienThoai;
        private String diaChi;
        private LocalDate ngayDat;
        private List<Giay> danhSachSanPham;

        public DonHang() {
            this.maDH = nextMaDH++;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.ngayDat = LocalDate.now();
            }
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

        public String getSoDienThoai() {
            return soDienThoai;
        }

        public void setSoDienThoai(String soDienThoai) {
            this.soDienThoai = soDienThoai;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public void setDiaChi(String diaChi) {
            this.diaChi = diaChi;
        }

        public LocalDate getNgayDat() {
            return ngayDat;
        }

        public void setNgayDat(LocalDate ngayDat) {
            this.ngayDat = ngayDat;
        }

        public List<Giay> getDanhSachSanPham() {
            return danhSachSanPham;
        }

        public void setDanhSachSanPham(List<Giay> danhSachSanPham) {
            this.danhSachSanPham = danhSachSanPham;
        }

        public void themSanPham(Giay giay) {
            danhSachSanPham.add(giay);
        }

        public void xoaSanPham(Giay giay) {
            danhSachSanPham.remove(giay);
        }
    }


