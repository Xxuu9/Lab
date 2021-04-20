package com.example.pizzaordering.ui.orderinghistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pizzaordering.Order;
import com.example.pizzaordering.R;
import java.util.List;

public class OrderingHistoryFragment extends Fragment implements OrderAdapter.ItemClickListener {

    private OrderingHistoryViewModel mOrderingHistoryViewModel;
    protected OrderAdapter mAdapter;
    private List<Order> mOrder;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        mOrderingHistoryViewModel = ViewModelProviders.of(this).get(OrderingHistoryViewModel.class);
        mOrderingHistoryViewModel.getAllResults().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                mAdapter.setOrders(orders);
                mOrder = orders;
            }
        });
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_orderinghistory_list, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.list_of_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new OrderAdapter(getActivity(), mOrder);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "Long click to delete the order", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        // remove the order from the recyclerview
        Order order = mAdapter.removeItem(position);
        // delete the order from the database
        mOrderingHistoryViewModel.deleteOne(order);
    }

    /*
        onLongClick
            mOrder.remove(position);
     */

}
