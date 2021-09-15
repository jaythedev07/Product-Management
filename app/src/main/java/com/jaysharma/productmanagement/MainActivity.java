package com.jaysharma.productmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productsRV;
    private static final int ADD_PRODUCT_REQUEST = 1;
    private static final int EDIT_PRODUCT_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productsRV = findViewById(R.id.products);
        FloatingActionButton fab = findViewById(R.id.FABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivityForResult(intent, ADD_PRODUCT_REQUEST);
            }
        });

        productsRV.setLayoutManager(new LinearLayoutManager(this));
        productsRV.setHasFixedSize(true);
        final ProductRVAdapter adapter = new ProductRVAdapter();
        productsRV.setAdapter(adapter);
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        viewmodal.getAllProducts().observe(this, new Observer<List<ProductModal>>() {
            @Override
            public void onChanged(List<ProductModal> models) {
                adapter.submitList(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodal.delete(adapter.getProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Product Deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                        attachToRecyclerView(productsRV);
        adapter.setOnItemClickListener(new ProductRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductModal model) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                intent.putExtra(NewProductActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewProductActivity.EXTRA_NAME, model.getName());
                intent.putExtra(NewProductActivity.EXTRA_DETAILS, model.getDetails());
                intent.putExtra(NewProductActivity.EXTRA_SERIAL, model.getSerial());
                intent.putExtra(NewProductActivity.EXTRA_EXPIERY, model.getExpiery());
                intent.putExtra(NewProductActivity.EXTRA_QUENTITY, model.getQuentity());
                intent.putExtra(NewProductActivity.EXTRA_PRICE, model.getPrice());
                startActivityForResult(intent, EDIT_PRODUCT_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            String Name = data.getStringExtra(NewProductActivity.EXTRA_NAME);
            String Details = data.getStringExtra(NewProductActivity.EXTRA_DETAILS);
            String Serial = data.getStringExtra(NewProductActivity.EXTRA_SERIAL);
            String Expiery = data.getStringExtra(NewProductActivity.EXTRA_EXPIERY);
            String Quentity = data.getStringExtra(NewProductActivity.EXTRA_QUENTITY);
            String Price = data.getStringExtra(NewProductActivity.EXTRA_PRICE);
            ProductModal model = new ProductModal(Name, Details, Serial, Expiery, Quentity, Price);
            viewmodal.insert(model);
            Toast.makeText(this, "Product Saved Succsessfully!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewProductActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Product can't be Updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String Name = data.getStringExtra(NewProductActivity.EXTRA_NAME);
            String Details = data.getStringExtra(NewProductActivity.EXTRA_DETAILS);
            String Serial = data.getStringExtra(NewProductActivity.EXTRA_SERIAL);
            String Expiery = data.getStringExtra(NewProductActivity.EXTRA_EXPIERY);
            String Quentity = data.getStringExtra(NewProductActivity.EXTRA_QUENTITY);
            String Price = data.getStringExtra(NewProductActivity.EXTRA_PRICE);
            ProductModal model = new ProductModal(Name, Details, Serial, Expiery, Quentity, Price);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Product Updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Product Not Saved", Toast.LENGTH_SHORT).show();
        }

    }
}