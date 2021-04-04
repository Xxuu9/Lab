package com.example.pizzaordering.ui.orderinghistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pizzaordering.R;

public class OrderingHistoryFragment extends Fragment {

    private OrderingHistoryViewModel orderingHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orderingHistoryViewModel =
                new ViewModelProvider(this).get(OrderingHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_orderinghistory, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        orderingHistoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}