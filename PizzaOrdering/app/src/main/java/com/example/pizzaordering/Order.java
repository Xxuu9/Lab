package com.example.pizzaordering;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Entity(tableName = "order_table")
public class Order {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    @NonNull
    private Integer mOrderId;

    @NonNull
    private String mOrderTime;

    @NonNull
    private String mPizzaSize;

    @NonNull
    private String mToppings;

    @NonNull
    private String mOrderPrice;

    public void setMOrderId(Integer id) {
        this.mOrderId = id;
    }

    public Integer getMOrderId(){
        return this.mOrderId;
    }

    public void setMOrderTime(String mOderTime) {
        this.mOrderTime = mOderTime;
    }

    public String getMOrderTime(){
        return this.mOrderTime;
    }

    public void setMPizzaSize(String mPizzaSize) {
        this.mPizzaSize = mPizzaSize;
    }

    public String getMPizzaSize(){
        return this.mPizzaSize;
    }

    public void setMToppings(String mToppings) {
        this.mToppings = mToppings;
    }

    public String getMToppings(){
        return this.mToppings;
    }

    public void setMOrderPrice(String mOrderPrice) {
        this.mOrderPrice = mOrderPrice;
    }

    public String getMOrderPrice(){
        return this.mOrderPrice;
    }


}
