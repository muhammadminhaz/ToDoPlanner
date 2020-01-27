package com.minhaz.muhammad.drbatol;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Fitness.class},version = 1)
public abstract class FitnessAppDatabase extends RoomDatabase {
    public abstract FitnessDao fitnessDao();
}
