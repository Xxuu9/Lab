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
        Integer positionDataId = mData.get(position).getMOrderId();
        String positionDataTime = mData.get(position).getMOrderTime();
        String positionDataSize = mData.get(position).getMPizzaSize();
        String positionDataToppings = mData.get(position).getMToppings();
        String positionDataPrice = mData.get(position).getMOrderPrice();
        holder.tvOrderId.setText("Order id:" + String.valueOf(positionDataId));
        holder.tvOrderTime.setText(positionDataTime);
        holder.tvPizzaSize.setText(positionDataSize);
        holder.tvToppings.setText(positionDataToppings);
        holder.tvOrderPrice.setText(positionDataPrice);
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
        TextView tvOrderId;
        TextView tvOrderTime;
        TextView tvPizzaSize;
        TextView tvToppings;
        TextView tvOrderPrice;

        ViewHolder(View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
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



    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public Order removeItem(int position)
    {
        Order order = mData.get(position);
        mData.remove(position);
        //Refresh
        notifyDataSetChanged();
        return order;
    }

    public void setOrders(List<Order> orders){
        mData = orders;
        notifyDataSetChanged();
    }
}
