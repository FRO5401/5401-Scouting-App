package com.fro.scoutingapptest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentEndgame extends Fragment {
    public FragmentEndgame() {/* Required empty public constructor*/}

    // Declare variables
    RowLargeBox row1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_endgame, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.endgame_row_1);

        row1.createTextDropdown("Endgame_Climb_Type",
                new ArrayList<>(Arrays.asList("None", "L1", "L2", "L3")));


        // Inflate the layout for this fragment
        return rootView;
    }
}