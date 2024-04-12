package binhpdph44989.group1.group1.adapterUser;


import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.fragmentUser.CTSPFragment;
import binhpdph44989.group1.group1.fragmentUser.ChiTietDonHangFragment;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.DonHangItems;
import binhpdph44989.group1.group1.model.Giay;

public class DonHangAdapterUser extends RecyclerView.Adapter<DonHangAdapterUser.DonHangViewHolder> {

    private List<DonHangItems> donHangItems;
    private DonHang donHang;
    private String hoten,phone,address;
    private Context context;

    public DonHangAdapterUser(List<DonHangItems> donHangItems, Context context) {
        this.donHangItems = donHangItems;
        this.context = context;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        DonHangItems donHangItem = donHangItems.get(position);
        Giay giay = donHangItem.getGiay();
        DonHang donHang = donHangItem.getDonHang();
        int tongtien = giay.getGiaban() * giay.getSoluong();


        holder.madhTextView.setText("Mã Đơn Hàng: " + donHang.getMaDH());
        holder.ngaydatTextView.setText("Ngày Đặt Hàng: " + donHang.getNgayDat());
        holder.tenKhTextView.setText("Người Đặt: " + hoten);
        holder.tenSPTextView.setText("Tên Sản Phẩm: " + giay.getTengiay());
        holder.slSanPhamTextView.setText("Số Lượng: " + giay.getSoluong());
        holder.tongTienTextView.setText("Tổng Tiền: " + tongtien);
        holder.SizeTextView.setText("Size" + giay.getSize());
        holder.diachitv.setText("Địa Chỉ: " + address);
        holder.sdtTextView.setText("Số Điện Thoại: " + phone);
        int imageResourceId = context.getResources().getIdentifier(giay.getHinhanh(), "drawable", context.getPackageName());

        if (imageResourceId != 0) {
            Picasso.get().load(imageResourceId).into(holder.imgDH);
        } else {
            // Use a default image resource if the specified resource is not found
            holder.imgDH.setImageResource(R.drawable.avatar1);
        }

        holder.item_donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowCTDonHang();
            }
        });
    }

    @Override
    public int getItemCount() {
        return donHangItems.size();
    }

    public void setHoTen(String hoTen) {
        this.hoten = hoTen;
        notifyDataSetChanged();
    }
    public void setPhone(String phone) {
        this.phone = phone;
        notifyDataSetChanged();
    }
    public void setAddress(String address) {
        this.address = address;
        notifyDataSetChanged();
    }

    public static class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView madhTextView;
        private TextView tenKhTextView;
        private TextView ngaydatTextView;
        private TextView slSanPhamTextView;
        private TextView tongTienTextView, tenSPTextView, sdtTextView, diachitv, SizeTextView;
        private ImageView imgDH;
        private CardView item_donhang;


        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            madhTextView = itemView.findViewById(R.id.madh);
            tenKhTextView = itemView.findViewById(R.id.tenKh);
            ngaydatTextView = itemView.findViewById(R.id.ngaydat);
            slSanPhamTextView = itemView.findViewById(R.id.SlSanPham);
            tongTienTextView = itemView.findViewById(R.id.TongTienDh);
            tenSPTextView = itemView.findViewById(R.id.tenSP);
            item_donhang = itemView.findViewById(R.id.item_donhang);
            sdtTextView = itemView.findViewById(R.id.SDT);
            diachitv = itemView.findViewById(R.id.DiaChi);
            SizeTextView = itemView.findViewById(R.id.SizeDh);
            imgDH = itemView.findViewById(R.id.imgDh);

        }

    }

    public void ShowCTDonHang() {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, ChiTietDonHangFragment.newInstance(donHang));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
