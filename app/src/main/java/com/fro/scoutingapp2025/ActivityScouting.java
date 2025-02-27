package com.fro.scoutingapp2025;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityScouting extends AppCompatActivity {
    // Declare global variables here \/
    TextView autoButton;
    TextView teleopButton;
    TextView endgameButton;
    TextView notesButton;
    ImageButton helpButton;
    ImageButton menuBars;
    ImageButton menuExit;
    LinearLayout menuPopup;
    LinearLayout bottomBar;
    ConstraintLayout fullPage;
    TextView submitButton;
    TextView homeButton;
    TextView confirmText;
    LinearLayout confirmPopup;
    TextView confirmYes;
    TextView confirmNo;
    HelpPopup helpPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);
        // Hides system bars
        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        // Instantiate variables here \/
        autoButton = findViewById(R.id.auto_button);
        teleopButton = findViewById(R.id.teleop_button);
        endgameButton = findViewById(R.id.endgame_button);
        notesButton = findViewById(R.id.notes_button);
        helpButton = findViewById(R.id.help_button);
        menuBars = findViewById(R.id.menu_bars);
        menuExit = findViewById(R.id.menu_exit);
        menuPopup = findViewById(R.id.menu_popup);
        bottomBar = findViewById(R.id.bottom_bar);
        fullPage = findViewById(R.id.scouting_full_page);
        submitButton = findViewById(R.id.submit);
        homeButton = findViewById(R.id.home_page);
        confirmText = findViewById(R.id.confirm_text);
        confirmPopup = findViewById(R.id.confirm_popup);
        confirmYes = findViewById(R.id.confirm_yes);
        confirmNo = findViewById(R.id.confirm_no);
        helpPopup = findViewById(R.id.scouting_help_popup);

        // Inflate all fragments so it creates all data in the list
        replaceFragment(new FragmentTeleop());
        replaceFragment(new FragmentEndgame());
        replaceFragment(new FragmentNotes());
        replaceFragment(new FragmentAuto());

        // Page changes
        autoButton.setOnClickListener(v -> replaceFragment(new FragmentAuto()));
        teleopButton.setOnClickListener(v -> replaceFragment(new FragmentTeleop()));
        endgameButton.setOnClickListener(v -> replaceFragment(new FragmentEndgame()));
        notesButton.setOnClickListener(v -> replaceFragment(new FragmentNotes()));


        // Set the size and position of popups
        fullPage.post(new Runnable() {
            @Override
            public void run() {
                // Menu popup
                ViewGroup.MarginLayoutParams menuParams = (ViewGroup.MarginLayoutParams) menuPopup.getLayoutParams();
                menuParams.width = fullPage.getWidth()/2;
                menuParams.height = fullPage.getHeight()/6;
                menuParams.leftMargin = fullPage.getWidth()/50;
                menuParams.bottomMargin = bottomBar.getHeight() + fullPage.getHeight()/100;
                menuPopup.setLayoutParams(menuParams);

                // Confirm popup
                ViewGroup.MarginLayoutParams confirmParams = (ViewGroup.MarginLayoutParams) confirmPopup.getLayoutParams();
                confirmParams.width = (int) (fullPage.getWidth()/1.8);
                confirmParams.height = fullPage.getHeight()/3;
                confirmPopup.setLayoutParams(confirmParams);

                // Help popup
                ViewGroup.MarginLayoutParams helpParams = (ViewGroup.MarginLayoutParams) helpPopup.getLayoutParameters();
                helpParams.width = (int) (fullPage.getWidth()/1.1);
                helpParams.height = (int) (fullPage.getHeight()/1.1) - bottomBar.getHeight();
                helpParams.bottomMargin = bottomBar.getHeight() + fullPage.getHeight()/100;
                helpPopup.setLayoutParameters(helpParams);
            }
        });

        // help popup
        helpPopup.createHelp(helpButton);
        helpPopup.setRatio(4,5);

        // Menu popup
        menuBars.setOnClickListener(v -> {
            menuBars.setVisibility(GONE);
            menuExit.setVisibility(VISIBLE);
            menuPopup.setVisibility(VISIBLE);
            fullPage.setClickable(true);
        });
        menuExit.setOnClickListener(v -> {
            menuBars.setVisibility(VISIBLE);
            menuExit.setVisibility(GONE);
            menuPopup.setVisibility(GONE);
            fullPage.setClickable(false);
        });
        fullPage.setOnClickListener(v -> {
            menuBars.setVisibility(VISIBLE);
            menuExit.setVisibility(GONE);
            menuPopup.setVisibility(GONE);
            confirmPopup.setVisibility(GONE);
            fullPage.setClickable(false);
        });
        homeButton.setOnClickListener(v -> {
            menuBars.setVisibility(VISIBLE);
            menuExit.setVisibility(GONE);
            menuPopup.setVisibility(GONE);
            fullPage.setClickable(false);
            startActivity(new Intent(getApplicationContext(), ActivityMain.class));
        });
        submitButton.setOnClickListener(v -> {
            confirmPopup.setVisibility(VISIBLE);
            fullPage.setClickable(false);
            confirmText.setText(Values.checkData());
        });

        confirmYes.setOnClickListener(v -> {
            menuBars.setVisibility(VISIBLE);
            menuExit.setVisibility(GONE);
            menuPopup.setVisibility(GONE);
            confirmPopup.setVisibility(GONE);
            fullPage.setClickable(false);
            Values.exportData(getApplicationContext());
            Values.clearData();
            startActivity(new Intent(getApplicationContext(), ActivityMain.class));
        });

        confirmNo.setOnClickListener(v -> {
            confirmPopup.setVisibility(GONE);
            fullPage.setClickable(true);
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.replaceFrame, fragment);
        fragmentTransaction.commit();
    }
}