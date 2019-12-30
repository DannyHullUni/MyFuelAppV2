package com.example.myfuelapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    void insert(Car car);

    @Query("DELETE FROM car_table")
    void deleteAll();

    @Query("SELECT * from car_table ORDER BY car ASC")
    LiveData<List<Car>> getAllCars();
}