package com.example.scorekeepingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_scoreA;
    private TextView textView_scoreB;
    private Button buttonMinusA;
    private Button buttonPlusA;
    private Button buttonMinusB;
    private Button buttonPlusB;
    private Button number1;
    private Button number2;
    private Button number3;
    private Button buttonReset;
    private Button buttonBegin;
    private int numberA = 0;
    private int numberB = 0;
    private int numberBt = 1;
    private int isFlag = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
    }

    private void initButton() {
        textView_scoreA = findViewById(R.id.textView_scoreA);
        textView_scoreB = findViewById(R.id.textView_scoreB);
        buttonMinusA = findViewById(R.id.button_minusA);
        buttonPlusA = findViewById(R.id.button_plusA);
        buttonMinusB = findViewById(R.id.button_minusB);
        buttonPlusB = findViewById(R.id.button_plusB);
        buttonReset = findViewById(R.id.button_reset);
        buttonBegin = findViewById(R.id.button_begin);
        number1 = findViewById(R.id.button_1);
        number2 = findViewById(R.id.button_2);
        number3 = findViewById(R.id.button_3);

        textView_scoreA.setOnClickListener(this);
        textView_scoreB.setOnClickListener(this);
        buttonMinusA.setOnClickListener(this);
        buttonPlusA.setOnClickListener(this);
        buttonMinusB.setOnClickListener(this);
        buttonPlusB.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonBegin.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_begin:
                break;
            case R.id.button_minusA:
                isFlag = 0;
                scoreMinus();
                break;
            case R.id.button_plusA:
                isFlag = 0;
                scoreAdd();
                break;
            case R.id.button_minusB:
                isFlag = 1;
                scoreMinus();
                break;
            case R.id.button_plusB:
                isFlag = 1;
                scoreAdd();
                break;



            case R.id.button_1:
                number1.setActivated(true);
                number2.setActivated(false);
                number3.setActivated(false);
                numberBt = Integer.parseInt(((Button)v).getText().toString());
                break;

            case R.id.button_2:
                number2.setActivated(true);
                number1.setActivated(false);
                number3.setActivated(false);
                numberBt = Integer.parseInt(((Button)v).getText().toString());
                break;

            case R.id.button_3:
                number3.setActivated(true);
                number1.setActivated(false);
                number2.setActivated(false);
                numberBt = Integer.parseInt(((Button)v).getText().toString());
                break;

            case R.id.button_reset:
                reset();

        }
    }

    public void scoreMinus() {
        if(isFlag == 0){
            numberA = numberA-numberBt;
        }
        if(isFlag == 1) {
            numberB = numberB-numberBt;
        }
        showText();
    }

    public void scoreAdd() {
        if(isFlag == 0){
            numberA = numberA+numberBt;
        }
        if(isFlag == 1){
            numberB = numberB+numberBt;
        }
        showText();
    }

    public void showText(){
        textView_scoreA.setText(Integer.toString(numberA));
        textView_scoreB.setText(Integer.toString(numberB));
    }

    public void reset(){
        textView_scoreA.setText("0");
        textView_scoreB.setText("0");
        number1.setActivated(false);
        number2.setActivated(false);
        number3.setActivated(false);
        numberA = 0;
        numberB = 0;
        isFlag = -1;
        numberBt = 1;

    }
}