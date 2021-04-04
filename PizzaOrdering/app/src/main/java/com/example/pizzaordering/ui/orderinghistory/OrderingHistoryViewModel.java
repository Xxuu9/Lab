package com.example.pizzaordering.ui.orderinghistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderingHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OrderingHistoryViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is orderinghistory fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}