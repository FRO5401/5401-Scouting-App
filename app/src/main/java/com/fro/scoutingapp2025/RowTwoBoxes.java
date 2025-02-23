package com.fro.scoutingapp2025;

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
 *     <li>stopwatch - A stopwatch with on, off, and reset</li>
 * </ul>
 */
public class RowTwoBoxes extends LinearLayout {
    TextView leftTextView;
    TextView rightTextView;
    ComponentFlipper leftFlipper;
    ComponentFlipper rightFlipper;

    // public variables for the developer to set which box they are using
    public static int left = 0;
    public static int right = 1;

    public RowTwoBoxes(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_two_boxes, this);

        CharSequence leftText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_text");
        CharSequence rightText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_text");
        CharSequence leftBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_box_type");
        CharSequence rightBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_box_type");

        initComponents();

        leftFlipper.setPadding(5, 10);
        rightFlipper.setPadding(5, 10);

        setLeftText(leftText);
        setRightText(rightText);
        setLeftBox(leftBox);
        setRightBox(rightBox);
    }

    private void initComponents() {
        leftTextView = findViewById(R.id.leftText);
        rightTextView = findViewById(R.id.rightText);
        leftFlipper = findViewById(R.id.leftFlipper);
        rightFlipper = findViewById(R.id.rightFlipper);
    }

    public void setLeftText(CharSequence value) {
        leftTextView.setText(value);
    }

    public void setRightText(CharSequence value) {
        rightTextView.setText(value);
    }

    public void setLeftBox(CharSequence value) {
        if (value == null) {
            Toast.makeText(this.getContext(), "ERROR: Null pointer", Toast.LENGTH_SHORT).show();
            return;
        }
        leftFlipper.changeTo(value);
    }

    public void setRightBox(CharSequence value) {
        if (value == null) {
            Toast.makeText(this.getContext(), "ERROR: Null pointer", Toast.LENGTH_SHORT).show();
            return;
        }
        rightFlipper.changeTo(value);
    }

    public void createTextDropdown(ArrayList<String> array, String name, int position) {
        if (position == left) {leftFlipper.createTextDropdown(array, name);}
        else if (position == right) {rightFlipper.createTextDropdown(array, name);}
        else{Toast.makeText(this.getContext(), "ERROR: Invalid position in dropdown "+name, Toast.LENGTH_SHORT).show();}
    }

    public void createTextTypeBox(String name, int position) {
        if (position == left) {leftFlipper.createTextTypeBox(name);}
        else if (position == right) {rightFlipper.createTextTypeBox(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in text type box "+name, Toast.LENGTH_SHORT).show();
        }
    }
}