package com.example.log_database_server;

public class ButtonData {

    private String ClassName;
    private String functionName;
    private String action;
    private String buttonName;
    private int ClickNumber;

    public ButtonData(){}

    public ButtonData(String className, String functionName, String action, String buttonName, int clickNumber) {
        ClassName = className;
        this.functionName = functionName;
        this.action = action;
        this.buttonName = buttonName;
        ClickNumber = clickNumber;
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

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public void setClickNumber(int clickNumber) {
        ClickNumber = clickNumber;
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

    public String getButtonName() {
        return buttonName;
    }

    public int getClickNumber() {
        return ClickNumber;
    }
}
