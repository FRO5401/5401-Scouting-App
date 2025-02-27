package com.fro.scoutingapp2025;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

public class Values {
    // public variables for the developer to set which box they are using
    public static int left = 0;
    public static int middle = 1;
    public static int right = 2;
    public static int vertical_level_1 = 3;
    public static int vertical_level_2 = 4;
    public static int vertical_level_3 = 5;
    public static int vertical_level_4 = 6;
    public static int vertical_level_5 = 7;
    public static int inputType_text = 8;
    public static int inputType_number = 9;

    // Data
    public static HashMap<String, Object> data = new HashMap<>();

    public static String getData(){
        String map ="[";
        for(String key: data.keySet()){
            map = (map + data.get(key) + ", ");
        }
        map += "]";
        return map;
    }

    public static void clearData(){
        data.clear();
    }

    public static String checkData(){
        StringBuilder missingVals = new StringBuilder();
        for(String key: data.keySet()){
            if (data.get(key).equals(0) || data.get(key).equals(-1) || data.get(key).equals("")){
                String name = key.replace('_', ' ');
                missingVals.append(name).append("\n");
            }
        }

        if (missingVals.toString().isEmpty()){
            return "All data has been successfully inputted.";
        }
        else {
            return "The input for these values were not changed:\n" + missingVals.toString();
        }
    }
}