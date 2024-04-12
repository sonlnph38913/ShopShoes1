package binhpdph44989.group1.group1.adapterUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.DonHangItems;
import binhpdph44989.group1.group1.model.Giay;

public class DonHangAdapterUser extends RecyclerView.Adapter<DonHangAdapterUser.DonHangViewHolder> {

    private List<DonHangItems> donHangItems;
    private String hoten;

    public DonHangAdapterUser(List<DonHangItems> donHangItems) {
        this.donHangItems = donHangItems;
    }


    //       private ArrayList<DonHang>list = new ArrayList<>();
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


//        // Hiển thị thông tin trên giao diện
        holder.madhTextView.setText("Mã Đơn Hàng: " + donHang.getMaDH());
        holder.ngaydatTextView.setText("Ngày Đặt Hàng: " + donHang.getNgayDat());
        holder.tenKhTextView.setText("Người Đặt: " +hoten );
        holder.tenSPTextView.setText("Tên Sản Phẩm: " + giay.getTengiay());
        holder.slSanPhamTextView.setText("Số Lượng: " + giay.getSoluong());
        holder.tongTienTextView.setText("Tổng Tiền: " + tongtien);
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

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            madhTextView = itemView.findViewById(R.id.madh);
            tenKhTextView = itemView.findViewById(R.id.tenKh); 
           ngaydatTextView = itemView.findViewById(R.id.ngaydat);
            slSanPhamTextView = itemView.findViewById(R.id.SlSanPham);
          tongTienTextView = itemView.findViewById(R.id.TongTienDh);
          tenSPTextView = itemView.findViewById(R.id.tenSP);
        }

        public void bind(DonHang donHang) {
            madhTextView.setText("Mã Đơn Hàng: " + donHang.getMaDH());
            tenKhTextView.setText("Tên Người Đặt: " + donHang.getHoTen());
            ngaydatTextView.setText("Ngày Đặt: " + donHang.getNgayDat());
//            slSanPhamTextView.setText("Số Sản Phẩm: " + donHang.getSoLuong());
//            tongTienTextView.setText("Thành Tiền: " + donHang.getTongTien());
        }
    }
}
