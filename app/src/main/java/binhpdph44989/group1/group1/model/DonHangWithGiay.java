package binhpdph44989.group1.group1.model;

public class DonHangWithGiay {
    private DonHang donHang;
    private Giay giay;

    public DonHangWithGiay(DonHang donHang, Giay giay) {
        this.donHang = donHang;
        this.giay = giay;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public Giay getGiay() {
        return giay;
    }
}
