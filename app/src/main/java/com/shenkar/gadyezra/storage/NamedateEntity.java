package com.shenkar.gadyezra.storage;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class NamedateEntity {
    @PrimaryKey(autoGenerate = true)
    int id;
    String date;
    String name;

    public void setRecord(String newName,String newDate) {
        name = newName;
        date = newDate;
    }
    public String getRecordAsString() {
        return this.date + '-' + this.name;
    }
}
