package com.fro.scoutingapptest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Creates a row with one long multicolored box. <br/>
 * Usually, the layout weight of this row is 1 out of a weight sum of 5
 *
 * @requires app:color_pattern=""
 * <br> The color of the background and middle section</li>
 * <ul>
 *     <li> white_grey_white - White background with grey middle section</li>
 *     <li> grey_white_grey - Grey background with white middle section</li>
 * </ul>
 *
 * @requires app:left_text=""
 * <br> The text that will be displayed in the left box header</li>
 * @requires app:middle_text=""
 * <br> The text that will be displayed in the middle box header</li>
 * @requires app:right_text=""
 * <br> The text that will be displayed in the right box header</li>
 *
 * @requires app:left_box_type=""
 * @requires app:middle_box_type=""
 * @requires app:right_box_type=""
 * <br> The type of component that will be in the left/middle/right boxes
 * <ul>
 *     <li>text_type_box - Typed input</li>
 *     <li>number_type_box - Typed input with numbers only</li>
 *     <li>text_dropdown - A dropdown list</li>
 *     <li>team_number_dropdown - A searchable dropdown of team numbers</li>
 *     <li>toggle - An on/off switch</li>
 *     <li>counter - A plus/minus counter starting at 0</li>
 *     <li>stopwatch - A stopwatch with on, off, and reset</li>
 * </ul>
 */
public class RowLongBox extends LinearLayout {
    // Declare variables
    TextView leftTextView;
    TextView middleTextView;
    TextView rightTextView;
    ComponentFlipper leftFlipper;
    ComponentFlipper middleFlipper;
    ComponentFlipper rightFlipper;

    //Elements to change the box color
    View middleView1;
    View middleView2;
    LinearLayout middleLayout;
    LinearLayout backgroundLayout;
    boolean wantsPadding = true;

