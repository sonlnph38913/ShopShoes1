package binhpdph44989.group1.group1.adapterUser;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.fragmentUser.ChiTietDonHangFragment;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.DonHangItems;
import binhpdph44989.group1.group1.model.Giay;

public class DonHangAdapterUser extends RecyclerView.Adapter<DonHangAdapterUser.DonHangViewHolder> {

    private List<DonHangItems> donHangItems;
    private DonHang donHang;
    private String hoten;
    private Context context;

    public DonHangAdapterUser(List<DonHangItems> donHangItems, Context context) {
        this.donHangItems = donHangItems;
        this.context = context;
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
        int tongtien =giay.getGiaban() * giay.getSoluong();



        holder.madhTextView.setText("Mã Đơn Hàng: " + donHang.getMaDH());
        holder.ngaydatTextView.setText("Ngày Đặt Hàng: " + donHang.getNgayDat());
        holder.tenKhTextView.setText("Người Đặt: " +hoten );
        holder.tenSPTextView.setText("Tên Sản Phẩm: " + giay.getTengiay());
        holder.slSanPhamTextView.setText("Số Lượng: " + giay.getSoluong());
        holder.tongTienTextView.setText("Tổng Tiền: " + tongtien);

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

    public static class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView madhTextView;
        private TextView tenKhTextView;
        private TextView ngaydatTextView;
        private TextView slSanPhamTextView;
        private TextView tongTienTextView,tenSPTextView;

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

        }

    }
    public void ShowCTDonHang(){
        if (donHang != null) {
            Bundle bundle = new Bundle();
            bundle.putString("maDonHang", donHang.getMaDH());

            // Chuyển sang Fragment chi tiết và truyền thông tin đơn hàng
            ChiTietDonHangFragment fragment = new ChiTietDonHangFragment();
            fragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container2, fragment)
                    .addToBackStack(null)
                    .commit();
            Toast.makeText(context, "Xem Chi Tiết Thành Công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xem Thất Bại", Toast.LENGTH_SHORT).show();
        }
    }
    private FragmentActivity requireActivity() {
        if (context instanceof FragmentActivity) {
            return (FragmentActivity) context;
        } else {
            throw new IllegalStateException("Fragment is not attached to an activity.");
        }
    }


}
