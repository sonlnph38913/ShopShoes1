package binhpdph44989.group1.group1.fragmentUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterUser.DonHangAdapterUser;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.Giay;

public class DonHangFragment extends Fragment {
    private RecyclerView recyclerView;
    private DonHangAdapterUser adapter;
    private List<DonHang> donHangList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);
        recyclerView = view.findViewById(R.id.rcvDonHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DonHangAdapterUser(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Lấy danh sách sản phẩm đã chọn từ bundle
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("selectedItems")) {
            ArrayList<Giay> selectedItems = bundle.getParcelableArrayList("selectedItems");
//            donHangList = createDonHangList(selectedItems);
            adapter.setDonHangList(donHangList);
        }

        return view;
    }

//    private List<DonHang> createDonHangList(List<Giay> selectedItems) {
//        List<DonHang> donHangList = new ArrayList<>();
//        for (Giay giay : selectedItems) {
//            DonHang existingOrder = findExistingOrder(donHangList, giay);
//            if (existingOrder != null) {
//                existingOrder.increaseQuantity();
//            } else {
//                DonHang newOrder = new DonHang();
//                newOrder.setSanPham(giay);
//                newOrder.setSoLuong(1);
//                donHangList.add(newOrder);
//            }
//        }
//        return donHangList;
//    }
//
//    private DonHang findExistingOrder(List<DonHang> donHangList, Giay giay) {
//        for (DonHang donHang : donHangList) {
//            if (donHang.getSanPham().getMagiay() == giay.getMagiay()) {
//                return donHang;
//            }
//        }
//        return null;
//    }
}
