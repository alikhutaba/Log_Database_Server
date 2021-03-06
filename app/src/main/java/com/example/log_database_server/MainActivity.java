package com.example.log_database_server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button main_BTN_Log;
    private Button main_BTN_funcLog;
    private Button main_BTN_buttonLog;
    private static final String baseUrl = "https://ec9b7ebf-2c18-4399-b9a7-f6e36137edb3.mock.pstmn.io/";


    private int indx = 0;
    private int butttonClicked = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_BTN_Log = findViewById(R.id.main_BTN_Log);
        main_BTN_funcLog = findViewById(R.id.main_BTN_funcLog);
        main_BTN_buttonLog = findViewById(R.id.main_BTN_buttonLog);


        LogToServer.initServer(baseUrl);
        LogToServer.deleteAllLogsFromDB();

        main_BTN_Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogToServer.sendLogToServer("pttt", "ali bla lba lba" + indx++, String.class);

            }
        });

        main_BTN_funcLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionTest();
            }
        });

        main_BTN_buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonData buttonData = new ButtonData("MainActivity", "onCreate", "call function without param", "main_BTN_buttonLog", butttonClicked++);
                LogToServer.addLogToDB("ButtonData", buttonData, ButtonData.class);
            }
        });


    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        LogToServer.sendAllLogsToServer();
    }


    public void functionTest() {

        FunctionData functionData = new FunctionData("MainActivity", "functionTest", "call function without param");
        LogToServer.addLogToDB("FunctionData", functionData, FunctionData.class);

    }


}