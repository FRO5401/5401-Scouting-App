package com.fro.scoutingapp2025;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityMain extends AppCompatActivity {
    // Declare global variables here \/
    TextView startButton;
    RowVerticalLongBox verticalLongBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate variables here \/
        startButton = findViewById(R.id.startButton);
        verticalLongBox = findViewById(R.id.vertical_long_box);

        // vertical long box
        verticalLongBox.createTypeBox("Main_Scouters_Name", Values.inputType_text, Values.vertical_level_1);
        verticalLongBox.createTeamNumberDropdown("Main_Team_Number", Values.vertical_level_2);
        verticalLongBox.createTextDropdown(
                new ArrayList<String>(Arrays.asList("Left", "Middle", "Right", "Other")),
               "Main_Robot_Position", Values.vertical_level_3);
        verticalLongBox.createTypeBox("Main_Match_Number", Values.inputType_number, Values.vertical_level_4);
        verticalLongBox.createTextDropdown(
                new ArrayList<String>(Arrays.asList("Left Coral Station", "Right Coral Station", "Processor", "Can't Tell")),
                "Main_Robot_Position", Values.vertical_level_5);
        // button to go to next page
        startButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivityScouting.class)));
    }
}