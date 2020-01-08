package com.example.myfuelapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "fuel_table")
public class Fuel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fuelID")
    private int mFuelID;
    @ColumnInfo(name = "odometer")
    private int mOdometer;
    @ColumnInfo(name= "fuel_price")
    private double mFuelPrice;
    @ColumnInfo(name = "fuel_litres")
    private double mFuelLitres;

    @Ignore
    public Fuel(){}

    //CONSTRUCTOR
    public Fuel(double mFuelPrice, double mFuelLitres, int mOdometer) {
        
        this.mFuelPrice = mFuelPrice;
        this.mFuelLitres = mFuelLitres;
        this.mOdometer = mOdometer;

    }

    //GETTERS
    public int getMOdometer() {
        return mOdometer;
    }
    public int getMFuelID() {
        return mFuelID;
    }
    public double getMFuelPrice() { return mFuelPrice; }
    public double getMFuelLitres() { return mFuelLitres; }

    //SETTERS
    public void setMOdometer(int mOdometer) { this.mOdometer = mOdometer; }
    public void setMFuelID(int mFuelID) { this.mFuelID = mFuelID; }
    public void setMFuelLitres(double mFuelLitres) { this.mFuelLitres = mFuelLitres; }
    public void setMFuelPrice(double mFuelPrice) { this.mFuelPrice = mFuelPrice; }
}