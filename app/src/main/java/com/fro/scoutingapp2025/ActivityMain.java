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
        verticalLongBox.createTypeBox("Main_Scouters_Name", Values.inputType_text, 20, Values.vertical_level_1);
        verticalLongBox.createTeamNumberDropdown("Main_Team_Number", Values.vertical_level_2);
        verticalLongBox.createTextDropdown(
                "Main_Robot_Position",
                new ArrayList<String>(Arrays.asList("Left", "Middle", "Right", "Other")),
                Values.vertical_level_3);
        verticalLongBox.createTypeBox("Main_Match_Number", Values.inputType_number, 3, Values.vertical_level_4);
        verticalLongBox.createTextDropdown(
                "Main_Human_Player_Position",
                new ArrayList<String>(Arrays.asList("Left Coral Station", "Right Coral Station", "Processor", "Can't Tell")),
                Values.vertical_level_5);
        // button to go to next page
        startButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivityScouting.class)));
    }
}