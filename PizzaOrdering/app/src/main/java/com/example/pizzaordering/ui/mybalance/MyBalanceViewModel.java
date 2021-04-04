package com.example.pizzaordering.ui.mybalance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyBalanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyBalanceViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is mybalance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}