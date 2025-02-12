package com.fro.scoutingapp2025;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAuto extends Fragment {
    public FragmentAuto() {/* Required empty public constructor*/}

    // Declare global variables here \/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        // Instantiate variables here \/


        // Inflate the layout for this fragment
        return rootView;
    }
}