package com.example.log_database_server;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIFactory{
    @POST("logs")
    Call<ResponseBody> sendLogs(@Body List<LogServer> log);

    @POST("log")
    Call<ResponseBody> sendLog(@Body LogServer log);
}