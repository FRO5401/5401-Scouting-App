package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentExample extends Fragment {
    public FragmentExample() {/* Required empty public constructor*/}

    // Declare global variables here \/
    //TODO Declare each row as row1, row2, etc
    RowTwoBoxes row1;
    RowThreeBoxes row2;
    RowLongBox row3;
    RowLargeBox row4;

    //TODO -- DELETE THIS FILE AND fragment_example.xml BEFORE APP USE !!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_example, container, false);
        // Instantiate variables here \/
        //TODO Instantiate through the root view
        row1 = rootView.findViewById(R.id.template_row_1);
        row2 = rootView.findViewById(R.id.template_row_2);
        row3 = rootView.findViewById(R.id.template_row_3);
        row4 = rootView.findViewById(R.id.template_row_4);


        //TODO Create each box in the row. Hover over the create function to view explanations

        /*   Row 1   */
        row1.createCounter("Template_Points_Scored", 30, Values.left);
        row1.createToggle("Template_Robot_Did_Thing", Values.right);

        /*   Row 2   */
        row2.createTextDropdown("Template_Dropdown_Option",
                new ArrayList<>(Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4")),
                Values.left);
        row2.createTeamNumberDropdown("Template_Team_Number", Values.middle);
        row3.createStopwatch("Template_Seconds_Passed", Values.right);

        /*   Row 3   */
        row3.createTypeBox("Template_Inputted_Text",Values.inputType_text, 40, Values.left);
        row3.createCounter("Template_Points_Not_Scored", 10, Values.middle);

        /*   Row 4   */
        row4.createMultiCounter("Template_Pieces_Scored", 400, Values.colorType_greyWhiteGrey, 2,4);

        // Inflate the layout for this fragment
        return rootView;
    }

    //TODO If you have a stopwatch on the fragment, the 5 lines below are required
    @Override
    public void onHiddenChanged(boolean hidden) {
        row3.pauseStopwatch(Values.right);
        super.onHiddenChanged(hidden);
    }
}