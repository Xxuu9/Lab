package com.example.pizzaordering;

import com.example.pizzaordering.ui.orderinghistory.OrderingHistoryViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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

    private RadioGroup rgPizzaSize;
    private TextView tvGoPrice, tvPaPrice, tvGpPrice, tvMrPrice, tvTmPrice, tvOnPrice, tvTotalPrice;
    private TextView tvGoAmount, tvPaAmount, tvGpAmount, tvMrAmount, tvTmAmount, tvOnAmount;
    private Button btGoDe, btGoIn, btPaDe, btPaIn, btGpDe, btGpIn, btMrDe, btMrIn, btTmDe, btTmIn,
            btOnDe, btOnIn;
    private String pizzaSize;
    private float goPrice, paPrice, gpPrice, mrPrice, tmPrice, onPrice;
    private float totalPrice, pizzaPrice;
    private int goPriceNum, paPriceNum, gpPriceNum, mrPriceNum, tmPriceNum, onPriceNum;

    private OrderingHistoryViewModel mOrderingHistoryViewModel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        mOrderingHistoryViewModel = ViewModelProviders.of(this).get(OrderingHistoryViewModel.class);

        update();
        init();


//        Button bt_confirm = (Button) findViewById(R.id.bt_confirm);
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
            currentOrder.setMOrderPrice("Total price: " + String.valueOf(totalPrice));

            mOrderingHistoryViewModel.insert(currentOrder);

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
        goPrice = Float.parseFloat(tvGoPrice.getText().toString());
        paPrice = Float.parseFloat(tvPaPrice.getText().toString());
        gpPrice = Float.parseFloat(tvGpPrice.getText().toString());
        mrPrice = Float.parseFloat(tvMrPrice.getText().toString());
        tmPrice = Float.parseFloat(tvTmPrice.getText().toString());
        onPrice = Float.parseFloat(tvOnPrice.getText().toString());
        goPriceNum = 0;
        paPriceNum = 0;
        gpPriceNum = 0;
        mrPriceNum = 0;
        tmPriceNum = 0;
        onPriceNum = 0;

        pizzaPrice = 0;
        totalPrice = 0;
    }

    public void print() {
        System.out.println(">>>" + goPriceNum);
        System.out.println(">>>" + paPriceNum);
        System.out.println(">>>" + gpPriceNum);
        System.out.println(">>>" + mrPriceNum);
        System.out.println(">>>" + tmPriceNum);
        System.out.println(">>>" + onPriceNum);
        System.out.println(">>>" + pizzaPrice);
        System.out.println(">>>" + totalPrice);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_go_de:
                goPriceNum = changePrices(goPriceNum, "-");
                break;
            case R.id.bt_go_in:
                goPriceNum = changePrices(goPriceNum, "+");
                break;
            case R.id.bt_pa_de:
                paPriceNum = changePrices(paPriceNum, "-");
                break;
            case R.id.bt_pa_in:
                paPriceNum = changePrices(paPriceNum, "+");
                break;
            case R.id.bt_gp_de:
                gpPriceNum = changePrices(gpPriceNum, "-");
                break;
            case R.id.bt_gp_in:
                gpPriceNum = changePrices(gpPriceNum, "+");
                break;
            case R.id.bt_mr_de:
                mrPriceNum = changePrices(mrPriceNum, "-");
                break;
            case R.id.bt_mr_in:
                mrPriceNum = changePrices(mrPriceNum, "+");
                break;
            case R.id.bt_tm_de:
                tmPriceNum = changePrices(tmPriceNum, "-");
                break;
            case R.id.bt_tm_in:
                tmPriceNum = changePrices(tmPriceNum, "+");
                break;
            case R.id.bt_on_de:
                onPriceNum = changePrices(onPriceNum, "-");
                break;
            case R.id.bt_on_in:
                onPriceNum = changePrices(onPriceNum, "+");
                break;
        }

        calculatePrice();

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

        tvGoAmount.setText(String.valueOf(goPriceNum));
        tvPaAmount.setText(String.valueOf(paPriceNum));
        tvGpAmount.setText(String.valueOf(gpPriceNum));
        tvMrAmount.setText(String.valueOf(mrPriceNum));
        tvTmAmount.setText(String.valueOf(tmPriceNum));
        tvOnAmount.setText(String.valueOf(onPriceNum));


        tvTotalPrice.setText(String.valueOf(totalPrice));
        print();
    }

    public int changePrices(int num, String operation) {
        int lowLimit = 0;
        int highLimit = 5;
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
        switch(checkedId){
            case R.id.rg_smallSize:
                pizzaSize = "small size";
                pizzaPrice = 4.99f;
                break;
            case R.id.rg_mediumSize:
                pizzaSize = "medium size";
                pizzaPrice = 5.99f;
                break;
            case R.id.rg_largeSize:
                pizzaSize = "large size";
                pizzaPrice = 6.99f;
                break;
            case R.id.rg_extraLargeSize:
                pizzaSize = "extra large size";
                pizzaPrice = 7.99f;
                break;
            default:
                break;
        }
        calculatePrice();
    }


}
