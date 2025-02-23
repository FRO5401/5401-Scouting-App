package com.fro.scoutingapp2025;

import java.util.HashMap;

public class Values {
    // public variables for the developer to set which box they are using
    public static int left = 0;
    public static int middle = 1;
    public static int right = 2;
    public static int inputType_text = 0;
    public static int inputType_number = 1;

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