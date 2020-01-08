package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputFuelActivity extends AppCompatActivity {

    public static final String EXTRA_ODOM = "com.example.android.myfuelapp.ODOM";
    public static final String EXTRA_COST = "com.example.android.myfuelapp.COST";
    public static final String EXTRA_LITRE = "com.example.android.myfuelapp.LITRE";

    EditText mEditFuelOdom;
    EditText mEditFuelCost;
    EditText mEditFuelLitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_fuel);

        mEditFuelCost = findViewById(R.id.edit_fuel_cost);
        mEditFuelLitre = findViewById(R.id.edit_fuel_litres);
        mEditFuelOdom = findViewById(R.id.edit_odometer);

        final Button button = findViewById(R.id.button_save_log);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent(InputFuelActivity.this, Fuel_Details.class);
                if (TextUtils.isEmpty(mEditFuelOdom.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String odom = mEditFuelOdom.getText().toString();
                    int newOdom = Integer.parseInt(odom);

                    String fuelCost = mEditFuelCost.getText().toString();
                    double newFuelCost = Double.parseDouble(fuelCost);

                    String fuelLitres = mEditFuelLitre.getText().toString();
                    double newFuelLitres = Double.parseDouble(fuelLitres);

                    replyIntent.putExtra(EXTRA_ODOM, newOdom);
                    replyIntent.putExtra(EXTRA_COST, newFuelCost);
                    replyIntent.putExtra(EXTRA_LITRE, newFuelLitres);

                    setResult(RESULT_OK, replyIntent);

                }
                finish();
            }
        });
    }
}
