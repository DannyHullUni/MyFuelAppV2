package com.example.myfuelapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car_table")
public class Car {

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = "manufacturer" ) //maybe change this back if it messes up to "car"
        private String mCar;
        @ColumnInfo(name = "carModel")
        private String mCarModel;

        public Car(){}
        //CONSTRUCTOR
        public Car(@NonNull String car, String model) {
                this.mCar = car;
                this.mCarModel = model;
        }

        //GETTERS
        public String getCar() { return this.mCar; }
        public String getCarModel() { return this.mCarModel; }

        //SETTERS
        public void setCar(String mCar) { this.mCar = mCar; }
        public void setMCarModel(String mCarModel) { this.mCarModel = mCarModel; }

        /* @ColumnInfo(name = "carYear")
        private String mCarYear;
        public String getCarYear() { return this.mCarYear; }
        public void setMCarYear(String mCarYear) { this.mCarYear = mCarYear; }

        @ColumnInfo(name = "carMileage")
        private String mCarMileage;
        public String getCarMileage() { return this.mCarMileage; }
        public void setMCarMileage(String mCarMileage) { this.mCarMileage = mCarMileage; } */

}
