package com.example.pizzaordering;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizzaordering.Order;
import com.example.pizzaordering.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderingViewModel extends ViewModel {

    private LiveData<List<Order>> totalHistoryOrders;
    private OrderRepository mRepository;

    public OrderingViewModel(Application application) {
        super();
        mRepository = new OrderRepository(application);
        totalHistoryOrders = mRepository.getAllOrders();
    }

    LiveData<List<Order>> getAllResults() { return totalHistoryOrders; }

    public void insert(Order orderDetail) {
        System.out.println(">>>----"+orderDetail.toString());
        mRepository.insert(orderDetail);
    }
}