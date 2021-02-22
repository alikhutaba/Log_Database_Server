package com.example.log_database_server;

public class LogConverter {


    public static LogServer LogDBToLogServer(LogDB logDB){
            return new LogServer(
                    logDB.getTimeStampDate(),
                    logDB.getTag(),
                    logDB.getData());

    }


    public static LogDB LogServerToLogDB(LogServer logServer){
        return new LogDB(
                logServer.getTimeStampDate(),
                logServer.getTag(),
                logServer.getData());
    }


}
