package com.fro.scoutingapptest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentNotes extends Fragment {
    public FragmentNotes() {/* Required empty public constructor*/}

    // Declare variables
    RowLongBox row1;
    RowLargeBox row2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        // Init variables
        row1 = rootView.findViewById(R.id.notes_row_1);
        row2 = rootView.findViewById(R.id.notes_row_2);

        // Row 1
        row1.createToggle("Notes_Did_Robot_Break", Values.left);
        row1.createToggle("Notes_Was_Robot_Tipped", Values.middle);
        row1.createToggle("Notes_Any_Penalties", Values.right);

        // Row 2
        row2.createTypeBox("Notes_Extra_Comments", Values.inputType_text, 2000);

        // Inflate the layout for this fragment
        return rootView;
    }
}