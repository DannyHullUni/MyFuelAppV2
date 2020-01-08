package com.example.myfuelapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class Fuel_Details extends AppCompatActivity {

    private FuelViewModel mFuelViewModel;
    public static final int NEW_FUEL_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel__details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FuelListAdapter adapter = new FuelListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Button convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
               // mFuelViewModel.UPDATE????
            }
        });

        final Button deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                mFuelViewModel.deleteAll();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fuel_Details.this, InputFuelActivity.class);
                startActivityForResult(intent, NEW_FUEL_ACTIVITY_REQUEST_CODE);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Fuel myFuel = adapter.getFuelAtPosition(position);

                        mFuelViewModel.deleteFuel(myFuel);
                    }
                });

        mFuelViewModel = ViewModelProviders.of(this).get(FuelViewModel.class);

        mFuelViewModel.getAllFuel().observe(this, new Observer<List<Fuel>>() {
            @Override
            public void onChanged(@Nullable final List<Fuel> fuels) {
                // Update the cached copy of the words in the adapter.
                adapter.setFuel(fuels);
            }
        });

        helper.attachToRecyclerView(recyclerView);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_FUEL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {


            Integer odom = data.getIntExtra(InputFuelActivity.EXTRA_ODOM, 0);
            double fuelCost = data.getDoubleExtra(InputFuelActivity.EXTRA_COST, 0.0);
            double fuelLitres = data.getDoubleExtra(InputFuelActivity.EXTRA_LITRE, 0.0);

            Fuel newFuel = new Fuel(fuelCost, fuelLitres, odom);
            mFuelViewModel.insert(newFuel);
            //calls the INSERT from the DAO to store the car in the database
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}

