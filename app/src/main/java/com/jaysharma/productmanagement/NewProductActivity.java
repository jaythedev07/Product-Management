package com.jaysharma.productmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class NewProductActivity extends AppCompatActivity {

    private EditText Name, Detail, Serial, Expiery, Quentity, Price;
    private Button save;

    public static final String EXTRA_ID =
            "com.jaysharma.productmanagement.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.jaysharma.productmanagement.EXTRA_NAME";
    public static final String EXTRA_DETAILS =
            "com.jaysharma.productmanagement.EXTRA_DETAILS";
    public static final String EXTRA_SERIAL =
            "com.jaysharma.productmanagement.EXTRA_SERIAL";
    public static final String EXTRA_EXPIERY =
            "com.jaysharma.productmanagement.EXTRA_EXPIERY";
    public static final String EXTRA_QUENTITY =
            "com.jaysharma.productmanagement.EXTRA_QUENTITY";
    public static final String EXTRA_PRICE =
            "com.jaysharma.productmanagement.EXTRA_PRICE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        Name = findViewById(R.id.name);
        Detail = findViewById(R.id.details);
        Serial = findViewById(R.id.serial);
        Expiery = findViewById(R.id.expiery);
        Quentity = findViewById(R.id.quentity);
        Price = findViewById(R.id.price);
        save = findViewById(R.id.save);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            Name.setText(intent.getStringExtra(EXTRA_NAME));
            Detail.setText(intent.getStringExtra(EXTRA_DETAILS));
            Serial.setText(intent.getStringExtra(EXTRA_SERIAL));
            Expiery.setText(intent.getStringExtra(EXTRA_EXPIERY));
            Quentity.setText(intent.getStringExtra(EXTRA_QUENTITY));
            Price.setText(intent.getStringExtra(EXTRA_PRICE));
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String detail = Detail.getText().toString();
                String serial = Serial.getText().toString();
                String expiery = Expiery.getText().toString();
                String quentity = Quentity.getText().toString();
                String price = Price.getText().toString();
                if (name.isEmpty() || detail.isEmpty() || serial.isEmpty() || expiery.isEmpty() || quentity.isEmpty() || price.isEmpty()) {
                    Toast.makeText(NewProductActivity.this, "Please enter the valid product details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveProduct(name, detail, serial, expiery, quentity, price);
            }
        });

    }

    private void saveProduct(String Name, String Detail, String Serial, String Expiery, String Quentity, String Price) {
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, Name);
        data.putExtra(EXTRA_DETAILS, Detail);
        data.putExtra(EXTRA_SERIAL, Serial);
        data.putExtra(EXTRA_EXPIERY, Expiery);
        data.putExtra(EXTRA_QUENTITY, Quentity);
        data.putExtra(EXTRA_PRICE, Price);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        Toast.makeText(this, "Product Saved!", Toast.LENGTH_SHORT).show();
    }

}