package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Creates a row with three smaller boxes <br/>
 * Usually, the layout weight of this row is 1 out of a weight sum of 5
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
public class RowThreeBoxes extends LinearLayout {

    TextView leftTextView;
    TextView middleTextView;
    TextView rightTextView;
    ComponentFlipper leftFlipper;
    ComponentFlipper middleFlipper;
    ComponentFlipper rightFlipper;

    public RowThreeBoxes(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_three_boxes, this);

        CharSequence leftText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_text");
        CharSequence middleText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_text");
        CharSequence rightText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_text");
        CharSequence leftBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_box_type");
        CharSequence middleBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_box_type");
        CharSequence rightBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_box_type");

        initComponents();

        leftFlipper.setPadding(10, 10);
        middleFlipper.setPadding(10, 10);
        rightFlipper.setPadding(10, 10);

        setLeftText(leftText);
        setMiddleText(middleText);
        setRightText(rightText);
        setLeftBox(leftBox);
        setMiddleBox(middleBox);
        setRightBox(rightBox);
    }

    private void initComponents() {
        leftTextView = findViewById(R.id.leftText);
        middleTextView = findViewById(R.id.middleText);
        rightTextView = findViewById(R.id.rightText);
        leftFlipper = findViewById(R.id.leftFlipper);
        middleFlipper = findViewById(R.id.middleFlipper);
        rightFlipper = findViewById(R.id.rightFlipper);
    }

    public void setLeftText(CharSequence value) {
        leftTextView.setText(value);
    }

    public void setMiddleText(CharSequence value) {
        middleTextView.setText(value);
    }

    public void setRightText(CharSequence value) {
        rightTextView.setText(value);
    }

    public void setLeftBox(CharSequence value) {
        if (value == null) {return;}
        leftFlipper.changeTo(value);
    }
    public void setMiddleBox(CharSequence value) {
        if (value == null) {return;}
        middleFlipper.changeTo(value);
    }
    public void setRightBox(CharSequence value) {
        if (value == null) {return;}
        rightFlipper.changeTo(value);
    }

    public void createTypeBox(String name, int inputType, int position) {
        if (position == Values.left) {leftFlipper.createTypeBox(name, inputType);}
        else if (position == Values.middle) {middleFlipper.createTypeBox(name, inputType);}
        else if (position == Values.right) {rightFlipper.createTypeBox(name, inputType);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in type box "+name, Toast.LENGTH_SHORT).show();
        }
    }

    public void createTextDropdown(ArrayList<String> array, String name, int position) {
        if (position == Values.left) {leftFlipper.createTextDropdown(array, name);}
        else if (position == Values.middle) {middleFlipper.createTextDropdown(array, name);}
        else if (position == Values.right) {rightFlipper.createTextDropdown(array, name);}
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
}
