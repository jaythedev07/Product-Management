package com.jaysharma.productmanagement;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class ProductModal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Name;
    private String Details;
    private String Serial;
    private String Expiery;
    private String Quentity;
    private String Price;

    public ProductModal(String Name, String Details, String Serial, String Expiery, String Quentity, String Price) {
        this.Name = Name;
        this.Details = Details;
        this.Serial = Serial;
        this.Expiery = Expiery;
        this.Quentity = Quentity;
        this.Price = Price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }

    public String getExpiery() {
        return Expiery;
    }

    public void setExpiery(String Expiery) {
        this.Expiery = Expiery;
    }

    public String getQuentity() {
        return Quentity;
    }

    public void setQuentity(String Quentity) {
        this.Quentity = Quentity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
