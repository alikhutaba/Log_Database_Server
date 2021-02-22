package com.example.log_database_server;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogController {

    private String baseUrl;
    public LogController(String baseUrl) {
        setBaseUrl(baseUrl);
    }


    private void setBaseUrl(String baseUrl) {
        if(baseUrl == null || baseUrl.isEmpty())
            throw new RuntimeException("unvalid base url");
        this.baseUrl = baseUrl;
    }




    public void sendLogsToServer(List<LogServer> logs, Callback callback){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIFactory service = retrofit.create(APIFactory.class);

        Call<ResponseBody> repos = service.sendLogs(logs);

        repos.enqueue(callback);

    }


    public void sendLogToServer(LogServer log, Callback callback){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIFactory service = retrofit.create(APIFactory.class);

        Call<ResponseBody> repos = service.sendLog(log);

        repos.enqueue(callback);

    }





}
