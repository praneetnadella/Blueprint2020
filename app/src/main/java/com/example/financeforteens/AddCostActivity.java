package com.example.financeforteens;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.ResultListener;
import com.amplifyframework.storage.result.StorageDownloadFileResult;
import com.amplifyframework.storage.result.StorageUploadFileResult;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.nio.charset.Charset.forName;

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
                                System.out.println(picked);
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

                String result = name + "," + cost + "," + date + "," + category;

                System.out.println(result);

                //File file;

                Amplify.Storage.downloadFile(
                        "addition.txt",
                        getExternalCacheDir() + "addition.txt",
                        new ResultListener<StorageDownloadFileResult>() {
                            @Override
                            public void onResult(StorageDownloadFileResult result) {
                                Log.i("StorageQuickStart", "Successfully downloaded: " + result.getFile().getName());
                            }

                            @Override
                            public void onError(Throwable error) {
                                Log.e("StorageQuickStart", error.getMessage());
                                File file = new File(getExternalCacheDir() + "addition.txt");
                            }
                        }
                );

                File file = new File(getExternalCacheDir() + "addition.text");
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.append(result);
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Amplify.Storage.uploadFile(
                        "addition.txt",
                        file.getAbsolutePath(),
                        new ResultListener<StorageUploadFileResult>() {
                            @Override
                            public void onResult(StorageUploadFileResult result) {
                                Log.i("StorageQuickStart", "Successfully uploaded: " + result.getKey());
                                Toast.makeText(AddCostActivity.this, "Your addition has been saved", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(AddCostActivity.this, FinanceActivity.class);
                                startActivity(i);
                            }

                            @Override
                            public void onError(Throwable error) {
                                Log.e("StorageQuickstart", "Upload error.", error);
                            }
                        }
                );


            }
        });

    }
}
