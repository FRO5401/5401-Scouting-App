package com.fro.scoutingapp2025;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityScouting extends AppCompatActivity {
    // Declare global variables here \/
    Button autoButton;
    Button teleopButton;
    Button endgameButton;
    Button notesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        // Instantiate variables here \/
        autoButton = findViewById(R.id.autoButton);
        teleopButton = findViewById(R.id.teleopButton);
        endgameButton = findViewById(R.id.endgameButton);
        notesButton = findViewById(R.id.notesButton);

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