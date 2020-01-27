package com.minhaz.muhammad.drbatol;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PrivateDao {
    @Query("SELECT * FROM private")
    List<Private> getAll();

    @Insert
    void insert(Private feel);

    @Delete
    void delete(Private feel);

    @Update
    void update(Private feel);
}
