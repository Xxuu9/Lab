package com.example.pizzaordering.ui.orderinghistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaordering.Order;
import com.example.pizzaordering.R;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    OrderAdapter(Context context, List<Order> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.order, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String positionData0 = mData.get(position).getMOrderTime();
        String positionData1 = mData.get(position).getMPizzaSize();
        String positionData2 = mData.get(position).getMToppings();
        String positionData3 = mData.get(position).getMOrderPrice();
        holder.tvOrderTime.setText(positionData0);
        holder.tvPizzaSize.setText(positionData1);
        holder.tvToppings.setText(positionData2);
        holder.tvOrderPrice.setText(positionData3);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        if(mData != null)
            return mData.size();
        return 0;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {
        TextView tvOrderTime;
        TextView tvPizzaSize;
        TextView tvToppings;
        TextView tvOrderPrice;

        ViewHolder(View itemView) {
            super(itemView);
            tvOrderTime = itemView.findViewById(R.id.tv_order_time);
            tvPizzaSize = itemView.findViewById(R.id.tv_pizza_size);
            tvToppings = itemView.findViewById(R.id.tv_toppings);
            tvOrderPrice = itemView.findViewById(R.id.tv_order_price);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }


        @Override
        public boolean onLongClick(View view) {
            if (mClickListener != null) mClickListener.onItemLongClick(view, getAdapterPosition());


            return true;
        }

    }

//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void removeItem(int position)
    {
        mData.remove(position);
        //Refresh
        notifyDataSetChanged();
    }

    public void setOrders(List<Order> orders){
        mData = orders;
        notifyDataSetChanged();
    }
}
