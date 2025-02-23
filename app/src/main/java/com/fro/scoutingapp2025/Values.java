package com.fro.scoutingapp2025;

import java.util.ArrayList;
import java.util.HashMap;

public class Values {
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