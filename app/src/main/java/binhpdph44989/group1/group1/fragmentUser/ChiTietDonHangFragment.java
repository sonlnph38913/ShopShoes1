package binhpdph44989.group1.group1.fragmentUser;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import binhpdph44989.group1.group1.CartViewModel;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.Giay;


public class ChiTietDonHangFragment extends Fragment {
    private CartViewModel cartViewModel;

    public Giay giay;
    public DonHang donHang;

    public ChiTietDonHangFragment(){

    }
    public static ChiTietDonHangFragment newInstance(DonHang donHang) {
        ChiTietDonHangFragment chiTietDonHangFragment = new ChiTietDonHangFragment();
        Bundle args = new Bundle();
        args.putParcelable("maDH", (Parcelable) donHang);
        chiTietDonHangFragment.setArguments(args);
        return chiTietDonHangFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_don_hang, container, false);
        if (getArguments() != null){
            donHang = getArguments().getParcelable("maDH");
            if (donHang != null){
                displayCTDonHang(view);
            }
        }

        return view;
    }
    public void displayCTDonHang(View view){
        TextView txtMaDH = view.findViewById(R.id.txtMaDhCt);
        txtMaDH.setText("Mã Đơn Hàng: " + donHang.getNgayDat());
    }
    public void onAttach(Context context){
        super.onAttach(context);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
    }
}