package com.fro.scoutingapp2025;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentTemplate extends Fragment {
    public FragmentTemplate() {/* Required empty public constructor*/}

    // Declare global variables here \/
    RowTwoBoxes rowTwoBoxes;
    RowThreeBoxes rowThreeBoxes;
    RowLongBox rowLongBox;
    RowLargeBox rowLargeBox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_template, container, false);
        // Instantiate variables here \/
        rowTwoBoxes = rootView.findViewById(R.id.rowTwoBoxes);
        rowThreeBoxes = rootView.findViewById(R.id.rowThreeBoxes);
        rowLongBox = rootView.findViewById(R.id.rowLongBox);
        rowLargeBox = rootView.findViewById(R.id.rowLargeBox);

        // Row two boxes
        rowTwoBoxes.createTextDropdown(
                new ArrayList<String>(Arrays.asList("two 1", "two 2", "two 3")),
                "Test_Two_Box_Left", Values.left);
        rowTwoBoxes.createTextDropdown(
                new ArrayList<String>(Arrays.asList("two 4", "two 5", "two 6")),
                "Test_Two_Box_Right", Values.right);

        // Three boxes
        rowThreeBoxes.createTextDropdown(
                new ArrayList<String>(Arrays.asList("three 1", "three 2", "three 3")),
                "Test_Three_Box_Left", Values.left);
        rowThreeBoxes.createTextDropdown(
                new ArrayList<String>(Arrays.asList("three 4", "three 5", "three 6")),
                "Test_Three_Box_Middle", Values.middle);
        rowThreeBoxes.createTextDropdown(
                new ArrayList<String>(Arrays.asList("three 7", "three 8", "three 9")),
                "Test_Three_Box_Right", Values.right);

        // Row Long box
        rowLongBox.createTextDropdown(
                new ArrayList<String>(Arrays.asList("long 1", "long 2", "long 3")),
                "Test_Long_Left_Box", Values.left);
        rowLongBox.createTextDropdown(
                new ArrayList<String>(Arrays.asList("long 4", "long 5", "long 6")),
                "Test_Long_Middle_Box", Values.middle);
        rowLongBox.createTextDropdown(
                new ArrayList<String>(Arrays.asList("long 7", "long 8", "long 9")),
                "Test_Long_Right_Box", Values.right);

        // Row large box
        rowLargeBox.createTextDropdown(
                new ArrayList<String>(Arrays.asList("large 1", "large 2", "large 3")),
                "Test_Large_Box");

        // Inflate the layout for this fragment
        return rootView;
    }
}