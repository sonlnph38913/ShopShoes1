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

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.CartViewModel;
import binhpdph44989.group1.group1.MainActivityUser;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterUser.GioHangAdapter;
import binhpdph44989.group1.group1.dao.DonHangDAO;
import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.Giay;


public class CTSPFragment extends Fragment {
    private CartViewModel cartViewModel;

    public Giay giay;

    public GioHangAdapter adapter;
    DonHangDAO donHangDAO;
    ArrayList<Giay> selectedItems = new ArrayList<>();

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
                if (giay.getSoluongkho()>0) {
                    if (cartViewModel != null) {

                        cartViewModel.addToCart(giay);
//                    cartViewModel.updateStock(selectedItems);
                        Toast.makeText(getContext(), "Đã Thêm Sản Phẩm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Hết Hàng", Toast.LENGTH_SHORT).show();
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
        TextView txtSoluongSp = view.findViewById(R.id.txtSoluongSp);


        int imageResourceId = getResources().getIdentifier(giay.getHinhanh(), "drawable", getContext().getPackageName());


        if (imageResourceId != 0) {
            Picasso.get().load(imageResourceId).into(imgGiayCt);
        } else {
            imgGiayCt.setImageResource(R.drawable.avatar1);
        }
        txtName.setText("Tên Giày: "+giay.getTengiay());
        txtGia.setText("Giá Bán:" + giay.getGiaban());
        txtSize.setText("Size:" + giay.getSize() + "$");
        txtSoluongSp.setText("Số Lượng Kho: " + giay.getSoluongkho());
//        cartViewModel.updateStock(selectedItems);
    }
    public void onAttach(Context context){
        super.onAttach(context);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
    }

}