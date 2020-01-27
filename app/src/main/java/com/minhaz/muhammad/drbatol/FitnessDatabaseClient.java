package com.minhaz.muhammad.drbatol;

import android.content.Context;

import androidx.room.Room;

public class FitnessDatabaseClient {
    private Context fCtx;
    private static FitnessDatabaseClient fInstance;

    private FitnessAppDatabase fitnessAppDatabase;

    private FitnessDatabaseClient(Context fCtx)
    {
        this.fCtx=fCtx;

        fitnessAppDatabase = Room.databaseBuilder(fCtx, FitnessAppDatabase.class, "fitnessToDo").build();
    }

    public static synchronized FitnessDatabaseClient getfInstance(Context fCtx) {
        if (fInstance == null) {
            fInstance = new FitnessDatabaseClient(fCtx);
        }
        return fInstance;
    }

    public FitnessAppDatabase getFitnessAppDatabase(){return fitnessAppDatabase;}
}
