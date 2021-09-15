package com.jaysharma.productmanagement;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ProductModal.class}, version = 2)
public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase instance;

    public abstract Dao Dao();

    public static synchronized ProductDatabase getInstance(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class, "product_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        PopulateDbAsyncTask(ProductDatabase instance) {
            Dao dao = instance.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
