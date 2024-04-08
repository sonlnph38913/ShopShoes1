package binhpdph44989.group1.group1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import binhpdph44989.group1.group1.model.DonHang;

public class DonHangViewModel extends ViewModel {
    private MutableLiveData<List<DonHang>> donHangList = new MutableLiveData<>();

    public LiveData<List<DonHang>> getDonHangList() {
        if (donHangList.getValue() == null) {
            donHangList.setValue(new ArrayList<>());
        }
        return donHangList;
    }

    public void setDonHangList(List<DonHang> donHangs) {
        donHangList.setValue(donHangs);
    }

}
