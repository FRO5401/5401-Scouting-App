package com.fro.scoutingapp2025;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    // Layouts
    RelativeLayout fullPage;
    LinearLayout bottomBar;
    // Fragment buttons
    TextView autoButton;
    TextView teleopButton;
    TextView endgameButton;
    TextView notesButton;
    // Menu popup
    ImageButton menuButton;
    LinearLayout menuPopup;
    TextView homeButton;
    // Submit popup
    TextView submitButton;
    TextView confirmText;
    LinearLayout confirmPopup;
    TextView confirmYes;
    TextView confirmNo;
    // Help popup
    HelpPopup helpPopup;
    ImageButton helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);
        EdgeToEdge.enable(this);
        // Hides system bars
        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        // Instantiate variables here \/
        // Layouts
        fullPage = findViewById(R.id.scouting_full_page);
        bottomBar = findViewById(R.id.bottom_bar);
        // Fragment buttons
        autoButton = findViewById(R.id.auto_button);
        teleopButton = findViewById(R.id.teleop_button);
        endgameButton = findViewById(R.id.endgame_button);
        notesButton = findViewById(R.id.notes_button);
        // Menu popup
        menuButton = findViewById(R.id.menu_bars);
        menuPopup = findViewById(R.id.menu_popup);
        homeButton = findViewById(R.id.home_page);
        // Submit popup
        submitButton = findViewById(R.id.submit);
        confirmText = findViewById(R.id.confirm_text);
        confirmPopup = findViewById(R.id.confirm_popup);
        confirmYes = findViewById(R.id.confirm_yes);
        confirmNo = findViewById(R.id.confirm_no);
        // Help popup
        helpPopup = findViewById(R.id.scouting_help_popup);
        helpButton = findViewById(R.id.help_button);

<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
        /* Big changes here
>>>>>>> Stashed changes
=======
        /* Big changes here
>>>>>>> Stashed changes
        // Inflate all fragments so it creates all data in the list
        replaceFragment(new FragmentTeleop());
        replaceFragment(new FragmentEndgame());
        replaceFragment(new FragmentNotes());
        replaceFragment(new FragmentAuto());
<<<<<<< Updated upstream
<<<<<<< Updated upstream

        // Page changes
        autoButton.setOnClickListener(v -> replaceFragment(new FragmentAuto()));
        teleopButton.setOnClickListener(v -> replaceFragment(new FragmentTeleop()));
        endgameButton.setOnClickListener(v -> replaceFragment(new FragmentEndgame()));
        notesButton.setOnClickListener(v -> replaceFragment(new FragmentNotes()));

=======
=======
>>>>>>> Stashed changes

        // Page changes
        autoButton.setOnClickListener(v -> replaceFragment(new FragmentAuto()));
        teleopButton.setOnClickListener(v -> replaceFragment(new FragmentTeleop()));
        endgameButton.setOnClickListener(v -> replaceFragment(new FragmentEndgame()));
        notesButton.setOnClickListener(v -> replaceFragment(new FragmentNotes()));
        *  */

        // Fragments
        FragmentAuto fragmentAuto = new FragmentAuto();
        FragmentTeleop fragmentTeleop = new FragmentTeleop();
        FragmentEndgame fragmentEndgame = new FragmentEndgame();
        FragmentNotes fragmentNotes = new FragmentNotes();

        // Create the fragments so the data is made
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.replaceFrame, fragmentNotes)
                .add(R.id.replaceFrame, fragmentEndgame)
                .add(R.id.replaceFrame, fragmentTeleop)
                .add(R.id.replaceFrame, fragmentAuto)
                .commit();

        // After the screen loads, hide the fragments (this is so the pages actually exist first)
        screen.post(() -> {
            hideFragment(fragmentTeleop);
            hideFragment(fragmentEndgame);
            hideFragment(fragmentNotes);
        });
        // Show first fragment
        showFragment(fragmentAuto);

        // Page changes
        autoButton.setOnClickListener(v -> {
            if (currentFragment != fragmentAuto) {
                hideFragment(currentFragment);
                showFragment(fragmentAuto);
            }
        });
        teleopButton.setOnClickListener(v -> {
            if (currentFragment != fragmentTeleop) {
                hideFragment(currentFragment);
                showFragment(fragmentTeleop);
            }
        });
        endgameButton.setOnClickListener(v -> {
            if (currentFragment != fragmentEndgame) {
                hideFragment(currentFragment);
                showFragment(fragmentEndgame);
            }
        });
        notesButton.setOnClickListener(v -> {
            if (currentFragment != fragmentNotes) {
                hideFragment(currentFragment);
                showFragment(fragmentNotes);
            }
        });

        /* End of big changes
         * also comment or uncomment below? */

//        // Sets the margins of the popups
//        setPopupMargins();
//        // The first time this page is created, gets the height of the bottom bar and sets popup margins
//        screen.post(() -> {
//            // If the bottom bar height was never set (aka the this page loaded for the first time)
//            if (Values.bottom_bar_height == 0) {
//                Values.bottom_bar_height = bottomBar.getHeight();
//                setPopupMargins();
//            }
//        });
<<<<<<< Updated upstream
>>>>>>> Stashed changes

