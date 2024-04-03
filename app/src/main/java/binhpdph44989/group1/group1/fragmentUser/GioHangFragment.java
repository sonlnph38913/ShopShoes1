package binhpdph44989.group1.group1.fragmentUser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.CartViewModel;
import binhpdph44989.group1.group1.MainActivityUser;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterUser.GioHangAdapter;
import binhpdph44989.group1.group1.model.Giay;


public class GioHangFragment extends Fragment {
    private CartViewModel cartViewModel;
    RecyclerView recyclerView;
    GioHangAdapter adapter;
    TextView txtTongTien;
    CheckBox cbAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        recyclerView = view.findViewById(R.id.rcvGioHang);
        txtTongTien = view.findViewById(R.id.txtTongTien);
        cbAll = view.findViewById(R.id.cbAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GioHangAdapter(new ArrayList<>(),getContext(),this);
        recyclerView.setAdapter(adapter);
        cbAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Cập nhật trạng thái của tất cả sản phẩm trong danh sách
            for (Giay giay : adapter.getGiayList()) {
                giay.setSelected(isChecked);
            }
            // Tính toán tổng giá bán của các sản phẩm đã chọn
            calculateTotalPrice();
            // Cập nhật lại giao diện
            adapter.notifyDataSetChanged();
        });


        return view;
    }
    public void calculateTotalPrice() {
        int totalPrice = 0;
        for (Giay giay : adapter.getGiayList()) {
            if (giay.isSelected()) {
                totalPrice += giay.getGiaban();
            }
        }
        txtTongTien.setText("Tổng tiền: " + totalPrice + "$");
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), new Observer<List<Giay>>() {
            @Override
            public void onChanged(List<Giay> giayList) {
                adapter.setList((ArrayList<Giay>) giayList);
            }
        });
    }

}


