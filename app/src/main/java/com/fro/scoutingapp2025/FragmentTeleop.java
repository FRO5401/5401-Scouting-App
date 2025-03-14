package com.fro.scoutingapp2025;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentTeleop extends Fragment {
    public FragmentTeleop() {/* Required empty public constructor*/}

    // Declare variables
    RowThreeBoxes row1;
    RowLongBox row2;
    RowLongBox row3;
    RowThreeBoxes row4_processor;
    RowTwoBoxes row4_default;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teleop, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.teleop_row_1);
        row2 = rootView.findViewById(R.id.teleop_row_2);
        row3 = rootView.findViewById(R.id.teleop_row_3);
        row4_processor = rootView.findViewById(R.id.teleop_row_4_processor);
        row4_default = rootView.findViewById(R.id.teleop_row_4_default);

        // Row 1
        row1.createTextDropdown(
                "Teleop_Algae_Pickup_Location",
                new ArrayList<String>(Arrays.asList("Reef", "Ground", "Both", "None")),
                Values.left);
        row1.createTextDropdown(
                "Teleop_Coral_Pickup_Location",
                new ArrayList<String>(Arrays.asList("Source", "Ground", "Both", "None")),
                Values.middle);
        row1.createCounter("Teleop_Reef_L1", 100, Values.right);

        // Row 2
        row2.createCounter("Teleop_Reef_L2", 12, Values.left);
        row2.createCounter("Teleop_Reef_L3", 12, Values.middle);
        row2.createCounter("Teleop_Reef_L4", 12, Values.right);

        // Row 3
        row3.createCounter("Teleop_Algae_Removed", 6, Values.left);
        row3.createCounter("Teleop_Algae_Processor", 18, Values.middle);
        row3.createCounter("Teleop_Algae_Net", 18, Values.right);

        // Row 4
        // Creating values even if visibility gone, so the json file stays the same
        row4_processor.createCounter("Teleop_Algae_Scored_Human_Player", 18, Values.left);
        row4_processor.createCounter("Teleop_Algae_Missed_Human_Player", 18, Values.middle);
        row4_processor.createStopwatch("Teleop_Defense_Stopwatch", Values.right);
        row4_default.createStopwatch("Teleop_Defense_Stopwatch", Values.right);

        // Setting which human player row is visible
        if (Values.data.get("Main_Human_Player_Position") == "Processor") {
            row4_processor.setVisibility(VISIBLE);
            row4_default.setVisibility(GONE);
        } else {
            row4_processor.setVisibility(GONE);
            row4_default.setVisibility(VISIBLE);
        }

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStop() {
        row4_default.pauseStopwatch(Values.right);
        super.onStop();
    }
}