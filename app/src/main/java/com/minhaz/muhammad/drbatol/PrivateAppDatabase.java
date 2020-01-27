package com.minhaz.muhammad.drbatol;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Private.class}, version = 1)
public abstract class PrivateAppDatabase extends RoomDatabase {
    public abstract PrivateDao privateDao();
}
