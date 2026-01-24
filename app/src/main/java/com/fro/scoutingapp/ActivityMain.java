package com.fro.scoutingapp;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
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
    ConstraintLayout screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hides system bars
        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        // Creates an exception handler (when app crashes, a stacktrace is written)
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        // Instantiate variables
        startButton = findViewById(R.id.start_button);
        verticalLongBox = findViewById(R.id.vertical_long_box);
        helpButton = findViewById(R.id.main_help_button);
        helpPopup = findViewById(R.id.main_help_popup);
        screen = findViewById(R.id.main_screen);


        //TODO Set main activity row

        // vertical long box
        verticalLongBox.createTypeBox("Main_Scouters_Name", Values.inputType_text, 20, Values.vertical_level_1);
        verticalLongBox.createTeamNumberDropdown("Main_Team_Number", Values.vertical_level_2);
        verticalLongBox.createTextDropdown(
                "Main_Robot_Position",
                new ArrayList<>(Arrays.asList("Left", "Middle", "Right", "Other")),
                Values.vertical_level_3);
        verticalLongBox.createTypeBox("Main_Match_Number", Values.inputType_number, 3, Values.vertical_level_4);
        verticalLongBox.createTextDropdown(
                "Main_Human_Player_Position",
                new ArrayList<>(Arrays.asList("Station 1", "Station 2", "Station 3", "Can't Tell")),
                Values.vertical_level_5);



        // Sets the margins of the popups
        setPopupMargins();
        // The first time this page is created, gets the screen size and sets popup margins
        screen.post(() -> {
            // If the screen width and height was never set (aka the app is loaded for the first time)
            if (Values.screen_width == 0 && Values.screen_height == 0) {
                Values.screen_width = screen.getWidth();
                Values.screen_height = screen.getHeight();
                setPopupMargins();
            }
        });

        // button to go to next page
        startButton.setOnClickListener(v ->{
            // Set loading screen
            startButton.setText("Loading...");
            screen.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline_tinted, getApplicationContext().getTheme()));
            screen.setForegroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_grey, getApplicationContext().getTheme())));
            // Starts next activity
            startActivity(new Intent(getApplicationContext(), ActivityScouting.class));
            // Ends current activity
            finish();
        });

        // help button
        helpPopup.setVisibility(VISIBLE);
        helpPopup.createHelp(helpButton);
        helpPopup.setRatio(4,5);
    }

    void setPopupMargins(){
        // Help popup margins
        ViewGroup.MarginLayoutParams helpParams = (ViewGroup.MarginLayoutParams) helpPopup.getLayoutParameters();
        helpParams.width = (int) (Values.screen_width/1.25);
        helpParams.height = (int) (Values.screen_height/1.25);
        helpPopup.setLayoutParameters(helpParams);
    }
}