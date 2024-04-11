package binhpdph44989.group1.group1.fragmentUser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.ProductViewModel;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterUser.GiayAdapterUser;
import binhpdph44989.group1.group1.dao.GiayDao;
import binhpdph44989.group1.group1.model.Giay;


public class DanhMuc1Fragment extends Fragment {
    private RecyclerView rcv_DanhMuc1;
    private GiayAdapterUser adapter;
    GiayDao giayDao;
    ArrayList<Giay>list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_muc1, container, false);
        rcv_DanhMuc1 = view.findViewById(R.id.rcvDm1);
        rcv_DanhMuc1.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GiayAdapterUser(getContext(),list,giayDao);
        rcv_DanhMuc1.setAdapter(adapter);
        loadProductsByCategory(); // Load sản phẩm theo mã loại giày
        return view;
    }
    private void loadProductsByCategory() {
        // Sử dụng ViewModel hoặc Repository để lấy danh sách sản phẩm theo mã loại giày
        // Ví dụ:
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductsByCategory(getId()).observe(getViewLifecycleOwner(), new Observer<List<Giay>>() {
            @Override
            public void onChanged(List<Giay> products) {
                adapter.setGiay(products);
            }
        });
    }
}