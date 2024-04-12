package binhpdph44989.group1.group1.adapterUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.CartViewModel;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.dao.DonHangDAO;
import binhpdph44989.group1.group1.fragmentUser.GioHangFragment;
import binhpdph44989.group1.group1.model.Giay;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private ArrayList<Giay> giaylist;
    private Context context;
    private GioHangFragment gioHangFragment;
    private DonHangDAO donHangDAO;
    private CartViewModel cartViewModel;

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

        holder.btnThemSl.setOnClickListener(view -> {
            if (giay.getSoluong() < giay.getSoluongkho()) {
                giay.setSoluong(giay.getSoluong() + 1); // Tăng số lượng lên 1
                notifyDataSetChanged(); // Cập nhật lại RecyclerView
                updateProductQuantity(giay, giay.getSoluongkho());
                gioHangFragment.calculateTotalPrice(); // Tính lại tổng tiền
            } else {
                Toast.makeText(context, "Số lượng vượt quá số lượng trong kho", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnGiamsl.setOnClickListener(view -> {
            if (giay.getSoluong() > 1) { // Kiểm tra nếu số lượng lớn hơn 1
                giay.setSoluong(giay.getSoluong() - 1); // Giảm số lượng đi 1
                notifyDataSetChanged(); // Cập nhật lại RecyclerView
                gioHangFragment.calculateTotalPrice(); // Tính lại tổng tiền
                updateProductQuantity(giay, giay.getSoluongkho());

            } else {
                // Hiển thị thông báo cho người dùng rằng số lượng không thể nhỏ hơn 1
                showDeleteDialog(position);
            }
        });
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

    public List<Giay> getSelectedItems() {
        List<Giay> selectedItems = new ArrayList<>();
        for (Giay giay : giaylist) {
            if (giay.isSelected()) {
                selectedItems.add(giay);
            }
        }
        return selectedItems;
    }

    public void removeGiay(Giay giay) {

        int index = giaylist.indexOf(giay);
        if (index != -1) {
            giaylist.remove(index);
            notifyItemRemoved(index);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTenGh, txtGiaGh, txtSizeGh,txtSoLuong,txtSLKho;
        ImageView imgGh;
        CheckBox cbPro;
        ImageView btnThemSl,btnGiamsl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenGh = itemView.findViewById(R.id.txtTenGh);
            txtGiaGh = itemView.findViewById(R.id.txtGiaGh);
            txtSizeGh = itemView.findViewById(R.id.txtSizeGh);
            imgGh = itemView.findViewById(R.id.imgHinhAnhGh);
            cbPro = itemView.findViewById(R.id.cbPro);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongGh);
            btnGiamsl = itemView.findViewById(R.id.btnGiamSl);
            btnThemSl = itemView.findViewById(R.id.btnTangSl);
            txtSLKho = itemView.findViewById(R.id.txtSLKho);


        }

        public void bind(Giay giay,int position) {
            txtTenGh.setText(giay.getTengiay());
            txtGiaGh.setText(String.valueOf("Giá:" + giay.getGiaban() + "$"));
            txtSizeGh.setText(String.valueOf("Size:" + giay.getSize()));
            txtSoLuong.setText(String.valueOf("SL: " + giay.getSoluong()));
            txtSLKho.setText(String.valueOf("SL Kho: " + (giay.getSoluongkho())));

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

    public Giay removeItem(int position) {
        Giay removedItem = giaylist.get(position);
        giaylist.remove(position);
        notifyItemRemoved(position);
        return removedItem;
    }
    private void showDeleteDialog(int position) {
        // Tạo dialog hỏi người dùng có muốn xóa hay không
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            // Nếu người dùng chọn "Có", thực hiện xóa sản phẩm và cập nhật giỏ hàng
            removeItem(position);
            gioHangFragment.calculateTotalPrice();
        });
        builder.setNegativeButton("Không", (dialog, which) -> {
            // Nếu người dùng chọn "Không", đóng dialog
            dialog.dismiss();
        });
        // Hiển thị dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public int countSelectedItems() {
        int count = 0;
        for (Giay giay : giaylist) {
            if (giay.isSelected()) {
                count++;
            }
        }
        return count;
    }
    public void updateProductQuantity(Giay giay, int newQuantity) {
        // Gọi phương thức cập nhật số lượng trong kho của sản phẩm tương ứng (có thể là gọi API hoặc thao tác trực tiếp với cơ sở dữ liệu)
        // Ví dụ:
        giay.setSoluongkho(newQuantity);
        // Lưu ý: Đây chỉ là ví dụ cơ bản, bạn cần thay thế bằng phương thức cập nhật thực tế trong ứng dụng của bạn.
    }


}
