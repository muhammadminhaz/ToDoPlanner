package com.minhaz.muhammad.drbatol;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FitnessDao {
    @Query("SELECT * FROM fitness")
    List<Fitness> getAll();

    @Insert
    void insert(Fitness fitness);

    @Delete
    void delete(Fitness fitness);

    @Update
    void update(Fitness fitness);
}
