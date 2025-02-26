package com.fro.scoutingapp2025;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentTeleop extends Fragment {
    public FragmentTeleop() {/* Required empty public constructor*/}

    // Declare global variables here \/
    RowThreeBoxes row1;
    RowLongBox row2;
    RowLongBox row3;
    RowThreeBoxes row4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teleop, container, false);
        // Instantiate variables here \/
        row1 = rootView.findViewById(R.id.teleop_row_1);
        row2 = rootView.findViewById(R.id.teleop_row_2);
        row3 = rootView.findViewById(R.id.teleop_row_3);
        row4 = rootView.findViewById(R.id.teleop_row_4);

        /*  Setting Text Values  */
        // Row 1
        row1.createTextDropdown(
                new ArrayList<String>(Arrays.asList("Reef", "Ground", "Both")),
                "Teleop_Algae_Pickup_Location", Values.left);
        row1.createTextDropdown(
                new ArrayList<String>(Arrays.asList("Source", "Ground", "Both")),
                "Teleop_Coral_Pickup_Location", Values.middle);
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
        row4.createCounter("Teleop_Algae_Scored_Human_Player", 18, Values.left);
        row4.createCounter("Teleop_Algae_Missed_Human_Player", 18, Values.middle);
        row4.createStopwatch("Teleop_Defense_Stopwatch", Values.right);

        // Inflate the layout for this fragment
        return rootView;
    }
}