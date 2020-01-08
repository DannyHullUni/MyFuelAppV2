package com.example.myfuelapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "car_table")
public class Car {

        @NonNull
        @PrimaryKey (autoGenerate = true)
        @ColumnInfo(name = "carID")
        private int mID;
        @ColumnInfo(name = "manufacturer" ) //maybe change this back if it messes up to "car"
        private String mCar;
        @ColumnInfo(name = "carModel")
        private String mCarModel;
        @ColumnInfo(name = "carMPG")
        private double mCarMPG;

        public Car(){}

        //CONSTRUCTOR
        public Car(@NonNull int carID, String car, String model, double mpg) {
                this.mID = carID;
                this.mCar = car;
                this.mCarModel = model;
                this.mCarMPG = mpg;
        }

        //GETTERS
        public int getID() { return this.mID; }
        public String getCar() { return this.mCar; }
        public String getCarModel() { return this.mCarModel; }
        public Double getCarMPG() { return this.mCarMPG; }

        //SETTERS
        public void setID(int mID) {this.mID = mID; }
        public void setCar(String mCar) { this.mCar = mCar; }
        public void setCarModel(String mCarModel) { this.mCarModel = mCarModel; }
        public void setCarMPG(double mCarMPG) { this.mCarMPG = mCarMPG; };






}
