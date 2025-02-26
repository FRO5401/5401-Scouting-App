package com.fro.scoutingapp2025;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityScouting extends AppCompatActivity {
    // Declare global variables here \/
    TextView autoButton;
    TextView teleopButton;
    TextView endgameButton;
    TextView notesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        // Instantiate variables here \/
        autoButton = findViewById(R.id.auto_button);
        teleopButton = findViewById(R.id.teleop_button);
        endgameButton = findViewById(R.id.endgame_button);
        notesButton = findViewById(R.id.notes_button);

        replaceFragment(new FragmentAuto());

        autoButton.setOnClickListener(v -> replaceFragment(new FragmentAuto()));
        teleopButton.setOnClickListener(v -> replaceFragment(new FragmentTeleop()));
        endgameButton.setOnClickListener(v -> replaceFragment(new FragmentEndgame()));
        notesButton.setOnClickListener(v -> replaceFragment(new FragmentNotes()));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.replaceFrame, fragment);
        fragmentTransaction.commit();
    }
}