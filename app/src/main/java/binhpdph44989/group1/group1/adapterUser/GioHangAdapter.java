package binhpdph44989.group1.group1.adapterUser;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.fragmentUser.GioHangFragment;
import binhpdph44989.group1.group1.model.Giay;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private ArrayList<Giay> giaylist;
    private Context context;
    private GioHangFragment gioHangFragment;

    public GioHangAdapter(ArrayList<Giay> giaylist, Context context, GioHangFragment gioHangFragment) {
        this.giaylist = giaylist;
        this.context = context;
        this.gioHangFragment = gioHangFragment; // Gán giá trị cho biến gioHangFragment
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Giay giay = giaylist.get(position);
        holder.bind(giay,position);
        holder.cbPro.setChecked(giay.isSelected());

        // Xử lý sự kiện khi người dùng thay đổi trạng thái của checkbox


    }



    @Override
    public int getItemCount() {
        return giaylist.size();
    }

    public void setList(ArrayList<Giay> giaylist) {
        this.giaylist = giaylist;
        notifyDataSetChanged();
    }

    public ArrayList<Giay> getGiayList() {
        return giaylist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTenGh, txtGiaGh, txtSizeGh;
        ImageView imgGh;
        CheckBox cbPro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenGh = itemView.findViewById(R.id.txtTenGh);
            txtGiaGh = itemView.findViewById(R.id.txtGiaGh);
            txtSizeGh = itemView.findViewById(R.id.txtSizeGh);
            imgGh = itemView.findViewById(R.id.imgHinhAnhGh);
            cbPro = itemView.findViewById(R.id.cbPro);
        }

        public void bind(Giay giay,int position) {
            txtTenGh.setText(giay.getTengiay());
            txtGiaGh.setText(String.valueOf("Giá:" + giay.getGiaban() + "$"));
            txtSizeGh.setText(String.valueOf("Size:" + giay.getSize()));
            String imgName = giay.getHinhanh();
            int resId = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
            imgGh.setImageResource(resId);

            cbPro.setOnCheckedChangeListener((buttonView, isChecked) -> {
                // Cập nhật trạng thái của sản phẩm
                giaylist.get(position).setSelected(isChecked);


                gioHangFragment.calculateTotalPrice();
            });



        }
    }
}
