package com.example.log_database_server;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LogDao {

    @Insert
    void insert(LogDB log);

    @Delete
    void delete(LogDB log);

    @Delete
    void deleteAll(LogDB... logs);


    @Query("SELECT * FROM LOGS_TABLE")
    List<LogDB> getAllLogs();

    @Query("SELECT * FROM LOGS_TABLE WHERE time BETWEEN :start AND :end")
    List<LogDB> getAllLogsBetweenDates(long start, long end);

    @Query("SELECT * FROM LOGS_TABLE where tag LIKE :tag")
    List<LogDB> getAllLogsByTag(String tag);

    @Query("SELECT * FROM LOGS_TABLE WHERE tag LIKE :tag AND time BETWEEN :start AND :end")
    List<LogDB> getAllLogsByTagBetweenDates(long start, long end, String tag);




}