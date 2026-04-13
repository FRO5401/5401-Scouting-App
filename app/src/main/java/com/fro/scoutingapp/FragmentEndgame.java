package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;


public class FragmentEndgame extends Fragment {
    public FragmentEndgame() {/* Required empty public constructor*/}

    // Declare variables
    RowLargeBox row1;
    RowLargeBox row2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_endgame, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.endgame_row_1);
        row2 = rootView.findViewById(R.id.endgame_row_2);

        // Row 1
        row1.createTextDropdown("Endgame_End_Position",  new ArrayList<>(Arrays.asList("L1", "L2", "L3", "Ground")));

        // Row 2
        row2.createTypeBox("Endgame_Seconds_Climbed_At",Values.inputType_number, 3);


        // Inflate the layout for this fragment
        return rootView;
    }
}