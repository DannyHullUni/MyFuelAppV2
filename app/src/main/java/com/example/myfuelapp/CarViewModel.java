package com.example.myfuelapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarViewModel extends AndroidViewModel {

    private CarRepo mRepository;
    private LiveData<List<Car>> mAllCars;

    public CarViewModel (Application application) {
        super(application);
        mRepository = new CarRepo(application);
        mAllCars = mRepository.getAllCars();
    }

    LiveData<List<Car>> getAllCars() {
        return mAllCars;
    }

    public void insert(Car car) {

        mRepository.insert(car);
    }

    /* public void insertAll(Car... cars) {
        mRepository.insertAll();
    } */

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteCar(Car car) {
        mRepository.deleteCar(car);
    }
}
