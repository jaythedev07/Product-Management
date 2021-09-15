package com.jaysharma.productmanagement;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<ProductModal>> allProducts;

    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
    }

    public void insert(ProductModal model) {
        repository.insert(model);
    }

    public void update(ProductModal model) {
        repository.update(model);
    }

    public void delete(ProductModal model) {
        repository.delete(model);
    }

    //public void deleteAllProducts() { repository.deleteAllProducts(); }

    public LiveData<List<ProductModal>> getAllProducts() {
        return allProducts;
    }
}
