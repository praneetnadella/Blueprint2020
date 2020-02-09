package com.example.financeforteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amazonaws.mobile.client.AWSMobileClient;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    public void signOut(View v) {
        AWSMobileClient.getInstance().signOut();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void stockSim(View v) {
        Intent loginIntent = new Intent (this, StockDataEntry.class);
        startActivity(loginIntent);
    }

    public void manageAcct(View v) {
        Intent loginIntent = new Intent (this, FinanceActivity.class);
        startActivity(loginIntent);
    }
}
