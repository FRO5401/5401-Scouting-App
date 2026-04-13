package com.fro.scoutingapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Creates a row with two medium sized boxes <br/>
 * Usually, the layout weight of this row is 1 out of a weight sum of 5
 *
 * @requires app:left_text=""
 * <br> The text that will be displayed in the left box header</li>
 * @requires app:right_text=""
 * <br> The text that will be displayed in the right box header</li>
 *
 * @requires app:left_box_type=""
 * @requires app:right_box_type=""
 * <br> The type of component that will be in the left/middle/right boxes
 * <ul>
 *     <li>text_type_box - Typed input</li>
 *     <li>number_type_box - Typed input with numbers only</li>
 *     <li>text_dropdown - A dropdown list</li>
 *     <li>team_number_dropdown - A searchable dropdown of team numbers</li>
 *     <li>toggle - An on/off switch</li>
 *     <li>counter - A plus/minus counter starting at 0</li>
 *     <li>multi_counter - A plus/minus counter with multiple increment sets starting at 0. Can have 1/2/3 increment sets.</li>
 *     <li>stopwatch - A stopwatch with on, off, and reset</li>
 * </ul>
 */
public class RowTwoBoxes extends LinearLayout implements InterfaceMultiRow {
    // Declare variables
    TextView leftTextView;
    TextView rightTextView;
    ComponentFlipper leftFlipper;
    ComponentFlipper rightFlipper;
    boolean wantsPadding = true;

    public RowTwoBoxes(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_two_boxes, this);

        // Get values from xml input
        CharSequence leftText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_text");
        CharSequence rightText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_text");
        CharSequence leftBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_box_type");
        CharSequence rightBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_box_type");

        // Init variables
        leftTextView = findViewById(R.id.two_boxes_left_text);
        rightTextView = findViewById(R.id.two_boxes_right_text);
        leftFlipper = findViewById(R.id.two_boxes_left_flipper);
        rightFlipper = findViewById(R.id.two_boxes_right_flipper);

        // Sets the padding of the flipper
        int verticalChange = 10;
        int horizontalChange = 10;
        leftFlipper.setPadding(verticalChange, horizontalChange, Values.two_boxes_flipper_height, Values.two_boxes_flipper_width);
        rightFlipper.setPadding(verticalChange, horizontalChange, Values.two_boxes_flipper_height, Values.two_boxes_flipper_width);
        // The first time this row, gets the size of it and sets the flipper padding
        rightFlipper.post(() -> {
            // If the flipper padding was set to 0 (aka the flipper is loaded for the first time)
            if (rightFlipper.getPadding() == 0 && wantsPadding) {
                Values.two_boxes_flipper_width = rightFlipper.getWidth();
                Values.two_boxes_flipper_height = rightFlipper.getHeight();
                leftFlipper.setPadding(verticalChange, horizontalChange, Values.two_boxes_flipper_height, Values.two_boxes_flipper_width);
                rightFlipper.setPadding(verticalChange, horizontalChange, Values.two_boxes_flipper_height, Values.two_boxes_flipper_width);
            }
        });

        // Set text and box type
        setText(leftTextView, leftText);
        setText(rightTextView, rightText);
        setBox(leftFlipper, leftBox);
        setBox(rightFlipper, rightBox);
    }

    public void setText(TextView textview, CharSequence value) { textview.setText(value); }

    public void setBox(ComponentFlipper flipper, CharSequence value) {
        if (value == null) {
            Toast.makeText(this.getContext(), "Error: Vertical long box value is null", Toast.LENGTH_SHORT).show();
            return;
        }
        flipper.changeTo(value);
    }

    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createTextDropdown(String name, ArrayList<String> array, int position) {
        if (position == Values.left) {leftFlipper.createTextDropdown(name, array);}
        else if (position == Values.right) {rightFlipper.createTextDropdown(name, array);}
        else{Toast.makeText(this.getContext(), "ERROR: Invalid position in dropdown "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createTypeBox(String name, int inputType, int maxCharacters, int position) {
        if (position == Values.left) {leftFlipper.createTypeBox(name, inputType, maxCharacters);}
        else if (position == Values.right) {rightFlipper.createTypeBox(name, inputType, maxCharacters);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in text type box "+name, Toast.LENGTH_SHORT).show();
        }
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createTeamNumberDropdown(String name, int position) {
        if (position == Values.left) {leftFlipper.createTeamNumberDropdown(name);}
        else if (position == Values.right) {rightFlipper.createTeamNumberDropdown(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in dropdown "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createToggle(String name, int position) {
        if (position == Values.left) {leftFlipper.createToggle(name);}
        else if (position == Values.right) {rightFlipper.createToggle(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in toggle "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createCounter(String name, int maxValue, int position) {
        if (position == Values.left) {leftFlipper.createCounter(name, maxValue);}
        else if (position == Values.right) {rightFlipper.createCounter(name, maxValue);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    // With 1 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1) {
        if (position == Values.left) {
            leftFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1);
            leftTextView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            leftFlipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else if (position == Values.right) {
            rightFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1);
            rightTextView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            rightFlipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}

        wantsPadding = false;
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    // With 2 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1, int incAmount2) {
        if (position == Values.left) {
            leftFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);
            leftTextView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            leftFlipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else if (position == Values.right) {
            rightFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);
            rightTextView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            rightFlipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}

        wantsPadding = false;
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    // With 3 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1, int incAmount2, int incAmount3) {
        if (position == Values.left) {
            leftFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);
            leftTextView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            leftFlipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else if (position == Values.right) {
            rightFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);
            rightTextView.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            rightFlipper.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}

        wantsPadding = false;
    }
    /** @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createStopwatch(String name, int position) {
        if (position == Values.left) {leftFlipper.createStopwatch(name);}
        else if (position == Values.right) {rightFlipper.createStopwatch(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in stopwatch "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The box that this component is in.
     * <ul>
     *      <li>Values.left - The component is in the left box</li>
     *      <li>Values.right - The component is in the right box</li>
     * </ul>
     */
    public void pauseStopwatch(int position) {
        if (position == Values.left) {leftFlipper.pauseStopwatch();}
        else if (position == Values.right) {rightFlipper.pauseStopwatch();}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in stopwatch pause ", Toast.LENGTH_SHORT).show();}
    }

    public void createCheckBox(String name, int amountOfCheckboxes, ArrayList<String> checkboxNames, int position) {
        if (position == Values.left) {leftFlipper.createCheckBox(name, amountOfCheckboxes, checkboxNames);}
        else if (position == Values.right) {rightFlipper.createCheckBox(name, amountOfCheckboxes, checkboxNames);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in checkbox "+name, Toast.LENGTH_SHORT).show();}
    }

    public void createSlider(String name, int minValue, int maxValue, double stepValue, int position) {
        if (position == Values.left) {leftFlipper.createSlider(name, minValue, maxValue, stepValue);}
        else if (position == Values.right) {rightFlipper.createSlider(name, minValue, maxValue, stepValue);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in checkbox "+name, Toast.LENGTH_SHORT).show();}
    }
}