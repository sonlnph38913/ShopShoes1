package binhpdph44989.group1.group1.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Giay implements Parcelable {
    private int magiay;
    private String tengiay;
    private String hinhanh;
    private int size;
    private int giaban;
    private int soluongkho;
    private  int soluong;
    private int maloai;

    public Giay() {
    }

    public Giay(int magiay, String tengiay, String hinhanh, int size, int giaban, int soluongkho, int soluong, int maloai) {
        this.magiay = magiay;
        this.tengiay = tengiay;
        this.hinhanh = hinhanh;
        this.size = size;
        this.giaban = giaban;
        this.soluongkho = soluongkho;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public int getSoluongkho() {
        return soluongkho;
    }

    public void setSoluongkho(int soluongkho) {
        this.soluongkho = soluongkho;
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

    protected Giay(Parcel in) {
        magiay = in.readInt();
        tengiay = in.readString();
        hinhanh = in.readString();
        size = in.readInt();
        giaban = in.readInt();
        soluongkho = in.readInt();
        soluong = in.readInt();
        maloai = in.readInt();


    }

    public static final Creator<Giay> CREATOR = new Creator<Giay>() {
        @Override
        public Giay createFromParcel(Parcel in) {
            return new Giay(in);
        }

        @Override
        public Giay[] newArray(int size) {
            return new Giay[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(magiay);
        dest.writeString(tengiay);
        dest.writeString(hinhanh);
        dest.writeInt(size);
        dest.writeInt(giaban);
        dest.writeInt(soluongkho);
        dest.writeInt(soluong);
        dest.writeInt(maloai);
    }



    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public boolean setSelected(boolean selected) {
        isSelected = selected;
        return selected;
    }


}
