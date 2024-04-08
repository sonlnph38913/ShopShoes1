package binhpdph44989.group1.group1.adapterUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.Giay;

public class DonHangAdapterUser extends RecyclerView.Adapter<DonHangAdapterUser.DonHangViewHolder> {
       
       private List<Giay>selectedItems;

    public DonHangAdapterUser(Context context, List<Giay> selectedItems) {
       
        this.selectedItems = selectedItems ;
    }

    public DonHangAdapterUser(ArrayList<Giay> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        Giay giay = selectedItems.get(position);

        holder.ngaydatTextView.setText("Ngày Đặt Hàng " + giay.getTengiay());

    }

    @Override
    public int getItemCount() {
        return selectedItems.size();
    }

    static class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView madhTextView;
        private TextView tenKhTextView;
        private TextView ngaydatTextView;
        private TextView slSanPhamTextView;
        private TextView tongTienTextView;

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            madhTextView = itemView.findViewById(R.id.madh);
            tenKhTextView = itemView.findViewById(R.id.tenKh);
            ngaydatTextView = itemView.findViewById(R.id.ngaydat);
            slSanPhamTextView = itemView.findViewById(R.id.SlSanPham);
            tongTienTextView = itemView.findViewById(R.id.TongTienDh);
        }

        public void bind(DonHang donHang) {
            madhTextView.setText("Mã Đơn Hàng: " + donHang.getMaDH());
            tenKhTextView.setText("Tên Người Đặt: " + donHang.getHoTen());
            ngaydatTextView.setText("Ngày Đặt: " + donHang.getNgayDat());
//            slSanPhamTextView.setText("Số Sản Phẩm: " + donHang.getSoLuong());
            tongTienTextView.setText("Thành Tiền: " + donHang.getTongTien());
        }
    }
}
