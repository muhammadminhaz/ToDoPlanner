package com.minhaz.muhammad.drbatol;

import android.content.Context;

import androidx.room.Room;

public class PrivateDatabaseClient {
    public Context pCtx;
    public static PrivateDatabaseClient pInstance;

    private PrivateAppDatabase privateAppDatabase;

    private PrivateDatabaseClient (Context pCtx)
    {
        this.pCtx=pCtx;
        privateAppDatabase = Room.databaseBuilder(pCtx, PrivateAppDatabase.class,"myPrivates").build();
    }

    public static synchronized PrivateDatabaseClient getpInstance(Context pCtx)
    {
        if (pInstance==null)
        {
            pInstance = new PrivateDatabaseClient(pCtx);
        }
        return pInstance;
    }

    public PrivateAppDatabase getPrivateAppDatabase(){return privateAppDatabase;}
}
