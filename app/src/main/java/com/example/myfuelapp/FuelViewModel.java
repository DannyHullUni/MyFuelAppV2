package com.example.myfuelapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FuelViewModel extends AndroidViewModel {

    private FuelRepo mRepository;
    private LiveData<List<Fuel>> mAllFuel;

    public FuelViewModel (Application application) {
        super(application);
        mRepository = new FuelRepo(application);
        mAllFuel = mRepository.getAllFuel();
    }

    LiveData<List<Fuel>> getAllFuel() {
        return mAllFuel;
    }

    public void insert(Fuel fuel) {

        mRepository.insert(fuel);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteFuel(Fuel fuel) {
        mRepository.deleteFuel(fuel);
    }
}

