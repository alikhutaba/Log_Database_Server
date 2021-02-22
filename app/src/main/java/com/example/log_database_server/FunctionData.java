package com.example.log_database_server;

public class FunctionData {

    private String ClassName;
    private String functionName;
    private String action;

    public FunctionData(){
    }

    public FunctionData(String className, String functionName, String action) {
        ClassName = className;
        this.functionName = functionName;
        this.action = action;
    }


    public String getClassName() {
        return ClassName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getAction() {
        return action;
    }


    public void setClassName(String className) {
        ClassName = className;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
