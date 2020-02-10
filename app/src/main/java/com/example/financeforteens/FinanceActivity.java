package com.example.financeforteens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.ResultListener;
import com.amplifyframework.storage.result.StorageDownloadFileResult;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails userStateDetails) {
                try {
                    Amplify.addPlugin(new AWSS3StoragePlugin());
                    Amplify.configure(getApplicationContext());
                    Log.i("StorageQuickstart", "All set and ready to go!");
                } catch (Exception e) {
                    Log.e("StorageQuickstart", e.getMessage());
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e("StorageQuickstart", "Initialization error.", e);
            }
        });

        simpleAdapterListView();

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

    private void simpleAdapterListView()
    {
        setTitle("Items Bought");

        ArrayList<String> nameArr = new ArrayList<String>();
        ArrayList<String> priceList = new ArrayList<String>();

        Amplify.Storage.downloadFile(
                "buy.txt",
                getExternalCacheDir() + "buy.txt",
                new ResultListener<StorageDownloadFileResult>() {
                    @Override
                    public void onResult(StorageDownloadFileResult result) {
                        Log.i("StorageQuickStart", "Successfully downloaded: " + result.getFile().getName());
                        Toast.makeText(FinanceActivity.this, "Files downloaded", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("StorageQuickStart", error.getMessage());
                        Toast.makeText(FinanceActivity.this, "Files downloaded", Toast.LENGTH_LONG).show();
                    }
                }
        );

        File file = new File(getExternalCacheDir() + "buy.text");
        System.out.println(file.toString());
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                nameArr.add(st.substring(0, st.indexOf(",")));
                String price = st.substring(st.indexOf(",") + 1);
                priceList.add("$" + price.substring(0, price.indexOf(",")));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;

        int titleLen = nameArr.size();
        for(int i =0; i < titleLen; i++) {
            Map<String,Object> listItemMap = new HashMap<String,Object>();
            listItemMap.put("title", nameArr.get(i));
            listItemMap.put("description", priceList.get(i));
            itemDataList.add(listItemMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                new String[]{"title","description"},new int[]{android.R.id.text1,android.R.id.text2});

        ListView listView = (ListView)findViewById(R.id.ListViewExample);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Object clickItemObj = adapterView.getAdapter().getItem(index);
                itemDataList.remove(clickItemObj);

                simpleAdapter.notifyDataSetChanged();
            }
        });

    }

}
