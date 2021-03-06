package com.example.pizzaordering.ui.mybalance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pizzaordering.R;

public class MyBalanceFragment extends Fragment {

    private MyBalanceViewModel myBalanceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myBalanceViewModel =
                new ViewModelProvider(this).get(MyBalanceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mybalance, container, false);
        return root;
    }
}