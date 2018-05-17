package com.shenkar.gadyezra.storage;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import java.util.List;

@Database(entities = {NamedateEntity.class}, version = 1)
public abstract class NamedateDb extends RoomDatabase  {
    private static final String TAG = NamedateDb.class.getSimpleName();
    private static NamedateDb INSTANCE;

    public static NamedateDb getInstance(Context context) {
        synchronized (NamedateDb.class) {
            if (INSTANCE == null) {
                // notice getApplicationContext
                // -- it prevents the memory leak that would happen if the activity was passed
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NamedateDb.class, "ndlog.db")
//                        .addMigrations(MIGRATION_1_2) // placeholder for future db versions
                        .build();
            }
            return INSTANCE;
        }
    }
    public abstract NamedateDao getNamedateDao();
    public LiveData<List<NamedateEntity>> readNamedate() {
        LiveData<List<NamedateEntity>> nameDateEntities = getNamedateDao().loadNamedate();
        return nameDateEntities;
    }
    public void writeToNamedateDB(final NamedateEntity namedateEntity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getNamedateDao().insertToLog(namedateEntity);
            }
        }).start();
    }
}