//        // Set the size and position of popups
//        fullPage.post(new Runnable() {
//            @Override
//            public void run() {
//                // Menu popup
//                ViewGroup.MarginLayoutParams menuParams = (ViewGroup.MarginLayoutParams) menuPopup.getLayoutParams();
//                menuParams.width = fullPage.getWidth()/2;
//                menuParams.height = fullPage.getHeight()/6;
//                menuParams.leftMargin = fullPage.getWidth()/50;
//                menuParams.bottomMargin = bottomBar.getHeight() + fullPage.getHeight()/100;
//                menuPopup.setLayoutParams(menuParams);
//
//                // Confirm popup
//                ViewGroup.MarginLayoutParams confirmParams = (ViewGroup.MarginLayoutParams) confirmPopup.getLayoutParams();
//                confirmParams.width = (int) (fullPage.getWidth()/1.8);
//                confirmParams.height = fullPage.getHeight()/3;
//                confirmPopup.setLayoutParams(confirmParams);
//
//                // Help popup
//                ViewGroup.MarginLayoutParams helpParams = (ViewGroup.MarginLayoutParams) helpPopup.getLayoutParameters();
//                helpParams.width = (int) (fullPage.getWidth()/1.1);
//                helpParams.height = (int) (fullPage.getHeight()/1.1) - bottomBar.getHeight();
//                helpParams.bottomMargin = bottomBar.getHeight() + fullPage.getHeight()/100;
//                helpPopup.setLayoutParameters(helpParams);
//            }
//        });
=======
>>>>>>> Stashed changes

        // help popup
        helpPopup.setVisibility(VISIBLE);
        helpPopup.createHelp(helpButton);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
//        helpPopup.setRatio(4,5);
=======
        helpPopup.setRatio(4, 5);
>>>>>>> Stashed changes
=======
        helpPopup.setRatio(4, 5);
>>>>>>> Stashed changes

        // Menu popup
        menuButton.setOnClickListener(v -> {
            menuPopup.setVisibility(VISIBLE);
            menuButton.setClickable(false);
            menuButton.setImageResource(R.drawable.menu_exit);
            fullPage.setClickable(true);
        });

        // Closing menu popup
        fullPage.setOnClickListener(v -> {
            menuButton.setClickable(true);
            menuButton.setImageResource(R.drawable.menu_bars);
            menuPopup.setVisibility(GONE);
            confirmPopup.setVisibility(GONE);
            fullPage.setClickable(false);
        });
        // Switch page to home
        homeButton.setOnClickListener(v -> {
            menuButton.setClickable(true);
            menuButton.setImageResource(R.drawable.menu_bars);
            menuPopup.setVisibility(GONE);
            fullPage.setClickable(false);
            startActivity(new Intent(getApplicationContext(), ActivityMain.class));
        });

        // Open confirmation screen
        submitButton.setOnClickListener(v -> {
            confirmPopup.setVisibility(VISIBLE);
            fullPage.setClickable(false);
            confirmText.setText(Values.checkData());
        });

        // Submit all data
        confirmYes.setOnClickListener(v -> {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
            /*
>>>>>>> Stashed changes
=======
            /*
>>>>>>> Stashed changes
            menuButton.setClickable(true);
            menuButton.setImageResource(R.drawable.menu_bars);
            menuPopup.setVisibility(GONE);
            confirmPopup.setVisibility(GONE);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            fullPage.setClickable(false);
=======
=======
>>>>>>> Stashed changes
            fullPage.setClickable(false); */

            // Hides current fragment to stop any background processes
            hideFragment(currentFragment);
            // Sets loading screen
            screen.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline_tinted, getApplicationContext().getTheme()));
            screen.setForegroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_grey, getApplicationContext().getTheme())));
            // Clears/exports data

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            Values.exportData(getApplicationContext());
            Values.clearData();
            startActivity(new Intent(getApplicationContext(), ActivityMain.class));
        });

        // Close confirmation screen
        confirmNo.setOnClickListener(v -> {
            confirmPopup.setVisibility(GONE);
            fullPage.setClickable(true);
        });
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.replaceFrame, fragment);
        fragmentTransaction.commit();
=======
=======
>>>>>>> Stashed changes
    /*    private void replaceFragment(Fragment fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.replaceFrame, fragment);
            fragmentTransaction.commit();
        } */

    private void createFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.replaceFrame, fragment)
                .commit();
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commit();
        currentFragment = fragment;
//        Toast.makeText(this, "Current: "+currentFragment, Toast.LENGTH_SHORT).show();
    }

    private void hideFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(fragment)
                .commit();
    }

    void setPopupMargins() {
        // Menu popup
        ViewGroup.MarginLayoutParams menuParams = (ViewGroup.MarginLayoutParams) menuPopup.getLayoutParams();
        menuParams.width = Values.screen_width / 2;
        menuParams.height = Values.screen_height / 6;
        menuParams.leftMargin = Values.screen_width / 50;
        menuParams.bottomMargin = Values.bottom_bar_height + Values.screen_height / 100;
        menuPopup.setLayoutParams(menuParams);

        // Confirm popup
        ViewGroup.MarginLayoutParams confirmParams = (ViewGroup.MarginLayoutParams) confirmPopup.getLayoutParams();
        confirmParams.width = (int) (Values.screen_width / 1.8);
        confirmParams.height = Values.screen_height / 3;
        confirmPopup.setLayoutParams(confirmParams);

        // Help popup
        ViewGroup.MarginLayoutParams helpParams = (ViewGroup.MarginLayoutParams) helpPopup.getLayoutParameters();
        helpParams.width = (int) (Values.screen_width / 1.1);
        helpParams.height = (int) (Values.screen_height / 1.1) - Values.bottom_bar_height;
        helpParams.bottomMargin = Values.bottom_bar_height + Values.screen_height / 100;
        helpPopup.setLayoutParameters(helpParams);
>>>>>>> Stashed changes
    }
}