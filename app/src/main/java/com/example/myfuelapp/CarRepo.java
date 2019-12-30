package com.example.myfuelapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepo {

    private CarDao mCarDao;
    private LiveData<List<Car>> mAllCars;

    CarRepo(Application application) {
        CarDatabase db = CarDatabase.getDatabase(application);
        mCarDao = db.carDao();
        mAllCars = mCarDao.getAllCars();
    }

    LiveData<List<Car>> getAllCars() {
        return mAllCars;
    }

    public void insert (Car car) {
        new insertAsyncTask(mCarDao).execute(car);
    }

    private static class insertAsyncTask extends AsyncTask<Car, Void, Void> {

        private CarDao mAsyncTaskDao;

        insertAsyncTask(CarDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Car... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
