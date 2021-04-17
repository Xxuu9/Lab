package com.example.dicerollingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener, TextView.OnEditorActionListener,
        View.OnKeyListener, View.OnClickListener{

    private RadioGroup diceType;
    private CheckBox rollTwice;
    private CheckBox containZero;
    private CheckBox timesTen;
    private EditText specificDiceSides;
    private Button add, roll;
    private TextView result;

    private String diceTypeName = "";
    private Boolean isAdd = false;
    private Boolean isRoll = false;
    private Boolean isRollTwice = false;
    private Boolean isContainZero = false;
    private Boolean isTimesTen = false;
    private String rollResult = "";
    private int diceSides;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update();

    }


    public void update() {

        diceType = findViewById(R.id.diceType);
        diceType.setOnCheckedChangeListener(this);

        rollTwice = findViewById(R.id.rollTwice);
        rollTwice.setOnCheckedChangeListener(this);

        containZero = findViewById(R.id.containZero);
        containZero.setOnCheckedChangeListener(this);

        timesTen = findViewById(R.id.timesTen);
        timesTen.setOnCheckedChangeListener(this);

        specificDiceSides = findViewById(R.id.specificDiceSides);
        specificDiceSides.setOnEditorActionListener(this);
        specificDiceSides.setOnKeyListener(this);

        add = findViewById(R.id.add);
        add.setOnClickListener(this);

        roll = findViewById(R.id.roll);
        roll.setOnClickListener(this);

        result = findViewById(R.id.result);
        result.setOnClickListener(this);


    }

    public void startRoll(){
        System.out.println(">>>sides"+isAdd);
        if (isAdd) {
            if (!specificDiceSides.getText().toString().equals("")) {
                diceSides = Integer.parseInt(specificDiceSides.getText().toString());
                diceTypeName = "";
//                diceType.clearCheck();
            }
            else{
                    Toast.makeText(this, "Please write the number of dice sides", Toast.LENGTH_SHORT).show();;
            }
        }
        else if (!diceTypeName.equals("")){
                switch (diceTypeName) {
                    case "d4":
                        diceSides = 4;
                        break;
                    case "d6":
                        diceSides = 6;
                        break;
                    case "d8":
                        diceSides = 8;
                        break;
                    case "d10":
                        diceSides = 10;
                        break;
                    case "d12":
                        diceSides = 12;
                        break;
                    case "d20":
                        diceSides = 20;
                        break;
                }
            }
        else{
            Toast.makeText(this, "Please choose a dice type", Toast.LENGTH_SHORT).show();;
        }

        System.out.println(">>>sides"+diceSides);

        if (isRoll) {
            if (isRollTwice){
                rollResult = rollDice(diceSides) + ", " + rollDice(diceSides);
            }
            else {
                rollResult = rollDice(diceSides);
            }
            rollResult = "Result:" + rollResult;
            result.setText(rollResult);
            isRoll = false;
        }


    }

    public String rollDice(int diceSides){
        int sideUp;
        if (isContainZero){
            sideUp = (int)(Math.random()* diceSides);
        }
        else{
            sideUp = (int)(Math.random()* diceSides + 1);
        }
        if (isTimesTen) {
            sideUp = sideUp * 10;
        }
        return String.valueOf(sideUp);
    }

    public void clear(){
        isAdd = false;
        specificDiceSides.setText("");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){

        switch(checkedId){
            case R.id.rbd4:
                diceTypeName = "d4";
                clear();
                break;
            case R.id.rbd6:
                diceTypeName = "d6";
                clear();
                break;
            case R.id.rbd8:
                diceTypeName = "d8";
                clear();
                break;
            case R.id.rbd10:
                diceTypeName = "d10";
                clear();
                break;
            case R.id.rbd12:
                diceTypeName = "d12";
                clear();
                break;
            case R.id.rbd20:
                diceTypeName = "d20";
                clear();
                break;
            default:
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()) {
            case R.id.rollTwice:
                isRollTwice = buttonView.isChecked();
                break;
            case R.id.containZero:
                isContainZero = buttonView.isChecked();
                break;
            case R.id.timesTen:
                isTimesTen = buttonView.isChecked();
            }
        }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch(actionId){
            case EditorInfo.IME_ACTION_DONE:
            case EditorInfo.IME_ACTION_UNSPECIFIED:
                update();
                break;
        }
        return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_ENTER){
//            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(billAmountEditText.getWindowToken(), 0);
//        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                isAdd = true;
                break;
            case R.id.roll:
                isRoll = true;
                break;
        }
        startRoll();

    }
}
