package com.example.log_database_server;


import android.annotation.SuppressLint;

import retrofit2.Callback;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class LogToServer {

    @SuppressLint("StaticFieldLeak")
    private static LogToServerConfig logToServerConfig = null;


    public interface DataLogReturned {
        public void logsReturned(List<LogDB> extLogs);
    }


    static void config(LogToServerConfig logToServerConfigInstance) {
        if (logToServerConfig == null)
            logToServerConfig = logToServerConfigInstance;
    }


    /**
     * this function start the server log configurations
     *
     * @param baseUrl the base server url to send the logs
     * @return void
     */
    public static void initServer(String baseUrl) {
        logToServerConfig.setBaseUrl(baseUrl);
    }



    /**
     * this function send a signal log to the server, without save it to database.
     *  with POST method and server URL : YOUR_BASE_URL/log
     * @param tag String : the tag of the log
     * @param data  this is the object that you want to log in that contain your data.
     * @param classOfT the type of data YOUR_DATA_CLASS.name
     * @return void
     */
    public static <T> void sendLogToServer(String tag, Object data, Class<T> classOfT) {

        sendLogToServer(new LogDB(tag, new Gson().toJson(data, classOfT)));

    }



    /**
     * this function add log the the local database, without send it to server.
     *
     * @param tag String : the tag of the log
     * @param data  this is the object that you want to log in that contain your data.
     * @param classOfT the type of data YOUR_DATA_CLASS.name
     * @return void
     */
    public static <T> void addLogToDB(String tag, Object data, Class<T> classOfT) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                logToServerConfig
                        .getLogDao()
                        .insert(new LogDB(tag, new Gson().toJson(data, classOfT)));
            }
        }).start();
    }



    /**
     * this function delete a list of logs from the local database.
     *
     * @param logs list of logs to delete.
     * @return void
     */
    public static void deleteLogsFromDB(List<LogDB> logs) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (LogDB log : logs) {
                    logToServerConfig.getLogDao().delete(log);
                }
            }
        }).start();
    }


    /**
     * this function delete a signal log from the local database.
     *
     * @param log to delete.
     * @return void
     */
    public static void deleteLogFromDB(LogDB log) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                logToServerConfig.getLogDao().delete(log);
            }
        }).start();
    }


    /**
     * this function delete all logs from the local database.
     *
     * @return void
     */
    public static void deleteAllLogsFromDB() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                logToServerConfig.getLogDao().deleteAll();
            }
        }).start();
    }




    /**
     * this function send all the logs to the server and delete them from the local database.
     *  with POST method and server URL : YOUR_BASE_URL/logs
     *  each post send 20 logs to the server.
     * @return void
     */
    public static void sendAllLogsToServer() {

        getAllLogs(new DataLogReturned() {
            @Override
            public void logsReturned(List<LogDB> logs) {
                sendLogs(logs);

            }
        });

    }

    /**
     * this function send all logs between the dates to the server and delete them from the local database.
     *  with POST method and server URL : YOUR_BASE_URL/logs
     *  each post send 20 logs to the server.
     * @param start Long the start second.
     * @param end  Long the end second.
     * @return void
     */
    public static void sendAllLogsBetweenDatesToServer(long start, long end) {
        getAllLogsBetweenDates(start, end, new DataLogReturned() {
            @Override
            public void logsReturned(List<LogDB> logs) {
                sendLogs(logs);
            }
        });

    }

    /**
     * this function send all logs with tag to the server and delete them from the local database.
     *  with POST method and server URL : YOUR_BASE_URL/logs
     *  each post send 20 logs to the server.
     * @param tag String the tag of the logs.
     * @return void
     */
    public static void sendAllLogsByTagToServer(String tag) {
        getAllLogsByTag(tag, new DataLogReturned() {
            @Override
            public void logsReturned(List<LogDB> logs) {
                sendLogs(logs);
            }
        });

    }


    /**
     * this function send all logs between the dates and with tag to the server and delete them from the local database.
     *  with POST method and server URL : YOUR_BASE_URL/logs
     *  each post send 20 logs to the server.
     * @param start Long the start second.
     * @param end  Long the end second.
     * @param tag String the tag of the logs.
     * @return void
     */
    public static void sendAllLogsByTagBetweenDatesToServer(long start, long end, String tag) {
        getAllLogsByTagBetweenDates(start, end, tag, new DataLogReturned() {
            @Override
            public void logsReturned(List<LogDB> logs) {
                sendLogs(logs);
            }
        });

    }


    // PRIVATE METHODS

    private static void sendLogs(List<LogDB> logs) {

        int indx;
        int subListSize = 20;

        for (indx = 0; indx < (logs.size()/subListSize) ; indx++) {

            sendLogsToServer(logs.subList(indx*subListSize, (indx+1)*subListSize));
        }

        if(logs.size() % subListSize > 0)
            sendLogsToServer(logs.subList(indx*subListSize, logs.size()));


    }


    private static void sendLogToServer(LogDB log) {

        Callback<ResponseBody> callback = new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Log.d("Server_Log", "LogToServer onResponse isSuccessful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Server_Log", "LogToServer onFailure ERROR"+t.getMessage());

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                logToServerConfig.getLogController().sendLogToServer(LogConverter.LogDBToLogServer(log), callback);
            }
        }).start();
    }



    private static void sendLogsToServer(List<LogDB> logs) {

        Callback<ResponseBody> callback = new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    Log.d("Server_Log", "LogToServer onResponse isSuccessful");
                    deleteLogsFromDB(logs);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Server_Log", "LogToServer onFailure ERROR"+t.getMessage());

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                logToServerConfig.getLogController().sendLogsToServer(
                        logs
                                .stream()
                                .map(LogConverter::LogDBToLogServer)
                                .collect(Collectors.toList())
                        , callback);
            }
        }).start();
    }





    private static void getAllLogs(final DataLogReturned dataLogReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (dataLogReturned != null) {
                    dataLogReturned.logsReturned(logToServerConfig.getLogDao().getAllLogs());
                }
            }
        }).start();
    }


    private static void getAllLogsBetweenDates(long start, long end, final DataLogReturned dataLogReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (dataLogReturned != null) {
                    dataLogReturned.logsReturned(logToServerConfig.getLogDao().getAllLogsBetweenDates(start, end));
                }
            }
        }).start();
    }

    private static void getAllLogsByTag(String tag, final DataLogReturned dataLogReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (dataLogReturned != null) {
                    dataLogReturned.logsReturned(logToServerConfig.getLogDao().getAllLogsByTag(tag));
                }
            }
        }).start();
    }

    private static void getAllLogsByTagBetweenDates(long start, long end, String tag, final DataLogReturned dataLogReturned) {
        new Thread(new Runnable() {
            public void run() {
                if (dataLogReturned != null) {
                    dataLogReturned.logsReturned(logToServerConfig.getLogDao().getAllLogsByTagBetweenDates(start, end, tag));
                }
            }
        }).start();
    }


}




