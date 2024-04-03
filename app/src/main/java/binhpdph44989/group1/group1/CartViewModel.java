package binhpdph44989.group1.group1;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.model.Giay;

public class CartViewModel extends ViewModel {
    private MutableLiveData<List<Giay>> cartItems = new MutableLiveData<>();

    public LiveData<List<Giay>> getCartItems() {
        if (cartItems.getValue() == null) {
            cartItems.setValue(new ArrayList<>());
        }
        return cartItems;
    }

    public void addToCart(Giay giay) {
        List<Giay> items = cartItems.getValue();
        if (items == null) {
            items = new ArrayList<>();
        }

        boolean isExist = false;
        for (Giay item : items) {
            if (item.getMagiay() == giay.getMagiay()) { // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng chưa
                isExist = true;
                // Cập nhật số lượng của sản phẩm
                item.setSoluong(item.getSoluong() + 1); // Tăng số lượng lên 1
                break;
            }
        }

        if (!isExist) {
            giay.setSoluong(1); // Nếu sản phẩm chưa tồn tại trong giỏ hàng, số lượng mặc định là 1
            items.add(giay);
        }

        cartItems.setValue(items);
    }
}
