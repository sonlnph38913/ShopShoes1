package binhpdph44989.group1.group1;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import binhpdph44989.group1.group1.dao.DonHangDAO;

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract DonHangDAO donHangDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
