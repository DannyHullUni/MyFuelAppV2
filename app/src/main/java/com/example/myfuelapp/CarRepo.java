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

    //METHODS START
    public void insert (Car car) {
        new insertAsyncTask(mCarDao).execute(car);
    }

    public void deleteAll()  {
        new deleteAllCarsAsyncTask(mCarDao).execute();
    }

    public void deleteCar(Car car) {
        new deleteCarAsyncTask(mCarDao).execute(car);
    }
    //METHODS END

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

    private static class deleteAllCarsAsyncTask extends AsyncTask<Void, Void, Void> {
        private CarDao mAsyncTaskDao;

        deleteAllCarsAsyncTask(CarDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteCarAsyncTask extends AsyncTask<Car, Void, Void> {

        private CarDao mAsyncTaskDao;

        deleteCarAsyncTask(CarDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Car... params) {
            mAsyncTaskDao.deleteCar(params[0]);
            return null;
        }
    }

}
