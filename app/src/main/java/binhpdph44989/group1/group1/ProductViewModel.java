package binhpdph44989.group1.group1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import binhpdph44989.group1.group1.model.Giay;

public class ProductViewModel extends ViewModel {
    private ProductRepository productRepository;
    private MutableLiveData<List<Giay>> productsLiveData;

    public ProductViewModel() {
        productRepository = new ProductRepository();
        productsLiveData = new MutableLiveData<>();
    }

    // Phương thức này sẽ trả về LiveData chứa danh sách sản phẩm theo mã loại giày
    public LiveData<List<Giay>> getProductsByCategory(int categoryId) {
        productsLiveData = (MutableLiveData<List<Giay>>) (MutableLiveData<List<Giay>>) productRepository.getProductsByCategory(categoryId);
        return productsLiveData;
    }
}
