package com.minhaz.muhammad.drbatol;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Fitness implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fitness")
    private String fitnessWork;

    @ColumnInfo(name = "fitness_details")
    private String fitnessDetails;

    @ColumnInfo(name = "fitness_schedule")
    private String fitnessSchedule;

    @ColumnInfo(name = "fit")
    private boolean fit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFitnessWork() {
        return fitnessWork;
    }

    public void setFitnessWork(String fitnessWork) {
        this.fitnessWork = fitnessWork;
    }

    public String getFitnessDetails() {
        return fitnessDetails;
    }

    public void setFitnessDetails(String fitnessDetails) {
        this.fitnessDetails = fitnessDetails;
    }

    public String getFitnessSchedule() {
        return fitnessSchedule;
    }

    public void setFitnessSchedule(String fitnessSchedule) {
        this.fitnessSchedule = fitnessSchedule;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }
}
