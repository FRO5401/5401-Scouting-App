package com.fro.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragmentNotes extends Fragment {
    public FragmentNotes() {/* Required empty public constructor*/}

    // Declare variables


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        // Init variables


        // Inflate the layout for this fragment
        return rootView;
    }
}