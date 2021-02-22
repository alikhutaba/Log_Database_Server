#  Log_Database_Server



![GitHub](https://img.shields.io/github/license/alikhutaba/Android-shape-image-button)
[![](https://jitpack.io/v/alikhutaba/Log_Database_Server.svg)](https://jitpack.io/#alikhutaba/Log_Database_Server)



A library for simple generic Object Logs implementation in local database and easy way to send them into your server 


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



send a signal log to the server, without save it to local database.

```java
	LogToServer.sendLogToServer(String tag, Object data, Class<T> classOfT);
```

add log the the local database, without send it to server.
```java
	addLogToDB(String tag, Object data, Class<T> classOfT)
```


```java

        SquareImageButton squareImageButton1;
        squareImageButton1 = (SquareImageButton) findViewById(R.id.squareimagebutton1);
        squareImageButton1.init(new ImageButtonConfig
                        .ImageButtonConfigBuilder(this)
                        .mainTitleColor("#FF000000") // main title text Color in Hex, default white (Hex:#FFFFFF").
                        .mainTitleSize(18) // main title text size, default 18 sp.
                        .mainTitletypeface(ResourcesCompat.getFont(this, R.font.alegreya_sans_sc_medium)) // main title font typeface, default font oxygen_light.
                        .mainTitleBackgroundColor("#B3FF8C00") // main title Background color in Hex, default Transparent black(Hex:#B3000000).
                        .subTitleColor("#FF000000") // sub title text Color in Hex, default white (Hex:#FFFFFF").
                        .subTitleSize(14) // sub title text size, default 14 sp.
                        .subTitletypeface(ResourcesCompat.getFont(this, R.font.alegreya_sans_sc_medium)) // sub title font typeface, default font oxygen_light.
                        .subTitleBackgroundColor("#B3FF8C00") // sub title Background color in Hex, default Transparent black(Hex:#B3000000).
                        .build(),
                new ImageButton("https://assets.entrepreneur.com/content/3x2/2000/20150824181921-meditate-yoga-relax-calm-zen.jpeg",
                        "Main title 1",
                        "Sub title 1"));
                        
                        
            squareImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // clickable image button....
            }
        });
```
