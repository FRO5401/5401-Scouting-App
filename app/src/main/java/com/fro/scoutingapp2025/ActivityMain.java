package com.fro.scoutingapp2025;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityMain extends AppCompatActivity {
    // Declare global variables here \/
    TextView startButton;
    RowVerticalLongBox verticalLongBox;
    ImageButton helpButton;
    HelpPopup helpPopup;
    ConstraintLayout fullPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        // Hides system bars
        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        // Instantiate variables here \/
        startButton = findViewById(R.id.startButton);
        verticalLongBox = findViewById(R.id.vertical_long_box);
        helpButton = findViewById(R.id.main_help_button);
        helpPopup = findViewById(R.id.main_help_popup);
        fullPage = findViewById(R.id.main_full_page);

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

        // Set the size and position of help popup
        fullPage.post(new Runnable() {
            @Override
            public void run() {
                // Help popup
                ViewGroup.MarginLayoutParams helpParams = (ViewGroup.MarginLayoutParams) helpPopup.getLayoutParameters();
                helpParams.width = (int) (fullPage.getWidth()/1.25);
                helpParams.height = (int) (fullPage.getHeight()/1.25);
                helpPopup.setLayoutParameters(helpParams);
            }
        });

        // help button
        helpPopup.setVisibility(VISIBLE);
        helpPopup.createHelp(helpButton);
        helpPopup.setRatio(4,5);
    }
}