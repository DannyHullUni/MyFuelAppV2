package com.example.myfuelapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Car car);

    @Query("DELETE FROM car_table")
    void deleteAll();

    @Query("SELECT * from car_table ORDER BY manufacturer ASC")
    LiveData<List<Car>> getAllCars();

    @Query("SELECT * from car_table LIMIT 1")
    Car[] getAnyCar();

    @Delete
    void deleteCar(Car car);
}