package com.fro.scoutingapp2025;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAuto extends Fragment {
    public FragmentAuto() {/* Required empty public constructor*/}

    // Declare global variables here \/
    RowTwoBoxes row1;
    RowLongBox row2;
    RowLongBox row3;
    RowTwoBoxes row4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        // Instantiate variables here \/
        row1 = rootView.findViewById(R.id.auto_row_1);
        row2 = rootView.findViewById(R.id.auto_row_2);
        row3 = rootView.findViewById(R.id.auto_row_3);
        row4 = rootView.findViewById(R.id.auto_row_4);


        /*  Setting Text Values  */
        // Row 1
        row1.createToggle("Auto_Leaves_Robot_Starting_Zone", Values.left);
        row1.createCounter("Auto_Reef_L1", 100, Values.right);

        // Row 2
        row2.createCounter("Auto_Reef_L2", 12, Values.left);
        row2.createCounter("Auto_Reef_L3", 12, Values.middle);
        row2.createCounter("Auto_Reef_L4", 12, Values.right);

        // Row 3
        row3.createCounter("Auto_Algae_Removed", 6, Values.left);
        row3.createCounter("Auto_Algae_Processor", 18, Values.middle);
        row3.createCounter("Auto_Algae_Net", 18, Values.right);

        // Row 4
        row4.createToggle("Auto_Picked_Up_From_Source", Values.left);
        row4.createToggle("Auto_Picked_Up_From_Ground", Values.right);

        // Inflate the layout for this fragment
        return rootView;
    }
}