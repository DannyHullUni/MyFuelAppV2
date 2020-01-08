package com.example.myfuelapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FuelRepo {

    private FuelDao mFuelDao;
    private LiveData<List<Fuel>> mAllFuel;

    FuelRepo(Application application) {
        CarDatabase db = CarDatabase.getDatabase(application);
        mFuelDao = db.fuelDao();
        mAllFuel = mFuelDao.getAllFuel();
    }

    LiveData<List<Fuel>> getAllFuel() {
        return mAllFuel;
    }

    //METHODS START
    public void insert (Fuel fuel) {
        new insertAsyncTask(mFuelDao).execute(fuel);
    }

    public void deleteAll()  {
        new deleteAllFuelAsyncTask(mFuelDao).execute();
    }

    public void deleteFuel(Fuel fuel) {
        new deleteFuelAsyncTask(mFuelDao).execute(fuel);
    }
    //METHODS END

    private static class insertAsyncTask extends AsyncTask<Fuel, Void, Void> {

        private FuelDao mAsyncTaskDao;

        private insertAsyncTask(FuelDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Fuel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllFuelAsyncTask extends AsyncTask<Void, Void, Void> {
        private FuelDao mAsyncTaskDao;

        deleteAllFuelAsyncTask(FuelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteFuelAsyncTask extends AsyncTask<Fuel, Void, Void> {

        private FuelDao mAsyncTaskDao;

        deleteFuelAsyncTask(FuelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Fuel... params) {
            mAsyncTaskDao.deleteFuel(params[0]);
            return null;
        }
    }

}

