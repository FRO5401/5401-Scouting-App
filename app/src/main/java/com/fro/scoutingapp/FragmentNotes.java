package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentNotes extends Fragment {
    public FragmentNotes() {/* Required empty public constructor*/}

    // Declare variables
    RowLongBox row1;
    RowLargeBox row2;
    RowLargeBox row3;
    RowLargeBox row4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.notes_row_1);
        row2 = rootView.findViewById(R.id.notes_row_2);
        row3 = rootView.findViewById(R.id.notes_row_3);
        row4 = rootView.findViewById(R.id.notes_row_4);

        // Row 1
        row1.createToggle("Notes_Did_Robot_Break", Values.left);
        row1.createToggle("Notes_Did_Robot_Get_Stuck", Values.middle);
        row1.createToggle("Notes_Any_Penalties", Values.right);

        // Row 2
        row2.createCheckBox("Notes_Defense_Type", 3, new ArrayList<>(Arrays.asList("Zone Defense", "Fuel Starvation", "Robot vs Robot")));

        // Row 1
        row3.createSlider("Notes_Defense_Rating", 0, 5, 1);

        // Row 4
        row4.createTypeBox("Notes_Extra_Comments", Values.inputType_text, 2000);

        // Inflate the layout for this fragment
        return rootView;
    }
}