package binhpdph44989.group1.group1.fragmentUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import binhpdph44989.group1.group1.CartViewModel;
import binhpdph44989.group1.group1.DonHangViewModel;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterUser.DonHangAdapterUser;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.DonHangItems;
import binhpdph44989.group1.group1.model.Giay;

public class DonHangFragment extends Fragment {
    private CartViewModel cartViewModel;
    private DonHangAdapterUser adapter;
    private List<DonHangItems> donHangItemList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);
        RecyclerView rcv_donhang = view.findViewById(R.id.rcvDonHang);
        rcv_donhang.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DonHangAdapterUser(donHangItemList,getContext());
        rcv_donhang.setAdapter(adapter);



        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), new Observer<List<Giay>>() {
            @Override
            public void onChanged(List<Giay> giayList) {
                for (Giay giay : giayList) {
                    DonHang donHang = new DonHang(); // Thay thế bằng cách lấy dữ liệu từ ViewModel hoặc database của bạn
                    DonHangItems donHangItem = new DonHangItems(giay, donHang);
                    donHangItemList.add(donHangItem);
                }
                adapter.notifyDataSetChanged();
            }
        });
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getHoTen().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String hoTen) {
                adapter.setHoTen(hoTen);

            }
        });
        cartViewModel.getPhone().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String phone) {
                adapter.setPhone(phone);

            }
        });
        cartViewModel.getAddress().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String address) {
                adapter.setAddress(address);

            }
        });
    }


}
