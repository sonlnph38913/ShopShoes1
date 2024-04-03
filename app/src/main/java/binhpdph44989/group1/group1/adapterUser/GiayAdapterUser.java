package binhpdph44989.group1.group1.adapterUser;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.dao.GiayDao;
import binhpdph44989.group1.group1.fragmentUser.CTSPFragment;
import binhpdph44989.group1.group1.model.Giay;

public class GiayAdapterUser extends RecyclerView.Adapter<GiayAdapterUser.ViewHolder> {
    private Context context;
    private ArrayList<Giay>list;
    private GiayDao giayDao;
    public GiayAdapterUser(Context context, ArrayList<Giay> list, GiayDao giayDao) {
        this.context = context;
        this.list = list;
        this.giayDao = giayDao;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_giay,parent,false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Giay g = list.get(position);
        holder.txtName.setText(g.getTengiay());
        holder.txtGia.setText("Giá: " + String.valueOf(g.getGiaban()) + "$");
        String imgName = g.getHinhanh();
        int resId = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
        holder.imgGiay.setImageResource(resId);
        holder.item_giay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, CTSPFragment.newInstance(g));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgGiay;
        TextView txtName,txtGia;
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
