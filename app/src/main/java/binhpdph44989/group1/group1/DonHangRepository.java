package binhpdph44989.group1.group1;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import binhpdph44989.group1.group1.dao.DonHangDAO;
import binhpdph44989.group1.group1.model.DonHang;

public class DonHangRepository {
    private DonHangDAO donHangDao;
    private ArrayList<DonHang> allDonHangs;

    public DonHangRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        donHangDao = database.donHangDao();
        allDonHangs = donHangDao.getDSDonHang();
    }

    public ArrayList<DonHang> getAllDonHangs() {
        return allDonHangs;
    }

    public void insert(DonHang donHang) {
        new InsertDonHangAsyncTask(donHangDao).execute(donHang);
    }

    private static class InsertDonHangAsyncTask extends AsyncTask<DonHang, Void, Void> {
        private DonHangDAO donHangDao;

        private InsertDonHangAsyncTask(DonHangDAO donHangDao) {
            this.donHangDao = donHangDao;
        }

        @Override
        protected Void doInBackground(DonHang... donHangs) {
            donHangDao.insert(donHangs[0]);
            return null;
        }
    }
}
