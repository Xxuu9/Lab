package com.example.pizzaordering;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderRepository {

    private OrderDao mOrderDao;
    private LiveData<List<Order>> mAllOrders;

    public OrderRepository(Application application) {
        OrderRoomDatabase db = OrderRoomDatabase.getDatabase(application);
        mOrderDao = db.orderDao();
        mAllOrders = mOrderDao.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() {
        return mAllOrders;
    }

    public void insert (Order order) {
        new insertAsyncTask(mOrderDao).execute(order);
    }

    public void deleteOne(Order order) {new deleteAsyncTask(mOrderDao).execute(order);}

    private static class insertAsyncTask extends AsyncTask<Order, Void, Void> {

        private OrderDao mAsyncTaskDao;

        insertAsyncTask(OrderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Order... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Order, Void, Void> {

        private OrderDao mAsyncTaskDao;

        deleteAsyncTask(OrderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Order... params) {
            mAsyncTaskDao.deleteOne(params[0].getMOrderId());
            return null;
        }
    }
}
