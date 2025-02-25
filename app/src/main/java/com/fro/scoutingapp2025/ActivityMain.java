package com.fro.scoutingapp2025;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMain extends AppCompatActivity {
    // Declare global variables here \/
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate variables here \/
        button = findViewById(R.id.startButton);

        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivityScouting.class)));
    }
}