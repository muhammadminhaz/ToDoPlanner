package com.minhaz.muhammad.drbatol;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Private implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "feel")
    private String feel;

    @ColumnInfo(name = "descrip")
    private String descrip;

    @ColumnInfo(name = "schedule_by")
    private String scheduleBy;

    @ColumnInfo(name = "done")
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getScheduleBy() {
        return scheduleBy;
    }

    public void setScheduleBy(String scheduleBy) {
        this.scheduleBy = scheduleBy;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
