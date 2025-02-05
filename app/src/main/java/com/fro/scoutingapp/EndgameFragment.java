package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class EndFragment extends Fragment {
    public EndFragment() {/* Required empty public constructor*/}

    // Declare global variables here \/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teleop, container, false);
        // Instantiate variables here \/


        // Inflate the layout for this fragment
        return rootView;
    }
}