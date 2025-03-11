package com.fro.scoutingapp2025;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentTemplate extends Fragment {
    public FragmentTemplate() {/* Required empty public constructor*/}

    // Declare global variables here \/
    RowTwoBoxes row1;
    RowThreeBoxes row2;
    RowLongBox row3;
    RowLargeBox row4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_template, container, false);
        // Instantiate variables here \/
        row1 = rootView.findViewById(R.id.template_row_1);
        row2 = rootView.findViewById(R.id.template_row_2);
        row3 = rootView.findViewById(R.id.template_row_3);
        row4 = rootView.findViewById(R.id.template_row_4);

        createRows();

        // Inflate the layout for this fragment
        return rootView;
    }

    public void createRows(){
        // create the rows here
    }
}