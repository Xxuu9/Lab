package com.example.pizzaordering;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Order.class}, version = 1, exportSchema = false)
public abstract class OrderRoomDatabase extends RoomDatabase {

    public abstract OrderDao orderDao();
    private static OrderRoomDatabase INSTANCE;

    static OrderRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrderRoomDatabase.class, "order_database")
//                            .fallbackToDestructiveMigration()
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static RoomDatabase.Callback sRoomDatabaseCallback =
//            new RoomDatabase.Callback(){
//                @Override
//                public void onOpen (@NonNull SupportSQLiteDatabase db){
//                    super.onOpen(db);
//                    new PopulateDbAsync(INSTANCE).execute();
//                }
//            };
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final OrderDao mDao;
//        Order od = new Order();
//
//
//        PopulateDbAsync(OrderRoomDatabase db) {
//            mDao = db.orderDao();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate the database
//            // when it is first created
////            mDao.deleteAll();
//            od.setMOrderId(1);
//            od.setMOrderTime("123");
//            od.setMPizzaSize("123");
//            od.setMToppings("test123");
//            od.setMOrderPrice("123");
//
//            for (int i = 0; i <= 2; i++) {
////                System.out.println("++>>>>" + orders[i]);
//                Order order = od;
////                System.out.println(">>>>>++++++++++++"+od.getMOrderPrice());
//                mDao.insert(order);
//            }
//            return null;
//        }
//    }
}



