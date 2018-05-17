package com.shenkar.gadyezra.storage;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NamedateDao {
    @Query("select id, date, name from NamedateEntity;")
    LiveData<List<NamedateEntity>> loadNamedate();

    @Insert
    void insertToLog(NamedateEntity ndRow);
}
