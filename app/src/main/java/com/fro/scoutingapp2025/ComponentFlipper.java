package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class ComponentFlipper extends LinearLayout {

    ViewFlipper flipper;

    public ComponentFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.component_flipper, this);

        initComponents();

    }

    private void initComponents() {
        flipper = (ViewFlipper) findViewById(R.id.flipper);
    }

    public void changeTo(CharSequence value){
        if (value.equals("0")){while (flipper.getCurrentView() != findViewById(R.id.textTypeBox)){flipper.showNext();}}
        if (value.equals("1")){while (flipper.getCurrentView() != findViewById(R.id.numberTypeBox)){flipper.showNext();}}
        if (value.equals("2")){while (flipper.getCurrentView() != findViewById(R.id.textDropdown)){flipper.showNext();}}
        if (value.equals("3")){while (flipper.getCurrentView() != findViewById(R.id.numberDropdown)){flipper.showNext();}}
        if (value.equals("4")){while (flipper.getCurrentView() != findViewById(R.id.toggle)){flipper.showNext();}}
        if (value.equals("5")){while (flipper.getCurrentView() != findViewById(R.id.counter)){flipper.showNext();}}
        if (value.equals("6")){while (flipper.getCurrentView() != findViewById(R.id.stopwatch)){flipper.showNext();}}
    }
}
