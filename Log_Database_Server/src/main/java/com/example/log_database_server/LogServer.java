package com.example.log_database_server;

import java.util.Date;

public class LogServer {

    public Date timeStampDate;

    public String tag;

    public String data;

    private LogServer() {}

    public LogServer(Date timeStampDate, String tag, String data) {
        this.timeStampDate = timeStampDate;
        this.tag = tag;
        this.data = data;
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


    public LogServer setTimeStampDate(Date timeStampDate) {
        this.timeStampDate = timeStampDate;
        return this;
    }

    public LogServer setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public LogServer setData(String data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        return "ServerLog{" +
                "timeStampDate=" + timeStampDate +
                ", tag='" + tag + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}