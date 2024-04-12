package binhpdph44989.group1.group1.fragmentUser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.model.DonHang;


public class ChiTietDonHangFragment extends Fragment {
    DonHang donHang =new DonHang();
    TextView txtMadh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_don_hang, container, false);
        txtMadh = view.findViewById(R.id.txtMaDhCt);
        // Lấy thông tin đơn hàng từ bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String maDonHang = bundle.getString("maDonHang");
            maDonHang = donHang.getMaDH();
            // Sử dụng mã đơn hàng để truy vấn cơ sở dữ liệu hoặc lấy thông tin từ nguồn dữ liệu khác
            // Hiển thị chi tiết của đơn hàng trên giao diện
        }
        txtMadh.setText("Mã Đơn Hàng: " + donHang.getMaDH() );

        return view;
    }
}