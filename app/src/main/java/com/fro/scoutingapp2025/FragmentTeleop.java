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

    // Declare global variables here \/
    RowThreeBoxes row1;
    RowLongBox row2;
    RowLongBox row3;
    RowThreeBoxes row4_processor;
    RowTwoBoxes row4_source;
    RowTwoBoxes row4_none;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teleop, container, false);
        // Instantiate variables here \/
        row1 = rootView.findViewById(R.id.teleop_row_1);
        row2 = rootView.findViewById(R.id.teleop_row_2);
        row3 = rootView.findViewById(R.id.teleop_row_3);
        row4_processor = rootView.findViewById(R.id.teleop_row_4_processor);
        row4_source = rootView.findViewById(R.id.teleop_row_4_source);
        row4_none = rootView.findViewById(R.id.teleop_row_4_none);

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
        // Human player values: Dropdown = 0, Left Coral Station = 1, Right Coral Station = 2, Processor = 3, Can't Tell = 4
         if ((int) Values.data.get("Main_Human_Player_Position") == 1 || (int) Values.data.get("Main_Human_Player_Position") == 2){
             row4_source.setVisibility(GONE);
             row4_none.setVisibility(GONE);
             row4_processor.setVisibility(VISIBLE);
             row4_processor.createCounter("Teleop_Algae_Scored_Human_Player", 18, Values.left);
             row4_processor.createCounter("Teleop_Algae_Missed_Human_Player", 18, Values.middle);
             row4_processor.createStopwatch("Teleop_Defense_Stopwatch", Values.right);
        }
        else if ((int) Values.data.get("Main_Human_Player_Position") == 3){
            row4_processor.setVisibility(GONE);
             row4_none.setVisibility(GONE);
             row4_source.setVisibility(VISIBLE);
            row4_source.createTextDropdown(
                    new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5")),
                    "Teleop_Human_Player_Feeding_Accuracy", Values.left);
            row4_source.createStopwatch("Teleop_Defense_Stopwatch", Values.right);
        }
        else {
            row4_source.setVisibility(GONE);
            row4_processor.setVisibility(GONE);
            row4_none.setVisibility(VISIBLE);
        }

        // Inflate the layout for this fragment
        return rootView;
    }
}