package com.example.pizzaordering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pizzaordering.ui.orderinghistory.OrderingHistoryFragment;

public class MenuActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);
    }

    public void Confirm(View view) {
        Intent intent = new Intent(MenuActivity.this, OrderingHistoryFragment.class);
        startActivity(intent);
    }
}
