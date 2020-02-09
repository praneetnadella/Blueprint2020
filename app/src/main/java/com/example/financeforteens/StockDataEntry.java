package com.example.financeforteens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class StockDataEntry extends AppCompatActivity {
    private TextView priceView;
    private TextView ownedView;
    private TextView shareView;
    private TextView balanceView;
    private SeekBar seekBar;
    private Button sellButton;
    private Button holdButton;
    private Button buyButton;

    private int price = 1, ownedShares = 0;
    private int numShares = 0;
    private int balance = 100;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_data_entry);

        priceView = (TextView) findViewById(R.id.price);
        ownedView = (TextView) findViewById(R.id.owned);
        shareView = (TextView) findViewById(R.id.shares);
        balanceView = (TextView) findViewById(R.id.balance);
        seekBar = (SeekBar) findViewById(R.id.priceSlider);
        sellButton = (Button) findViewById(R.id.sell);
        holdButton = (Button) findViewById(R.id.hold);
        buyButton = (Button) findViewById(R.id.buy);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numShares = progress / 5;
                shareView.setText("SHARES: " + numShares);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String text = "";

                if(numShares == 0) Toast.makeText(StockDataEntry.this, "You can't sell 0 shares", Toast.LENGTH_SHORT).show();
                else if(ownedShares < numShares) Toast.makeText(StockDataEntry.this, "You don't have enough shares", Toast.LENGTH_SHORT).show();
                else {
                    ownedShares -= numShares;
                    ownedView.setText("OWNED: " + ownedShares);
                    balance += numShares * price;
                    balanceView.setText("BALANCE: $" + balance);
                    setPrice();
                }
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                setPrice();
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";

                if(numShares == 0) Toast.makeText(StockDataEntry.this, "You can't buy 0 shares", Toast.LENGTH_SHORT).show();
                else if(balance < numShares * price) Toast.makeText(StockDataEntry.this, "It smells like broke in here", Toast.LENGTH_SHORT).show();
                else {
                    ownedShares += numShares;
                    ownedView.setText("OWNED: " + ownedShares);
                    balance -= numShares * price;
                    balanceView.setText("BALANCE: $" + balance);
                    setPrice();
                }

            }
        });
    }

    public void setPrice(){
        price = (int)(Math.random() * 101);
        priceView.setText("PRICE: $" + price);
    }
}
