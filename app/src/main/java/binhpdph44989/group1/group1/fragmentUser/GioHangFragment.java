package binhpdph44989.group1.group1.fragmentUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.CartViewModel;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterUser.GioHangAdapter;
import binhpdph44989.group1.group1.dao.DonHangDAO;
import binhpdph44989.group1.group1.model.Giay;


public class GioHangFragment extends Fragment {
    private CartViewModel cartViewModel;
    RecyclerView recyclerView;
    GioHangAdapter adapter;
    TextView txtTongTien;
    CheckBox cbAll;
    Button btnDatHang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        recyclerView = view.findViewById(R.id.rcvGioHang);
        txtTongTien = view.findViewById(R.id.txtTongTien);
        btnDatHang = view.findViewById(R.id.btnDatHang);
        cbAll = view.findViewById(R.id.cbAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GioHangAdapter(new ArrayList<>(), getContext(), this);
        recyclerView.setAdapter(adapter);
        cbAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Cập nhật trạng thái của tất cả sản phẩm trong danh sách
            for (Giay giay : adapter.getGiayList()) {
                giay.setSelected(isChecked);
            }
            // Tính toán tổng giá bán của các sản phẩm đã chọn
            calculateTotalPrice();
            // Cập nhật lại giao diện
            adapter.notifyDataSetChanged();
        });
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });

        return view;
    }

    public void calculateTotalPrice() {
        int totalPrice = 0;
        for (Giay giay : adapter.getGiayList()) {
            if (giay.isSelected()) {
                totalPrice += giay.getGiaban() * giay.getSoluong();
            }
        }
        txtTongTien.setText("Tổng tiền: " + totalPrice + "$");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), new Observer<List<Giay>>() {
            @Override
            public void onChanged(List<Giay> giayList) {
                adapter.setList(new ArrayList<>(giayList));
            }
        });
    }

    private void placeOrder() {
        // Kiểm tra xem có sản phẩm nào được chọn không
        boolean hasSelectedItem = false;
        for (Giay giay : adapter.getGiayList()) {
            if (giay.isSelected()) {
                hasSelectedItem = true;
                break;
            }
        }

        // Nếu không có sản phẩm nào được chọn, hiển thị dialog cảnh báo
        if (!hasSelectedItem) {
            showSelectProductDialog();
            return;
        }

        // Tiếp tục thực hiện đặt hàng
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xác nhận đặt hàng");
        builder.setMessage("Bạn có muốn mua các sản phẩm đã chọn không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogOrder();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng dialog
                dialog.dismiss();
            }
        });
        // Hiển thị dialog
        builder.create().show();
    }

    private void showSelectProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
        builder.setMessage("Vui lòng chọn ít nhất một sản phẩm để đặt hàng.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng dialog
                dialog.dismiss();
            }
        });
        // Hiển thị dialog
        builder.create().show();
    }

    private void showDialogOrder() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_order, null);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        EditText edtFullName = dialogView.findViewById(R.id.edtFullName);
        EditText edtPhone = dialogView.findViewById(R.id.edtPhone);
        EditText edtAddress = dialogView.findViewById(R.id.edtAddress);
        Button btnOrder = dialogView.findViewById(R.id.btnOrder);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtFullName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                if (!name.isEmpty() | !phone.isEmpty() | !address.isEmpty()) {
                    // Tạo đơn hàng mới với thông tin người đặt
                    cartViewModel.setHoTen(name);
                    cartViewModel.setPhone(phone);
                    cartViewModel.setAddress(address);
                    order();
                    alertDialog.dismiss();
                } else {
                    // Nếu họ tên không hợp lệ, hiển thị thông báo
                    Toast.makeText(getContext(), "Vui lòng nhập Thông Tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void order() {
        // Lấy danh sách các sản phẩm đã chọn từ adapter
        ArrayList<Giay> selectedItems = (ArrayList<Giay>) adapter.getSelectedItems();

        // Kiểm tra số lượng kho của từng sản phẩm
        boolean hasInsufficientStock = false;
        for (Giay giay : selectedItems) {
            if (giay.getSoluongkho() < giay.getSoluong()) {
                hasInsufficientStock = true;
                break;
            }
        }

        // Nếu có ít nhất một sản phẩm có số lượng kho không đủ, hiển thị thông báo
        if (hasInsufficientStock) {
            Toast.makeText(getContext(), "Số lượng kho không đủ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Nếu số lượng kho đủ, thực hiện đặt hàng
        DonHangFragment donHangFragment = new DonHangFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("selectedItems", selectedItems);
        donHangFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.framelayout, donHangFragment)
                .addToBackStack(null)
                .commit();
        Toast.makeText(getContext(), "Đặt Hàng Thành Công", Toast.LENGTH_SHORT).show();

        // Cập nhật số lượng kho trong cơ sở dữ liệu
        DonHangDAO donHangDAO = new DonHangDAO(getContext());
        for (Giay giay : selectedItems) {
            int newStock = giay.getSoluongkho() - giay.getSoluong();
            if (newStock >= 0) {
                // Cập nhật số lượng kho trong cơ sở dữ liệu
                donHangDAO.updateProductQuantity(String.valueOf(giay.getMagiay()), giay.getSoluong());
                adapter.updateProductQuantity(giay, giay.getSoluongkho() - giay.getSoluong()  );
            } else {
                // Nếu số lượng kho âm, không thực hiện cập nhật và xóa sản phẩm khỏi giỏ hàng
                Toast.makeText(getContext(), "Số lượng kho không đủ", Toast.LENGTH_SHORT).show();
                adapter.removeGiay(giay); // Hàm removeGiay() là hàm bạn cần tạo trong GioHangAdapter để xóa sản phẩm khỏi giỏ hàng
                adapter.notifyDataSetChanged(); // Cập nhật lại RecyclerView
            }
        }
    }


}
