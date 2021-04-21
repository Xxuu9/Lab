package com.example.pizzaordering;

import com.example.pizzaordering.ui.orderinghistory.OrderingHistoryViewModel;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MenuActivity extends AppCompatActivity implements TextView.OnEditorActionListener,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private OrderingHistoryViewModel mOrderingHistoryViewModel;
    private SharedPreferences savedValues;
    private RadioGroup rgPizzaSize;
    private TextView tvGoPrice, tvPaPrice, tvGpPrice, tvMrPrice, tvTmPrice, tvOnPrice, tvTotalPrice;
    private TextView tvGoAmount, tvPaAmount, tvGpAmount, tvMrAmount, tvTmAmount, tvOnAmount;
    private Button btGoDe, btGoIn, btPaDe, btPaIn, btGpDe, btGpIn, btMrDe, btMrIn, btTmDe, btTmIn,
            btOnDe, btOnIn, btClear;
    private String pizzaSize;
    private float goPrice, paPrice, gpPrice, mrPrice, tmPrice, onPrice;
    private float totalPrice, pizzaPrice;
    private int goPriceNum, paPriceNum, gpPriceNum, mrPriceNum, tmPriceNum, onPriceNum;
    private int rgPizzaSizeId;

    // a very important variable to indicate if the radio button is changed by user or not
    private boolean isChangedByClear = true;

    private SharedPreferences.Editor editor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        mOrderingHistoryViewModel = ViewModelProviders.of(this).get(OrderingHistoryViewModel.class);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);


        update();
        collectPrices();  //collect all prices from TextViews
        init();  // initialize all variables
    }

    public void onPause() {
        savedValues();
        super.onPause();
    }

    public void onStop(){
        savedValues();
        super.onStop();
    }

    public void onDestroy(){
        savedValues();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        if(savedValues.getBoolean("prefer_remember", false)) {
            goPriceNum = savedValues.getInt("goPriceNum", goPriceNum);
            paPriceNum = savedValues.getInt("paPriceNum", paPriceNum);
            gpPriceNum = savedValues.getInt("gpPriceNum", gpPriceNum);
            mrPriceNum = savedValues.getInt("mrPriceNum", mrPriceNum);
            tmPriceNum = savedValues.getInt("tmPriceNum", tmPriceNum);
            onPriceNum = savedValues.getInt("onPriceNum", onPriceNum);
            pizzaSize = savedValues.getString("pizzaSize", pizzaSize);
            pizzaPrice = savedValues.getFloat("pizzaPrice", pizzaPrice);
            rgPizzaSizeId = savedValues.getInt("rgPizzaSizeId", rgPizzaSizeId);
            setAllValues(false);
        }

    }

    private void savedValues(){
        editor = savedValues.edit();
        if (savedValues.getBoolean("prefer_remember", false)) {
            editor.putInt("goPriceNum", goPriceNum);
            editor.putInt("paPriceNum", paPriceNum);
            editor.putInt("gpPriceNum", gpPriceNum);
            editor.putInt("mrPriceNum", mrPriceNum);
            editor.putInt("tmPriceNum", tmPriceNum);
            editor.putInt("onPriceNum", onPriceNum);
            editor.putString("pizzaSize", pizzaSize);
            editor.putFloat("pizzaPrice", pizzaPrice);
            editor.putInt("rgPizzaSizeId", rgPizzaSizeId);
        }
        else{
            editor.clear();
            setAllValues(true);
            editor.putBoolean("prefer_remember", false);
        }

        //editor.commit();
        editor.apply();
    }

    public void update(){
        // TextView
        tvGoPrice = findViewById(R.id.tv_go_price);
        tvGoPrice.setOnClickListener(this);

        tvPaPrice = findViewById(R.id.tv_pa_price);
        tvPaPrice.setOnClickListener(this);

        tvGpPrice = findViewById(R.id.tv_gp_price);
        tvGpPrice.setOnClickListener(this);

        tvMrPrice = findViewById(R.id.tv_mr_price);
        tvMrPrice.setOnClickListener(this);

        tvTmPrice = findViewById(R.id.tv_tm_price);
        tvTmPrice.setOnClickListener(this);

        tvOnPrice = findViewById(R.id.tv_on_price);
        tvOnPrice.setOnClickListener(this);


        tvTotalPrice = findViewById(R.id.tv_total_price);
        tvTotalPrice.setOnClickListener(this);


        tvGoAmount = findViewById(R.id.tv_go_amount);
        tvGoAmount.setOnClickListener(this);

        tvPaAmount = findViewById(R.id.tv_pa_amount);
        tvPaAmount.setOnClickListener(this);

        tvGpAmount = findViewById(R.id.tv_gp_amount);
        tvGpAmount.setOnClickListener(this);

        tvMrAmount = findViewById(R.id.tv_mr_amount);
        tvMrAmount.setOnClickListener(this);

        tvTmAmount = findViewById(R.id.tv_tm_amount);
        tvTmAmount.setOnClickListener(this);

        tvOnAmount = findViewById(R.id.tv_on_amount);
        tvOnAmount.setOnClickListener(this);


        // Button
        btGoDe = findViewById(R.id.bt_go_de);
        btGoDe.setOnClickListener(this);

        btGoIn = findViewById(R.id.bt_go_in);
        btGoIn.setOnClickListener(this);

        btPaDe = findViewById(R.id.bt_pa_de);
        btPaDe.setOnClickListener(this);

        btPaIn = findViewById(R.id.bt_pa_in);
        btPaIn.setOnClickListener(this);

        btGpDe = findViewById(R.id.bt_gp_de);
        btGpDe.setOnClickListener(this);

        btGpIn = findViewById(R.id.bt_gp_in);
        btGpIn.setOnClickListener(this);

        btMrDe = findViewById(R.id.bt_mr_de);
        btMrDe.setOnClickListener(this);

        btMrIn = findViewById(R.id.bt_mr_in);
        btMrIn.setOnClickListener(this);

        btTmDe = findViewById(R.id.bt_tm_de);
        btTmDe.setOnClickListener(this);

        btTmIn = findViewById(R.id.bt_tm_in);
        btTmIn.setOnClickListener(this);

        btOnDe = findViewById(R.id.bt_on_de);
        btOnDe.setOnClickListener(this);

        btOnIn = findViewById(R.id.bt_on_in);
        btOnIn.setOnClickListener(this);

        btClear = findViewById(R.id.bt_clearAll);
        btClear.setOnClickListener(this);

        // Radio Group
        rgPizzaSize = findViewById(R.id.rg_pizzaSize);
        rgPizzaSize.setOnCheckedChangeListener(this);

    }

    public void Confirm(View view) {
        if (pizzaPrice !=0) {
            Order currentOrder = new Order();
            currentOrder.setMOrderTime(this.getCurrentDateTime());
            currentOrder.setMPizzaSize("Pizza size: " + pizzaSize);
            currentOrder.setMToppings(mergeToppings());
            currentOrder.setMOrderPrice("Total price: " + totalPrice);

            mOrderingHistoryViewModel.insert(currentOrder);

            setAllValues(true);
            Intent intent = new Intent(MenuActivity.this, OrderingHistoryActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,
                    "Please choose the size of the pizza!", Toast.LENGTH_SHORT).show();
        }
    }

    private String mergeToppings() {
        String merged = "";
        if (goPriceNum != 0) {
            merged += "Green Olives: " + goPriceNum + " ";
        }
        if (paPriceNum != 0) {
            merged += "Pineapple: " + paPriceNum + " ";
        }
        if (gpPriceNum != 0) {
            merged += "Green Pepper: " + gpPriceNum + " ";
        }
        if (mrPriceNum != 0) {
            merged += "Mushroom: " + mrPriceNum + " ";
        }
        if (tmPriceNum != 0) {
            merged += "Tomato: " + tmPriceNum + " ";
        }
        if (onPriceNum != 0) {
            merged += "Onion: " + onPriceNum + " ";
        }
        if (merged.isEmpty()) {
            merged = "Toppings: None";
        }
        else{
            merged = "Toppings: " + merged;
        }
        return merged;
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        switch(actionId){
//            case EditorInfo.IME_ACTION_DONE:
//            case EditorInfo.IME_ACTION_UNSPECIFIED:
//                update();
//                break;
//        }

        return false;
    }

    public void init(){


        goPriceNum = 0;
        paPriceNum = 0;
        gpPriceNum = 0;
        mrPriceNum = 0;
        tmPriceNum = 0;
        onPriceNum = 0;

        pizzaPrice = 0;
        totalPrice = 0;

        rgPizzaSizeId = 0;

        pizzaSize = "";


    }

    public void collectPrices(){
        goPrice = Float.parseFloat(tvGoPrice.getText().toString());
        paPrice = Float.parseFloat(tvPaPrice.getText().toString());
        gpPrice = Float.parseFloat(tvGpPrice.getText().toString());
        mrPrice = Float.parseFloat(tvMrPrice.getText().toString());
        tmPrice = Float.parseFloat(tvTmPrice.getText().toString());
        onPrice = Float.parseFloat(tvOnPrice.getText().toString());
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_go_de:
                goPriceNum = changePrices(goPriceNum, "-");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_go_in:
                goPriceNum = changePrices(goPriceNum, "+");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_pa_de:
                paPriceNum = changePrices(paPriceNum, "-");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_pa_in:
                paPriceNum = changePrices(paPriceNum, "+");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_gp_de:
                gpPriceNum = changePrices(gpPriceNum, "-");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_gp_in:
                gpPriceNum = changePrices(gpPriceNum, "+");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_mr_de:
                mrPriceNum = changePrices(mrPriceNum, "-");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_mr_in:
                mrPriceNum = changePrices(mrPriceNum, "+");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_tm_de:
                tmPriceNum = changePrices(tmPriceNum, "-");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_tm_in:
                tmPriceNum = changePrices(tmPriceNum, "+");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_on_de:
                onPriceNum = changePrices(onPriceNum, "-");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_on_in:
                onPriceNum = changePrices(onPriceNum, "+");
                calculatePrice();
                setAllValues(false);
                break;
            case R.id.bt_clearAll:
                setAllValues(true);
                break;
        }

    }

    public void calculatePrice() {
        totalPrice = pizzaPrice +
                goPrice * goPriceNum +
                paPrice * paPriceNum +
                gpPrice * gpPriceNum +
                mrPrice * mrPriceNum +
                tmPrice * tmPriceNum +
                onPrice * onPriceNum;
        totalPrice = (float) Math.round(totalPrice * 100) / 100;

    }

    // set all displayed values in the layout
    // boolean clear is used to determine if clear all values or not
    public void setAllValues(Boolean clear){
        if (clear){
            rgPizzaSize.clearCheck();
            isChangedByClear = true;
            init();
        }
        else {
            if (rgPizzaSizeId != 0) {
                rgPizzaSize.check(rgPizzaSizeId);
            }
        }
        tvGoAmount.setText(String.valueOf(goPriceNum));
        tvPaAmount.setText(String.valueOf(paPriceNum));
        tvGpAmount.setText(String.valueOf(gpPriceNum));
        tvMrAmount.setText(String.valueOf(mrPriceNum));
        tvTmAmount.setText(String.valueOf(tmPriceNum));
        tvOnAmount.setText(String.valueOf(onPriceNum));
        tvTotalPrice.setText(String.valueOf(totalPrice));
    }

    public int changePrices(int num, String operation) {
        int lowLimit = 0;   // can not buy less than 0 items
        int highLimit = 5;  // can not buy more than 5 items for each topping
        int temp = 0;
        if (operation.equals("-")) {
            temp = num - 1;
            if (temp < lowLimit) {
                Toast.makeText(this,
                        "The number cannot be less than zero", Toast.LENGTH_SHORT).show();
                temp = lowLimit;
            }
        }
        else{
            temp = num + 1;
            if (temp > highLimit) {
                Toast.makeText(this,
                        "The number cannot be greater than five", Toast.LENGTH_SHORT).show();
                temp = highLimit;
            }
        }
        return temp;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // IMPORTANT:
        // if isChangedByClear = true, it means the onCheckedChanged is triggered by the clear
        // button or the change of the settings. So this is not a real "onCheckedChanged" event
        // and should be ignored. Otherwise, it will cause double triggered problem here.
        if (isChangedByClear){
            switch(checkedId){
                case R.id.rg_smallSize:
                    rgPizzaSizeId = checkedId;
                    pizzaSize = "small size";
                    pizzaPrice = 4.99f;
                    calculatePrice();
                    setAllValues(false);
                    break;
                case R.id.rg_mediumSize:
                    rgPizzaSizeId = checkedId;
                    pizzaSize = "medium size";
                    pizzaPrice = 5.99f;
                    calculatePrice();
                    setAllValues(false);
                    break;
                case R.id.rg_largeSize:
                    rgPizzaSizeId = checkedId;
                    pizzaSize = "large size";
                    pizzaPrice = 6.99f;
                    calculatePrice();
                    setAllValues(false);
                    break;
                case R.id.rg_extraLargeSize:
                    rgPizzaSizeId = checkedId;
                    pizzaSize = "extra large size";
                    pizzaPrice = 7.99f;
                    calculatePrice();
                    setAllValues(false);
                    break;
                default:
                    calculatePrice();
//                setAllValues(true);
                    break;
            }
        }
        // if isChangedByClear = false, then reset the value.
        // After that, onCheckedChanged will received the user input.
        else{
            isChangedByClear = true;
        }

    }


}
