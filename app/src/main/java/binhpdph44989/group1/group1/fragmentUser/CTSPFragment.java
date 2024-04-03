package binhpdph44989.group1.group1.fragmentUser;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import binhpdph44989.group1.group1.CartViewModel;
import binhpdph44989.group1.group1.MainActivityUser;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.Giay;


public class CTSPFragment extends Fragment {
    private CartViewModel cartViewModel;

    public Giay giay;

    public  CTSPFragment(){

    }
    public static CTSPFragment newInstance(Giay giay){
        CTSPFragment ctspFragment = new CTSPFragment();
        Bundle args = new Bundle();
        args.putParcelable("magiay", (Parcelable) giay);
        ctspFragment.setArguments(args);
        return ctspFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_t_s_p,container,false);

        if (getArguments() != null){
            giay = getArguments().getParcelable("magiay");
            if (giay != null){
            displayCTSanPham(view);
            }
        }

        //them vao gio hang
        Button btnAddCart = view.findViewById(R.id.addGioHang);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartViewModel != null){
                    cartViewModel.addToCart(giay);
                    Toast.makeText(getContext(), "Đã Thêm Sản Phẩm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
    private  void displayCTSanPham(View view){
        TextView txtName = view.findViewById(R.id.txtTenGiayCt);
        TextView txtGia = view.findViewById(R.id.txtGiaBanCt);
        TextView txtSize = view.findViewById(R.id.txtSizeCt);
        ImageView imgGiayCt = view.findViewById(R.id.imgGiayCt);

// Lấy ID của ảnh từ tệp drawable
        int imageResourceId = getResources().getIdentifier(giay.getHinhanh(), "drawable", getContext().getPackageName());

// Kiểm tra xem ID ảnh có hợp lệ không
        if (imageResourceId != 0) {
            // Sử dụng Picasso để hiển thị ảnh
            Picasso.get().load(imageResourceId).into(imgGiayCt);
        } else {
            // Nếu không tìm thấy ảnh, bạn có thể hiển thị ảnh mặc định hoặc xử lý tùy thuộc vào yêu cầu của bạn
            // Ví dụ:
            imgGiayCt.setImageResource(R.drawable.avatar1);
        }
        txtName.setText(giay.getTengiay());
        txtGia.setText("Giá Bán:" + giay.getGiaban());
        txtSize.setText("Size:" + giay.getSize());
    }
    public void onAttach(Context context){
        super.onAttach(context);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
    }

}