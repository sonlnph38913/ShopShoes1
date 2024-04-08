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
        if (giay == null) {
            return; // Kiểm tra giay null
        }

        List<Giay> items = cartItems.getValue();
        if (items == null) {
            items = new ArrayList<>();
        }

        boolean isExist = false;
        for (Giay item : items) {
            if (item.getMagiay() == giay.getMagiay()) {
                isExist = true;
                // Cập nhật số lượng
                item.setSoluong(item.getSoluong() + 1);
                break;
            }
        }

        if (!isExist) {
            giay.setSoluong(1);
            items.add(giay);
        }

        cartItems.setValue(items);
    }

    public void clearCart() {
        cartItems.setValue(new ArrayList<>());
    }
}
