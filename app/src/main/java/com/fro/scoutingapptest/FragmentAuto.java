package com.fro.scoutingapptest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentAuto extends Fragment {
    public FragmentAuto() {/* Required empty public constructor*/}

    // Declare variables
    RowLargeBox row1;
    RowLargeBox row2;
    RowThreeBoxes row3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.auto_row_1);
        row2 = rootView.findViewById(R.id.auto_row_2);

        row1.createTextDropdown(
                "Auto_Climb_Type",
                new ArrayList<>(Arrays.asList("None", "L1")));
        row2.createMultiCounter("Auto_Fuel_Scored",400, Values.colorType_greyWhiteGrey, 1, 2 ,4);

        row3.createCounter("Auto_Example", 400, Values.left);
        // Inflate the layout for this fragment
        return rootView;
    }
}