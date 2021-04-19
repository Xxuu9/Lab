package com.example.pizzaordering.ui.orderinghistory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaordering.Order;
import com.example.pizzaordering.R;

import java.util.ArrayList;
import java.util.List;

public class OrderingHistoryFragment extends Fragment implements OrderAdapter.ItemClickListener {

    private OrderingHistoryViewModel mOrderingHistoryViewModel;

    protected RecyclerView mRecyclerView;
    protected OrderAdapter mAdapter;

    private List<Order> mOrder;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

//        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
//        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());

        setHasOptionsMenu(true);

        mOrderingHistoryViewModel = ViewModelProviders.of(this).get(OrderingHistoryViewModel.class);
//        System.out.println(">>>---++"+mOrderingHistoryViewModel.getAllResults());

        mOrderingHistoryViewModel.getAllResults().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                System.out.println(">>><<<"+orders);
                mOrder = orders;
            }

        });


    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        mOrderingHistoryViewModel =
//                new ViewModelProvider(this).get(OrderingHistoryViewModel.class);



        View root = inflater.inflate(R.layout.fragment_orderinghistory_list, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.list_of_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");
//
//        ArrayList<ArrayList<String>> animalNamesList= new ArrayList<>();
//        animalNamesList.add(animalNames);
//        animalNamesList.add(animalNames);
//        animalNamesList.add(animalNames);

//        mOrderingHistoryViewModel.getAllResults().observe(this, new Observer<List<Order>>() {
//            @Override
//            public void onChanged(List<Order> results) {
//                System.out.println(">>>>>>>" + results.get(results.size()-1).getMOrderTime());
//
//
//            }




        mAdapter = new OrderAdapter(getActivity(), mOrder);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);


        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        // https://stackoverflow.com/questions/62490730/cannot-resolve-method-maketextcontext-java-lang-string-int
        Toast.makeText(getActivity(), "You clicked " + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}