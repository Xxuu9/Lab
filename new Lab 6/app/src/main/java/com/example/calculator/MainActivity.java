package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // set up member variables that will be used in following methods.
    private Button zero, one, two, three, four, five, six, seven, eight, nine;
    private Button dot;
    private Button addition, minus;
    private Button divide;
    private Button multiply;
    private Button equals;
    private Button modulo;
    private Button clear, delete;
    private TextView theFirstView;
    private TextView theOptionView;
    private TextView theSecondView;
    private TextView theEqualView;
    private TextView theAnswerView;
    private int isFlag = 0;
    private String str1 = "";
    private String str2 = "";
    private String option = "";
    private double number1, number2;
    private double numResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();

    }


    //initialize the buttons
    public void initButton() {

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);
        minus = findViewById(R.id.minus);
        addition = findViewById(R.id.addition);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        equals = findViewById(R.id.equals);
        modulo = findViewById(R.id.modulo);
        dot = findViewById(R.id.dot);
        modulo = findViewById(R.id.modulo);
        theFirstView = findViewById(R.id.theFirst);
        theAnswerView = findViewById(R.id.output);
        theSecondView = findViewById(R.id.theSecond);
        theOptionView = findViewById(R.id.theOption);
        theEqualView = findViewById(R.id.theEqual);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        dot.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        addition.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        equals.setOnClickListener(this);
        modulo.setOnClickListener(this);
        theFirstView.setOnClickListener(this);
        theOptionView.setOnClickListener(this);
        theEqualView.setOnClickListener(this);
        theSecondView.setOnClickListener(this);
        theAnswerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
                if (isFlag == 0) {
                    if (option == "") {
                        str1 = str1 + ((Button) v).getText().toString();
                        theFirstView.setText(str1);
                    } else {
                        str2 = str2 + ((Button) v).getText().toString();
                        theSecondView.setText(str2);
                    }
                }
                // already pressed "=", clear the input before, reset the isFlag value to 0
                if (isFlag == 1) {
                    clear();
                    isFlag = 0;
                    str1 += ((Button) v).getText().toString();
                    theFirstView.setText(str1);
                }
                break;

            //use if statement to see where should the dot be
            case R.id.dot:
                if (str1 != "" && option == ""&& !str1.contains(".")) {
                    str1 += ((Button) v).getText().toString();
                    theFirstView.setText(str1);
                }
                else if (option != "" && str2 != ""&& !str2.contains(".")) {
                    str2 += ((Button) v).getText().toString();
                    theSecondView.setText(str2);
                }
                else {// execute nothing
                }
                break;
            // no operate here, actual operator is in case R.id.equals
            case R.id.addition:
                option = "+";
                theOptionView.setText("+");
                break;
            case R.id.minus:
                option = "-";
                theOptionView.setText("-");
                break;
            case R.id.multiply:
                option = "*";
                theOptionView.setText("*");
                break;
            case R.id.divide:
                option = "/";
                theOptionView.setText("/");
                break;
            case R.id.modulo:
                option = "%";
                theOptionView.setText("%");
                break;
            case R.id.clear:
                clear();
                break;
            case R.id.delete:
                deleteElement();
                break;
            case R.id.equals:
                operatorMethod();
                break;
        }
    }

    public void operatorMethod() {
        theEqualView.setText("=");
        if(theFirstView.getText() == "" || theSecondView.getText() == "" ) return;
        isFlag = 1;
        number1 = Double.parseDouble(theFirstView.getText().toString());
        number2 = Double.parseDouble(theSecondView.getText().toString());

        if (option == "+") {
            numResult = number1 + number2;
        }
        if (option == "-") {
            numResult = number1 - number2;
        }
        if (option == "*") {
            numResult = number1 * number2;
        }
        if (option == "/") {
            numResult = number1 / number2;
        }
        if (option == "%") {
            numResult = number1 % number2;
        }
        typeCast();
    }

    //use if statement to decide which number or character should be deleted
    public void deleteElement() {
        if(theEqualView.getText().toString() != ""){
            theEqualView.setText("");
        }
        else if (theAnswerView.getText().toString() == "" && theSecondView.getText().toString() != "") {
            if (str2.length() == 1) {
                theSecondView.setText("");
                str2 = "";
            } else {
                str2 = str2.substring(0, theSecondView.length() - 1);
                theSecondView.setText(str2);
            }
        }
        else if (theSecondView.getText().toString() == "" && theOptionView.getText().toString() != "") {
            theOptionView.setText("");
            option = "";
        }
        else if (theOptionView.getText().toString() == "" && theFirstView.getText().toString() != "") {
            if (str1.length() == 1) {
                theFirstView.setText("");
                str1 = "";
            } else {
                str1 = str1.substring(0, str1.length() - 1);
                theFirstView.setText(str1);
            }
        }

    }

    public void clear() {
        str1 = "";
        str2 = "";
        theOptionView.setText("");
        theSecondView.setText("");
        theFirstView.setText("");
        theAnswerView.setText("");
        theEqualView.setText("");
        option = "";

    }

    //if the result is an integer, convert it to an integer, if the result is a decimal, keep six decimal places after the decimal point
    public void typeCast(){
        int intResult = (int)numResult;
        if (intResult == numResult){
            theAnswerView.setText(intResult + "");
        }
        if (intResult != numResult){
            numResult = Double.parseDouble(String.format("%.6f", numResult));
            theAnswerView.setText(numResult + "");
        }
    }
}