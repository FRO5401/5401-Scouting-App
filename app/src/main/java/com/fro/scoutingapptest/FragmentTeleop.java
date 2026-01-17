package com.fro.scoutingapptest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentTeleop extends Fragment {
    public FragmentTeleop() {/* Required empty public constructor*/}

    // Declare variables
    RowLargeBox row1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teleop, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.teleop_row_1);

        row1.createMultiCounter("Teleop_Fuel_Scored",400, Values.colorType_greyWhiteGrey, 1, 2,4);


        // Inflate the layout for this fragment
        return rootView;
    }

}