package com.fro.scoutingapp2025;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentEndgame extends Fragment {
    public FragmentEndgame() {/* Required empty public constructor*/}

    // Declare global variables here \/
    RowThinLargeBox row1;
    RowThinLargeBox row2;
    RowThinLargeBox row3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_endgame, container, false);
        // Instantiate variables here \/
        row1 = rootView.findViewById(R.id.endgame_row_1);
        row2 = rootView.findViewById(R.id.endgame_row_2);
        row3 = rootView.findViewById(R.id.endgame_row_3);

        /*  Setting Text Values  */
        row1.createTextDropdown(
                new ArrayList<String>(Arrays.asList("Parked", "Shallow Cage", "Deep Cage", "Not In Barge Zone")),
                "Endgame_End_Location");

        row2.createTextDropdown(
                new ArrayList<String>(Arrays.asList("Scoring Coral", "Scoring Algae", "Climbing", "Defense", "Other", "Nothing")),
                "Endgame_Robot_Spent_Last_10_Seconds_Doing");

        row3.createTypeBox("Endgame_Started_Climbing_At_This_Time", Values.inputType_number);

        // Inflate the layout for this fragment
        return rootView;
    }
}