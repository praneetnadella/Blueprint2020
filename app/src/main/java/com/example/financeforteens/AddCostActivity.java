package com.example.financeforteens;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCostActivity extends AppCompatActivity {

    Button selectDate;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    Date picked;
    Button add;
    //Saved values
    String name;
    EditText nameText;
    String category;
    Spinner categoryChooser;
    String cost;
    EditText costText;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        selectDate = findViewById(R.id.date);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddCostActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                Calendar c = Calendar.getInstance();
                                Date today = c.getTime();
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, month);
                                c.set(Calendar.DAY_OF_MONTH, day);
                                picked = c.getTime();
                                if (picked.equals(today))
                                    selectDate.setText("TODAY");
                                else
                                    selectDate.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(picked));
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        Spinner spinner = (Spinner) findViewById(R.id.categories);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        add  = findViewById(R.id.addItem);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText = findViewById(R.id.name);
                name = nameText.getText().toString();
                costText = findViewById(R.id.cost);
                cost = costText.getText().toString();
                date = DateFormat.getDateInstance(DateFormat.SHORT).format(picked);
                categoryChooser = findViewById(R.id.categories);
                category = categoryChooser.getSelectedItem().toString();

            }
        });

    }
}
