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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaordering.R;

import java.util.ArrayList;

public class OrderingHistoryFragment extends Fragment implements OrderAdapter.ItemClickListener {

    private OrderingHistoryViewModel orderingHistoryViewModel;

    protected RecyclerView mRecyclerView;
    protected String[] mDataset;
    protected OrderAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

//        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
//        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());

        setHasOptionsMenu(true);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        orderingHistoryViewModel =
                new ViewModelProvider(this).get(OrderingHistoryViewModel.class);



        View root = inflater.inflate(R.layout.fragment_orderinghistory_list, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.list_of_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        System.out.println(">>>" + animalNames);

        mAdapter = new OrderAdapter(getActivity(), animalNames);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);



        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        // https://stackoverflow.com/questions/62490730/cannot-resolve-method-maketextcontext-java-lang-string-int
        Toast.makeText(getActivity(), "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


    private void initDataset() {
        mDataset = new String[10];
        for (int i = 0; i < 10; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }
}