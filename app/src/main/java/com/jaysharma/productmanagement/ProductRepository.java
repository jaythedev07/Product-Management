package com.jaysharma.productmanagement;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private Dao dao;
    private LiveData<List<ProductModal>> allProducts;

    public ProductRepository(Application application) {
        ProductDatabase database = ProductDatabase.getInstance(application);
        dao = database.Dao();
        allProducts = dao.getAllProducts();
    }

    public void insert(ProductModal model) {
        new InsertProductAsyncTask(dao).execute(model);
    }

    public void update(ProductModal model) {
        new UpdateProductAsyncTask(dao).execute(model);
    }

    public void delete(ProductModal model) {
        new DeleteProductAsyncTask(dao).execute(model);
    }

    public void deleteAllProducts() {
        new DeleteAllProductsAsyncTask(dao).execute();
    }

    public LiveData<List<ProductModal>> getAllProducts() {
        return allProducts;
    }

    private static class InsertProductAsyncTask extends AsyncTask<ProductModal, Void, Void> {
        private Dao dao;

        private InsertProductAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductModal... model) {
            dao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateProductAsyncTask extends AsyncTask<ProductModal, Void, Void> {
        private Dao dao;

        private UpdateProductAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductModal... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<ProductModal, Void, Void> {
        private Dao dao;

        private DeleteProductAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllProductsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllProductsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllProducts();
            return null;
        }
    }
}
