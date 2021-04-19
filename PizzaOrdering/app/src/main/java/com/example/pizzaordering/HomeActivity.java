package com.example.pizzaordering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

public class HomeActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

    }

    public void beginOrder(View view) {
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);

    }

}
