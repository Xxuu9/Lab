package com.example.pizzaordering.ui.orderinghistory;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizzaordering.Order;
import com.example.pizzaordering.OrderRepository;

import java.util.List;

public class OrderingHistoryViewModel extends AndroidViewModel {

    private LiveData<List<Order>> totalHistoryOrders;
    private OrderRepository mRepository;


    public OrderingHistoryViewModel(Application application) {
        super(application);
        mRepository = new OrderRepository(application);
        totalHistoryOrders = mRepository.getAllOrders();
    }

    LiveData<List<Order>> getAllResults() { return totalHistoryOrders; }

    public void insert(Order orderDetail) { mRepository.insert(orderDetail); }

    public void deleteOne(Order orderDetail) {
        mRepository.deleteOne(orderDetail);
    }
}
