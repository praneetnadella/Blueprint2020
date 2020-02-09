package com.example.financeforteens;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        this.simpleAdapterView();

    }

    private void simpleAdapterView(){
        setTitle("Items");

        String[] titleArr = { "Name", "Sex", "Age", "Location","Email"};
        //String[] descArr = { "Jerry", "Male", "43", "Singapore", "webmaster@dev2qa.com" };

        final ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;

        int titleLen = titleArr.length;
        for(int i =0; i < titleLen; i++) {
            Map<String,Object> listItemMap = new HashMap<String,Object>();
            listItemMap.put("title", titleArr[i]);
           // listItemMap.put("description", descArr[i]);

            itemDataList.add(listItemMap);
        }

        final SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                new String[]{"title"},new int[]{android.R.id.text1,android.R.id.text2});

        final ListView listView = findViewById(R.id.ListViewExample);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int index, long l) {
                Object clickItemObj = adapterView.getAdapter().getItem(index);
                itemDataList.remove(clickItemObj);

                simpleAdapter.notifyDataSetChanged();
            }
        });

        Button button;
        button = findViewById(R.id.Button1);
        button.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent loginIntent = new Intent (FinanceActivity.this, AddCostActivity.class);
               startActivity(loginIntent);
           }
        });
    }

}
