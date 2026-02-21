package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragmentTeleop extends Fragment {
    public FragmentTeleop() {/* Required empty public constructor*/}


    // Declare variables
    RowLargeBox row1;
    RowLargeBox row2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teleop, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.teleop_row_1);
        row2 = rootView.findViewById(R.id.teleop_row_2);

        // Row 1
        row1.createMultiCounter("Teleop_Fuel_Scored", 1500, Values.colorType_greyWhiteGrey, 1, 5);

        // Row 2
        row2.createCounter("Teleop_Times_Fuel_Stored_Outpost", 50);

        // Inflate the layout for this fragment
        return rootView;
    }

}