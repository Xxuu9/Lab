package com.example.pizzaordering;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OrderingHistoryActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_orderinghistory);


    }

    /*
        SharedPreference keys:
            String: size
            int: olives
            int: pineapple
            int: green pepper
            int: mushrooms
            int: tomato
            int: onion

          placeOrder.onClick()
            delete everything in shared preferences
            editor.clear();
            editor.commit();
     */





}
