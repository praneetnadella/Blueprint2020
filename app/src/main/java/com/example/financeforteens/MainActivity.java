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

<<<<<<< HEAD
    public void stockDataEntry(View v) {
        Intent stock = new Intent(this, StockDataEntry.class);
        startActivity(stock);
=======
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
>>>>>>> 9a24a50d11d216c72c0bae6ae1be1a5b29ea0b06
    }
}
