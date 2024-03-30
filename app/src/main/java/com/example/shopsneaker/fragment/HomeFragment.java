package com.example.shopsneaker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopsneaker.R;
import com.example.shopsneaker.adapter.GiayAdapter;
import com.example.shopsneaker.dao.GiayDao;
import com.example.shopsneaker.model.Giay;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    GiayAdapter adapter;
    ArrayList<Giay> list;
    RecyclerView rcvGiay;
    GiayDao giayDao;
    ImageView imgInfo, GioHang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imgInfo = view.findViewById(R.id.imgInfo);
        GioHang = view.findViewById(R.id.GioHang);
        rcvGiay = view.findViewById(R.id.rcvGiay);
        SearchView svGiay = view.findViewById(R.id.svGiay);
        giayDao = new GiayDao(getContext());
        loadData();
        //seacrh
        svGiay.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Giay> listFilter = new ArrayList<>();
                for (Giay giay : giayDao.getDSGiay()) {
                    if (giay.getTengiay().toLowerCase().contains(newText.toLowerCase())) {
                        listFilter.add(giay);
                    }
                }
                loadSearch(listFilter);

                return false;
            }
        });

        //chuyển info
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //chuyển giỏ hàng
        GioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;


    }

    //data sản phẩm
    private void loadData() {
        list = giayDao.getDSGiay();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new GiayAdapter(getContext(), list, giayDao);
        rcvGiay.setLayoutManager(gridLayoutManager);
        rcvGiay.setAdapter(adapter);
    }

    //data search
    private void loadSearch(ArrayList<Giay> listg) {
        GiayAdapter adapter = new GiayAdapter(getContext(), listg, giayDao);
        rcvGiay.setAdapter(adapter);
    }
}