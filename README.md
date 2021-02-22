#  Log_Database_Server



![GitHub](https://img.shields.io/github/license/alikhutaba/Android-shape-image-button)
[![](https://jitpack.io/v/alikhutaba/Log_Database_Server.svg)](https://jitpack.io/#alikhutaba/Log_Database_Server)



A library for simple generic Object Logs implementation in local database and easy way to send them into your server 



```
Json send to server : 

	{
		tag: "ButtonData"
		timeStampDate: "Feb 22, 2021 12:04:37 PM"
		data: "YOUR CLASS DATA"
	}
```
## Setup
Step 1. Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency:

```
	dependencies {
	        implementation 'com.github.alikhutaba:Log_Database_Server:Tag'
	}
```


## Init

init LogToServer when tha app start add your server url
```java
         LogToServer.initServer(baseUrl);
```


## Usage

send all the logs to the server with POST method, and delete them from the local database.
URL : YOUR_BASE_URL/logs
each post send 20 logs to the server.
```java
	LogToServer.sendAllLogsToServer();
```


send a signal log to the server with POST method, without save it to local database.
URL : YOUR_BASE_URL/log
```java
	LogToServer.sendLogToServer(String tag, Object data, Class<T> classOfT);
```


add log the the local database, without send it to server.
```java
	LogToServer.addLogToDB(String tag, Object data, Class<T> classOfT)
```



send all logs between the dates to the server with POST method, and delete them from the local database.
URL : YOUR_BASE_URL/logs
each post send 20 logs to the server.
```java
	LogToServer.sendAllLogsBetweenDatesToServer(long start, long end)
```


send all logs with tag to the server with POST method, and delete them from the local database.
URL : YOUR_BASE_URL/logs
each post send 20 logs to the server.
```java
	LogToServer.sendAllLogsByTagToServer(String tag)
```





delete a list of logs from the local database.
```java
	LogToServer.deleteLogsFromDB(List<LogDB> logs)
```


delete a signal log from the local database.
```java
	LogToServer.deleteLogFromDB(LogDB logs)
```


delete all logs from the local database.
```java
	LogToServer.deleteAllLogsFromDB()
```


