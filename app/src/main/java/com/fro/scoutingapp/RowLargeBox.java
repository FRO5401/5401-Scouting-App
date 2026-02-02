package com.fro.scoutingapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Creates a row with one large box. <br/>
 * Usually, the layout weight of this row is 2 out of a weight sum of 5
 *
 * @requires app:text=""
 * <br> The text that will be displayed in the box header

 * @requires app:box_type=""
 * <br> The type of component that will be in the box
 * <ul>
 *     <li>text_type_box - Typed input</li>
 *     <li>number_type_box - Typed input with numbers only</li>
 *     <li>text_dropdown - A dropdown list</li>
 *     <li>team_number_dropdown - A searchable dropdown of team numbers</li>
 *     <li>toggle - An on/off switch</li>
 *     <li>counter - A plus/minus counter starting at 0, incremented by 1</li>
 *     <li>multi_counter - A plus/minus counter with multiple increment sets starting at 0. Can have 1/2/3 increment sets.</li>
 *     <li>stopwatch - A stopwatch with on, off, and reset</li>
 * </ul>
 */
public class RowLargeBox extends LinearLayout implements InterfaceSingleRow {
    // Declare variables
    TextView textView;
    ComponentFlipper flipper;
    boolean wantsPadding = true;

    public RowLargeBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_large_box, this);

        // Get values from xml input
        CharSequence text = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");
        CharSequence box =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type");

        // Init variables
        textView = findViewById(R.id.large_box_text);
        flipper = findViewById(R.id.large_box_flipper);

        // Sets the padding of the flipper
        int verticalChange = 10;
        int horizontalChange = 10;
        flipper.setPadding(verticalChange, horizontalChange, Values.large_box_flipper_height, Values.large_box_flipper_width);
        // The first time this row, gets the size of it and sets the flipper padding
        flipper.post(() -> {
            // If the flipper padding was set to 0 (aka the flipper is loaded for the first time)
            if (flipper.getPadding() == 0 && wantsPadding) {
                Values.large_box_flipper_width = flipper.getWidth();
                Values.large_box_flipper_height = flipper.getHeight();
                flipper.setPadding(verticalChange, horizontalChange, Values.large_box_flipper_height, Values.large_box_flipper_width);
            }
        });

        // Set text and box type
        setText(textView, text);
        setBox(flipper, box);
    }

    public void setText(TextView textview, CharSequence value) { textview.setText(value); }

    public void setBox(ComponentFlipper flipper, CharSequence value) {
        if (value == null) {
            Toast.makeText(this.getContext(), "Error: Vertical long box value is null", Toast.LENGTH_SHORT).show();
            return;
        }
        flipper.changeTo(value);
    }

    public void createTypeBox(String name, int inputType, int maxCharacters) {flipper.createTypeBox(name, inputType, maxCharacters);}

    public void createTextDropdown(String name, ArrayList<String> array) {flipper.createTextDropdown(name, array);}

    public void createTeamNumberDropdown(String name) {flipper.createTeamNumberDropdown(name);}

    public void createToggle(String name) {flipper.createToggle(name);}

    public void createCounter(String name, int maxValue) {flipper.createCounter(name, maxValue);}

    // With 1 increment set
    public void createMultiCounter(String name, int maxValue, int colorPattern, int incAmount1) {
        flipper.createMultiCounter(name, maxValue, colorPattern, incAmount1);
        textView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
        flipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        wantsPadding = false;
    }

    // With 2 increment sets
    public void createMultiCounter(String name, int maxValue, int colorPattern, int incAmount1, int incAmount2) {
        flipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);
        textView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
        flipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        wantsPadding = false;
    }

    // With 3 increment sets
    public void createMultiCounter(String name, int maxValue, int colorPattern, int incAmount1, int incAmount2, int incAmount3) {
        flipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);
        textView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
        flipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        wantsPadding = false;
    }

    // A stopwatch with on, off, and reset
    public void createStopwatch(String name) {flipper.createStopwatch(name);}

    public void pauseStopwatch() {flipper.pauseStopwatch();}
}
