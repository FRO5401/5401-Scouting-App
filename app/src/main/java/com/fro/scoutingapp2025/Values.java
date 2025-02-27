package com.fro.scoutingapp2025;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class Values {
    // Current year
    public static final int year = 2025;

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
            if (data.get(key).equals(0) || data.get(key).equals("")){
                String name = key.replace('_', ' ');
                missingVals.append(name).append("\n");
            }
        }

        if (missingVals.toString().isEmpty()){return "All data has been successfully inputted.";}
        else {return "The input for these values were not changed:\n" + missingVals.toString();}
    }

    public static void exportData(Context c){
        Calendar calendar = Calendar.getInstance();
        String pathToExternalStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File path = new File(pathToExternalStorage + "/" + "Scouting-"+year);

        JSONObject jsonObject = new JSONObject();

        //start page
        for(String key: data.keySet()) {
            try {
                if (data.get(key).equals("Dropdown")){ jsonObject.put(key, ""); }
                else { jsonObject.put(key, data.get(key)); }
            }
            catch (JSONException e) {throw new RuntimeException(e);}
        }

        try {
            FileOutputStream writer = new FileOutputStream(new File(path, year+"_SCOUTING_DATA_" + calendar.getTimeInMillis() + ".json"));
            writer.write(jsonObject.toString().getBytes());
            writer.close();
        }
        catch (IOException e) { e.printStackTrace(); }

        Toast.makeText(c, path.toString(), Toast.LENGTH_SHORT).show();
    }
}