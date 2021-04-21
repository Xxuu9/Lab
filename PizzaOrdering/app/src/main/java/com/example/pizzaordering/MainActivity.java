package com.example.pizzaordering;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.pizzaordering.ui.orderinghistory.OrderingHistoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private OrderingHistoryViewModel mOrderingHistoryViewModel;
    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_menu, R.id.settings)
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_menu)
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_menu)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        System.out.println(">> ITEM " + item.toString());
        System.out.println(">> ITEM " + item.getItemId());

        switch(item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            case 16908332:
                System.out.println(">> Test");
                onSupportNavigateUp();
                break;
//            case R.id.:
//                System.out.println(">> Test");
//                onSupportNavigateUp();
//                break;
            default:
                System.out.println(">> NAV");
                super.onOptionsItemSelected(item);
        }
        return true;
    }




    @Override
    public boolean onSupportNavigateUp() {
        System.out.println(">>>>>>>>>>");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void beginOrder(View view) {
        System.out.println(">>>>>>>>>>-------------");

        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}