    public RowLongBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_long_box, this);

        // Get values from xml input
        CharSequence leftText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_text");
        CharSequence middleText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_text");
        CharSequence rightText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_text");
        CharSequence leftBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_box_type");
        CharSequence middleBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_box_type");
        CharSequence rightBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_box_type");
        CharSequence colorPattern = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "color_pattern");

        // Init variables
        leftTextView = findViewById(R.id.two_boxes_left_text);
        middleTextView = findViewById(R.id.two_boxes_middle_text);
        rightTextView = findViewById(R.id.two_boxes_right_text);
        leftFlipper = findViewById(R.id.two_boxes_left_flipper);
        middleFlipper = findViewById(R.id.two_boxes_middle_flipper);
        rightFlipper = findViewById(R.id.two_boxes_right_flipper);
        middleView1 = findViewById(R.id.two_boxes_middle_view_1);
        middleView2 = findViewById(R.id.two_boxes_middle_view_2);
        middleLayout = findViewById(R.id.two_boxes_middle_layout);
        backgroundLayout = findViewById(R.id.two_boxes_background_layout);

        // Sets the padding of the flipper
        leftFlipper.setPadding(10, 10, Values.long_box_flipper_height, Values.long_box_flipper_width);
        middleFlipper.setPadding(10, 10, Values.long_box_flipper_height, Values.long_box_flipper_width);
        rightFlipper.setPadding(10, 10, Values.long_box_flipper_height, Values.long_box_flipper_width);
        // The first time this row, gets the size of it and sets the flipper padding
        rightFlipper.post(() -> {
            // If the flipper padding was set to 0 (aka the flipper is loaded for the first time)
            if (rightFlipper.getPadding() == 0 & wantsPadding) {
                Values.long_box_flipper_width = rightFlipper.getWidth();
                Values.long_box_flipper_height = rightFlipper.getHeight();
                leftFlipper.setPadding(10, 10, Values.long_box_flipper_height, Values.long_box_flipper_width);
                middleFlipper.setPadding(10, 10, Values.long_box_flipper_height, Values.long_box_flipper_width);
                rightFlipper.setPadding(10, 10, Values.long_box_flipper_height, Values.long_box_flipper_width);
            }
        });

        // Set text, box type, and color pattern
        setText(leftTextView, leftText);
        setText(middleTextView, middleText);
        setText(rightTextView, rightText);
        setBox(leftFlipper, leftBox);
        setBox(middleFlipper, middleBox);
        setBox(rightFlipper, rightBox);
        setColorPattern(colorPattern);
    }

    public void setText(TextView textview, CharSequence value) { textview.setText(value); }

    public void setBox(ComponentFlipper flipper, CharSequence value) {
        if (value == null) {
            Toast.makeText(this.getContext(), "Error: Vertical long box value is null", Toast.LENGTH_SHORT).show();
            return;
        }
        flipper.changeTo(value);
    }

    public void setColorPattern(CharSequence value) {
        if (value == null) {return;}
        if (value.equals("0")){
            backgroundLayout.setBackgroundResource(R.drawable.box_background);
            middleView1.setBackgroundResource(R.color.light_grey);
            middleView2.setBackgroundResource(R.color.light_grey);
            middleLayout.setBackgroundResource(R.color.light_grey);
        }
        if (value.equals("1")){
            backgroundLayout.setBackgroundResource(R.drawable.box_background_grey);
            middleView1.setBackgroundResource(R.color.white);
            middleView2.setBackgroundResource(R.color.white);
            middleLayout.setBackgroundResource(R.color.white);
        }
    }

    public void createTypeBox(String name, int inputType, int maxCharacters, int position) {
        if (position == Values.left) {leftFlipper.createTypeBox(name, inputType, maxCharacters);}
        else if (position == Values.middle) {middleFlipper.createTypeBox(name, inputType, maxCharacters);}
        else if (position == Values.right) {rightFlipper.createTypeBox(name, inputType, maxCharacters);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in type box "+name, Toast.LENGTH_SHORT).show();
        }
    }

    public void createTextDropdown(String name, ArrayList<String> array, int position) {
        if (position == Values.left) {leftFlipper.createTextDropdown(name, array);}
        else if (position == Values.middle) {middleFlipper.createTextDropdown(name, array);}
        else if (position == Values.right) {rightFlipper.createTextDropdown(name, array);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in dropdown "+name, Toast.LENGTH_SHORT).show();}
    }

    public void createTeamNumberDropdown(String name, int position) {
        if (position == Values.left) {leftFlipper.createTeamNumberDropdown(name);}
        else if (position == Values.middle) {middleFlipper.createTeamNumberDropdown(name);}
        else if (position == Values.right) {rightFlipper.createTeamNumberDropdown(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in dropdown "+name, Toast.LENGTH_SHORT).show();}
    }

    public void createToggle(String name, int position) {
        if (position == Values.left) {leftFlipper.createToggle(name);}
        else if (position == Values.middle) {middleFlipper.createToggle(name);}
        else if (position == Values.right) {rightFlipper.createToggle(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in toggle "+name, Toast.LENGTH_SHORT).show();}
    }

    public void createCounter(String name, int maxValue, int position) {
        if (position == Values.left) {leftFlipper.createCounter(name, maxValue);}
        else if (position == Values.middle) {middleFlipper.createCounter(name, maxValue);}
        else if (position == Values.right) {rightFlipper.createCounter(name, maxValue);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}
    }

    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1, int incAmount2, int incAmount3) {
        if (position == Values.left) {
            leftFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);
            leftTextView.setLayoutParams( new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            leftFlipper.setLayoutParams( new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else if (position == Values.middle) {
            middleFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);
            middleTextView.setLayoutParams( new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            middleFlipper.setLayoutParams( new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else if (position == Values.right) {
            rightFlipper.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);
            rightTextView.setLayoutParams( new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,  1.0f));
            rightFlipper.setLayoutParams( new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,  4.0f));
        }
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}

        wantsPadding = false;
    }

    public void createStopwatch(String name, int position) {
        if (position == Values.left) {leftFlipper.createStopwatch(name);}
        else if (position == Values.middle) {middleFlipper.createStopwatch(name);}
        else if (position == Values.right) {rightFlipper.createStopwatch(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in stopwatch "+name, Toast.LENGTH_SHORT).show();}
    }

    public void pauseStopwatch(int position) {
        if (position == Values.left) {leftFlipper.pauseStopwatch();}
        else if (position == Values.middle) {middleFlipper.pauseStopwatch();}
        else if (position == Values.right) {rightFlipper.pauseStopwatch();}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in stopwatch pause ", Toast.LENGTH_SHORT).show();}
    }
}