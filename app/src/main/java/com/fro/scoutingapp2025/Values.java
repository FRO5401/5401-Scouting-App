package com.fro.scoutingapp2025;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;

public class Values {
    // public variables for the developer to set which box they are using
    public static int left = 0;
    public static int middle = 1;
    public static int right = 2;
    public static int inputType_text = 3;
    public static int inputType_number = 4;

    // stopwatch
    public static int seconds;
    public static int minutes;
    public static Looper looper = Looper.getMainLooper();
    public static Handler handler = new Handler(looper);
    public static long millisecond = 0;
    public static long startTime = 0;
    public static long timeBuff = 0;
    public static long updateTime = 0;

    public static Runnable getRunnable(TextView timer) {
        return new Runnable() {
            @Override
            public void run() {
                millisecond = SystemClock.uptimeMillis() - startTime;
                updateTime = timeBuff + millisecond;
                seconds = (int) (updateTime/1000);
                minutes = seconds / 60;
                seconds = seconds % 60;

                String text = minutes + ":" + String.format(Locale.getDefault(), "%02d", seconds);
                timer.setText(text);
                Values.data.put("Teleop_Defense_Stopwatch", text);
                handler.postDelayed(this, 0);
            }
        };
    }

    // Data
    public static HashMap<String, Object> data = new HashMap<>();

    public static String getMap(){
        String map ="[";
        for(String key: data.keySet()){
            map = (map + data.get(key) + ", ");
        }
        map += "]";
        return map;
    }
}