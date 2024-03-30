package com.example.shopsneaker.model;

public class Giay {
    private int magiay;
    private String tengiay;
    private String hinhanh;
    private int giaban;
    private int soluong;
    private int maloai;

    public Giay() {
    }

    public Giay(int magiay, String tengiay, String hinhanh, int giaban, int soluong, int maloai) {
        this.magiay = magiay;
        this.tengiay = tengiay;
        this.hinhanh = hinhanh;
        this.giaban = giaban;
        this.soluong = soluong;
        this.maloai = maloai;
    }

    public int getMagiay() {
        return magiay;
    }

    public void setMagiay(int magiay) {
        this.magiay = magiay;
    }

    public String getTengiay() {
        return tengiay;
    }

    public void setTengiay(String tengiay) {
        this.tengiay = tengiay;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }


}
