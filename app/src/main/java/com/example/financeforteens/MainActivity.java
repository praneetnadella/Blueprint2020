package com.example.financeforteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
