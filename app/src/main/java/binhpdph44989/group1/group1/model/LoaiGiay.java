package binhpdph44989.group1.group1.model;

public class LoaiGiay {
    private int maloai;
    private String tenloai;
    private int trangthailoai;

    public LoaiGiay(int maloai, String tenloai, int trangthailoai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.trangthailoai = trangthailoai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getTrangthailoai() {
        return trangthailoai;
    }

    public void setTrangthailoai(int trangthailoai) {
        this.trangthailoai = trangthailoai;
    }
}
