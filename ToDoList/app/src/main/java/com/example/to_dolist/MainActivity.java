package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    HashMap<Integer, String> listItem = new HashMap<Integer, String>();
    private String[] arr1 = convertArray(listItem);
    private ArrayAdapter<String> adapter;
    private Button buttonAdd;
    private Button buttonEdit;
    private EditText editText;
    private ListView listView;
    private String str1;
    private int ind;
    private int needChangeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
    }

    private void initButton() {
        arr1 = convertArray(listItem);
        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, arr1);
        listView = (ListView) findViewById(R.id.todo_list);
        listView.setAdapter(adapter);


        buttonAdd = findViewById(R.id.bt_add);
        buttonEdit = findViewById(R.id.bt_edit);
        editText = findViewById(R.id.edit_text);

        editText.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.bt_add:
                addContent();
                break;
            case R.id.bt_edit:
                editContent();
                break;
        }

    }

    private void addContent() {
        str1 = ((EditText) editText).getText().toString();
        listItem.put(ind, str1);
        ind = ind + 1;
        arr1 = convertArray(listItem);
        refresh();
    }

    private void editContent() {
        str1 = ((EditText) editText).getText().toString();
        listItem.put(needChangeId, str1);
        arr1 = convertArray(listItem);
        refresh();
    }


    private void refresh(){
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, arr1);
        ListView listView = (ListView) findViewById(R.id.todo_list);
        listView.setAdapter(adapter);
    }


    private String[] convertArray(HashMap map){
        int len = 0;
        Set set = map.keySet();
        for (Object in : set)
        { String str = (String) map.get(in);
          len += 1;
        }
        String[] strArray = new String[len];
        len = 0;
        for (Object in : set)
        { String str = (String) map.get(in);
            strArray[len] = str;
            len += 1;
        }
        return strArray;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        needChangeId = position;
        editText.setText(listItem.get(needChangeId));
    }
}
