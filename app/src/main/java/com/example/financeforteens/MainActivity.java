package com.example.financeforteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {

                    @Override
                    public void onResult(UserStateDetails userStateDetails) {
                        Log.i("INIT", "onResult: " + userStateDetails.getUserState());
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("INIT", "Initialization error.", e);
                    }
                }
        );

 */
    }

    public void login (View v) {
        Intent loginIntent = new Intent (this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void addCost(View v) {
        Intent loginIntent = new Intent (this, AddCostActivity.class);
        startActivity(loginIntent);
    }

    public void finance(View v) {
        Intent loginIntent = new Intent (this, FinanceActivity.class);
        startActivity(loginIntent);
    }

    public void stockDataEntry(View v) {
        Intent loginIntent = new Intent (this, StockDataEntry.class);
        startActivity(loginIntent);
    }
}
