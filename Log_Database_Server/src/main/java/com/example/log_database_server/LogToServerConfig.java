package com.example.log_database_server;

import android.content.Context;

import androidx.room.Room;

public class LogToServerConfig {

    private Context context;
    private String baseUrl;
     private LogDatabase db;
    private LogDao logDao;
    private LogController logController;

    public LogToServerConfig(){}

    public LogToServerConfig(Context context) {
        this.context = context;
        this.db = Room.databaseBuilder(context, LogDatabase.class, "log-database").build();
        this.logDao = db.logDao();
    }

    private void initController(){
        this.logController = new LogController(this.baseUrl);

    }

    public Context getContext() {
        return context;
    }

    public String getBaseUrl() {
        return baseUrl;
    }


    public LogDatabase getDb() {
        return db;
    }

    public LogDao getLogDao() {
        return logDao;
    }

    public LogController getLogController() {
        return logController;
    }

    public LogToServerConfig setContext(Context context) {
        this.context = context;
        return this;
    }

    public LogToServerConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        initController();
        return this;
    }

    public LogToServerConfig setDb(LogDatabase db) {
        this.db = db;
        return this;
    }

    public LogToServerConfig setLogDao(LogDao logDao) {
        this.logDao = logDao;
        return this;
    }

    public LogToServerConfig setLogController(LogController logController) {
        this.logController = logController;
        return this;
    }
}
