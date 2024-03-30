package com.example.shopsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopsneaker.R;
import com.example.shopsneaker.dao.GiayDao;
import com.example.shopsneaker.model.Giay;

import java.util.ArrayList;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Giay> list;
    private final GiayDao giayDao;

    public GiayAdapter(Context context, ArrayList<Giay> list, GiayDao giayDao) {
        this.context = context;
        this.list = list;
        this.giayDao = giayDao;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_giay, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Giay g = list.get(position);
        holder.txtName.setText(g.getTengiay());
        holder.txtGia.setText("Giá: " + g.getGiaban() + "$");
        String imgName = g.getHinhanh();
        int resId = ((Activity) context).getResources().getIdentifier(imgName, "drawable", ((Activity) context).getPackageName());
        holder.imgGiay.setImageResource(resId);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGiay;
        TextView txtName, txtGia;
        CardView item_giay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGiay = itemView.findViewById(R.id.imgGiay);
            txtName = itemView.findViewById(R.id.txtTenGiay);
            txtGia = itemView.findViewById(R.id.txtGiaBan);

            item_giay = itemView.findViewById(R.id.item_giay);
        }

    }
}
