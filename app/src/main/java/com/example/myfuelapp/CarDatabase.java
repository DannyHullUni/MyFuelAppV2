package com.example.myfuelapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Car.class}, version = 11, exportSchema = false)
public abstract class CarDatabase extends RoomDatabase {

    public abstract CarDao carDao();
    private static CarDatabase INSTANCE;

    static CarDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CarDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CarDatabase.class, "car_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CarDao mDao;

        private PopulateDbAsync(CarDatabase db) {
            mDao = db.carDao();
        }

        @Override
        protected Void doInBackground(final Void... voids) {

                    mDao.insert(new Car("Honda", "Whatever"));
                    mDao.insert(new Car("Ford", "Tester"));
                    return null;
                }
            }

        }


