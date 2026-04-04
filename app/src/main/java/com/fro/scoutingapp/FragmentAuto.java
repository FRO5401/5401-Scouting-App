package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;


public class FragmentAuto extends Fragment {
    public FragmentAuto() {/* Required empty public constructor*/}

    //TODO Create this page

    // Declare variables
    RowLargeBox row1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.auto_row_1);

        row1.createCheckBox("Auto_Defense_Type", 3,
            new ArrayList<>(Arrays.asList("Zone Defense", "Fuel Starvation", "Robot v Robot")));

        // Inflate the layout for this fragment
        return rootView;
    }
}