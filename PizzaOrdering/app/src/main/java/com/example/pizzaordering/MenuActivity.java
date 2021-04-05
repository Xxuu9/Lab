package com.example.pizzaordering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pizzaordering.ui.orderinghistory.OrderingHistoryFragment;

public class MenuActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

//        Button bt_confirm = (Button) findViewById(R.id.bt_confirm);
    }

    public void Confirm(View view) {
        Intent intent = new Intent(MenuActivity.this, OrderingHistoryActivity.class);
        startActivity(intent);
    }



}
