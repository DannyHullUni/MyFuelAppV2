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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CarViewModel mCarViewModel;
    private FuelViewModel mFuelViewModel;
    public static final int NEW_CAR_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CarListAdapter adapter = new CarListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final Button deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                mCarViewModel.deleteAll();
                mFuelViewModel.deleteAll();
                View fab = findViewById(R.id.fab);
                fab.setVisibility(View.VISIBLE);

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCarActivity.class);
                startActivityForResult(intent, NEW_CAR_ACTIVITY_REQUEST_CODE);


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
                        Car myCar = adapter.getCarAtPosition(position);

                        // Delete the car
                        mCarViewModel.deleteCar(myCar);
                        mFuelViewModel.deleteAll();
                        View fab = findViewById(R.id.fab);
                        fab.setVisibility(View.VISIBLE);
                    }
                });

        helper.attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new CarListAdapter.OnItemClickListener() {
            @Override
            public void ItemClicked(Car car) {
                Intent intent = new Intent(MainActivity.this, Fuel_Details.class);;

                startActivity(intent);
                //START NEW ACTIVITY FROM HERE, NEEDS TO BE PASSED A POSITION TO KNOW WHAT ITEM WAS CLICKED..
            }
        });

        mCarViewModel = ViewModelProviders.of(this).get(CarViewModel.class);

        mFuelViewModel = ViewModelProviders.of(this).get(FuelViewModel.class);

        mCarViewModel.getAllCars().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(@Nullable final List<Car> cars) {
                // Update the cached copy of the words in the adapter.
                adapter.setCars(cars);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CAR_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            String car = data.getStringExtra(NewCarActivity.EXTRA_REPLY);
            String model = data.getStringExtra(NewCarActivity.EXTRA_MOD);
            double mpg = data.getDoubleExtra(NewCarActivity.EXTRA_MPG, 0.0);
            int carID = data.getIntExtra(NewCarActivity.EXTRA_ID, 0);

            Car myCar = new Car(carID, car, model, mpg);

            mCarViewModel.insert(myCar);
            View fab = findViewById(R.id.fab);
            fab.setVisibility(View.GONE);

            //calls the INSERT from the DAO to store the car in the database
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
