package com.jaysharma.productmanagement;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao

public interface Dao {

    @Insert
    void insert(ProductModal model);

    @Update
    void update(ProductModal model);

    @Delete
    void delete(ProductModal model);

    @Query("DELETE FROM product_table")
    void deleteAllProducts();

    @Query("SELECT * FROM product_table ORDER BY name ASC")
    LiveData<List<ProductModal>> getAllProducts();

}
