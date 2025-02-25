package com.fro.scoutingapp2025;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentNotes extends Fragment {
    public FragmentNotes() {/* Required empty public constructor*/}

    // Declare global variables here \/
    RowLongBox row1;
    RowLargeBox row2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        // Instantiate variables here \/
        row1 = rootView.findViewById(R.id.notes_row_1);
        row2 = rootView.findViewById(R.id.notes_row_2);

        /*  Setting Text Values  */
        // Row 1
//        row1.createToggle("Endgame_Did_Robot_Break", Values.left);
//        row1.createToggle("Endgame_Was_Robot_Tipped", Values.middle);
//        row1.createToggle("Endgame_Any_Penalties", Values.right);

        // Row 2
        row2.createTypeBox("Notes_Extra_Comments", Values.inputType_text);

        // Inflate the layout for this fragment
        return rootView;
    }
}