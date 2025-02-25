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

        // Row 2

        // Row 3

        // Row 4

        // Inflate the layout for this fragment
        return rootView;
    }
}