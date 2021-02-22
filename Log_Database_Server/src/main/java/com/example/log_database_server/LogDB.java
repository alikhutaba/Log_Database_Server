package com.example.log_database_server;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "LOGS_TABLE")

public class LogDB {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "time")
    private Date timeStampDate;

    @ColumnInfo(name = "tag")
    private String tag;

    @ColumnInfo(name = "data")
    private String data;

    private LogDB() {}

    @Ignore
    public LogDB(String tag, String data) {
        this.timeStampDate = new Date();
        this.tag = tag;
        this.data = data;
    }

    public LogDB(Date timeStampDate, String tag, String data) {
        this.timeStampDate = timeStampDate;
        this.tag = tag;
        this.data = data;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTimeStampDate(Date timeStampDate) {
        this.timeStampDate = timeStampDate;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUid() {
        return uid;
    }

    public Date getTimeStampDate() {
        return timeStampDate;
    }

    public String getTag() {
        return tag;
    }

    public String getData() {
        return data;
    }


    @Override
    public String toString() {
        return "LogDB{" +
                "uid=" + uid +
                ", timeStampDate=" + timeStampDate +
                ", tag='" + tag + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}