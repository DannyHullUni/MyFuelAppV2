package com.example.myfuelapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FuelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Fuel fuel);

    @Query("DELETE FROM fuel_table")
    void deleteAll();

    @Query("SELECT * from fuel_table ORDER BY fuelID ASC")
    LiveData<List<Fuel>> getAllFuel();

    @Query("SELECT * from fuel_table LIMIT 1")
    Fuel[] getAnyFuel();

    @Delete
    void deleteFuel(Fuel fuel);
}
