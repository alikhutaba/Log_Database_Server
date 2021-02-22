package com.example.log_database_server;


import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(entities = {LogDB.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class LogDatabase extends RoomDatabase {
    public abstract LogDao logDao();
}