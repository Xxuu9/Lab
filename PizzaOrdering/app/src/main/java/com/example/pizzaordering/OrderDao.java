package com.example.pizzaordering;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);

    @Query("DELETE FROM order_table")
    void deleteAll();

    @Query("DELETE FROM order_table WHERE order_id = :id")
    void deleteOne(Integer id);

    @Query("SELECT * from order_table ORDER BY order_id DESC")
    LiveData<List<Order>> getAllOrders();

    @Query("SELECT * from order_table WHERE order_id = :id")
    LiveData<Order> getSpecificOrder(String id);
}
