package com.example.myfuelapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car_table")
public class Car {
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "car" )
        private String mCar;

        public Car(@NonNull String car) {
            this.mCar = car;
        }

        public String getCar() {
            return this.mCar;
        }
    }
