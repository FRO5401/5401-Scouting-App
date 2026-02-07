package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class FragmentAuto extends Fragment {
    public FragmentAuto() {/* Required empty public constructor*/}

    // Declare variables
    RowLargeBox row1;
    RowLargeBox row2;
    RowTwoBoxes row3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.auto_row_1);
        row2 = rootView.findViewById(R.id.auto_row_2);
        row3 = rootView.findViewById(R.id.auto_row_3);

        // Row 1
        row1.createMultiCounter("Auto_Fuel_Scored", 1000, Values.colorType_greyWhiteGrey, 1,5);

        // Row 2
        row2.createMultiCounter("Auto_Fuel_Passed", 1000, Values.colorType_greyWhiteGrey, 1, 5);

        // Row 3
        row3.createToggle("Auto_Climbed_L1", Values.left);
        row3.createToggle("Auto_Used_Depot", Values.right);

        // Inflate the layout for this fragment
        return rootView;
    }
}