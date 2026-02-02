package com.fro.scoutingapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Creates a row with one long multicolored box. <br/>
 * Usually, the layout weight of this row is 3 out of a weight sum of 5
 *
 * @requires app:row_amount=""
 * <br> The amount of rows that will be created on this component.
 * <br>If less than 5 rows are created, then text and box_types for those rows are unnecessary
 * <ul>
 *     <li>three_rows - Creates 3 rows</li>
 *     <li>four_rows - Creates 4 rows</li>
 *     <li>five_rows - Creates 5 rows</li>
 * <ul>
 *
 * @requires app:text_1=""
 * @requires app:text_2=""
 * @requires app:text_3=""
 * @requires app:text_4=""
 * @requires app:text_5=""
 * <br> The text that will be displayed in the headers on the left. Numbers are from 1-5, top-bottom</li>
 *
 * @requires app:box_type_1=""
 * @requires app:box_type_2=""
 * @requires app:box_type_3=""
 * @requires app:box_type_4=""
 * @requires app:box_type_5=""
 * <br> The type of component that will be in the boxes. Boxes are from 1-5, top-bottom
 * <ul>
 *     <li>text_type_box - Typed input</li>
 *     <li>number_type_box - Typed input with numbers only</li>
 *     <li>text_dropdown - A dropdown list</li>
 *     <li>team_number_dropdown - A searchable dropdown of team numbers</li>
 *     <li>toggle - An on/off switch</li>
 *     <li>counter - A plus/minus counter starting at 0</li>
 *     <li>multi_counter - A plus/minus counter with multiple increment sets starting at 0. Can have 1/2/3 increment sets.</li>
 *     <li>stopwatch - A stopwatch with on, off, and reset</li>
 */
public class RowVerticalLongBox extends LinearLayout implements InterfaceMultiRow {
    // Declare variables
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    ComponentFlipper flipper1;
    ComponentFlipper flipper2;
    ComponentFlipper flipper3;
    ComponentFlipper flipper4;
    ComponentFlipper flipper5;
    LinearLayout backgroundBox;
    LinearLayout layout4;
    LinearLayout layout5;

    boolean wantsPadding = true;

    public RowVerticalLongBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_vertical_long_box, this);

        // Get values from xml input
        CharSequence text1 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_1");
        CharSequence text2 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_2");
        CharSequence text3 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_3");
        CharSequence text4 =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_4");
        CharSequence text5 =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_5");
        CharSequence box1 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type_1");
        CharSequence box2 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type_2");
        CharSequence box3 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type_3");
        CharSequence box4 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type_4");
        CharSequence box5 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type_5");
        CharSequence rowAmount = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "row_amount");

        // Init variables
        textView1 = findViewById(R.id.vertical_long_box_text_1);
        textView2 = findViewById(R.id.vertical_long_box_text_2);
        textView3 = findViewById(R.id.vertical_long_box_text_3);
        textView4 = findViewById(R.id.vertical_long_box_text_4);
        textView5 = findViewById(R.id.vertical_long_box_text_5);
        flipper1 = findViewById(R.id.vertical_long_box_flipper_1);
        flipper2 = findViewById(R.id.vertical_long_box_flipper_2);
        flipper3 = findViewById(R.id.vertical_long_box_flipper_3);
        flipper4 = findViewById(R.id.vertical_long_box_flipper_4);
        flipper5 = findViewById(R.id.vertical_long_box_flipper_5);
        backgroundBox = findViewById(R.id.vertical_long_box_background);
        layout4 = findViewById(R.id.vertical_long_box_layout_4);
        layout5 = findViewById(R.id.vertical_long_box_layout_5);

        // Sets the padding of the flipper
        int verticalChange = 5;
        int horizontalChange = 15;
        flipper1.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
        flipper2.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
        flipper3.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
        flipper4.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
        flipper5.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
        // The first time this row is created, gets the size of it and sets the flipper padding
        flipper1.post(() -> {
            // If the flipper padding was set to 0 (aka the flipper is loaded for the first time)
            if (flipper1.getPadding() == 0 && wantsPadding) {
                Values.vertical_long_box_flipper_width = flipper1.getWidth();
                Values.vertical_long_box_flipper_height = flipper1.getHeight();
                flipper1.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
                flipper2.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
                flipper3.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
                flipper4.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
                flipper5.setPadding(verticalChange, horizontalChange, Values.vertical_long_box_flipper_height, Values.vertical_long_box_flipper_width);
            }
        });


        // Set text and box type for first 3 rows
        setText(textView1, text1);
        setText(textView2, text2);
        setText(textView3, text3);
        setBox(flipper1, box1);
        setBox(flipper2, box2);
        setBox(flipper3, box3);

        // If the user created 3 rows
        if (rowAmount.toString().equals("0")){
            layout4.setVisibility(GONE);
            layout5.setVisibility(GONE);
            backgroundBox.setWeightSum(3f);
        }
        // If the user created 4 rows
        else if (rowAmount.toString().equals("1")){
            layout5.setVisibility(GONE);
            setText(textView4, text4);
            setBox(flipper4, box4);
            backgroundBox.setWeightSum(4f);
        }
        // If the user created 5 rows
        else {
            setText(textView4, text4);
            setBox(flipper4, box4);
            setText(textView5, text5);
            setBox(flipper5, box5);
        }
    }

    public void setText(TextView textview, CharSequence value) { textview.setText(value); }

    public void setBox(ComponentFlipper flipper, CharSequence value) {
        if (value == null) {
            Toast.makeText(this.getContext(), "Error: Vertical long box value is null", Toast.LENGTH_SHORT).show();
            return;
        }
        flipper.changeTo(value);
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    public void createTypeBox(String name, int inputType, int maxCharacters, int position) {
        if (position == Values.vertical_level_1) {flipper1.createTypeBox(name, inputType, maxCharacters);}
        else if (position == Values.vertical_level_2) {flipper2.createTypeBox(name, inputType, maxCharacters);}
        else if (position == Values.vertical_level_3) {flipper3.createTypeBox(name, inputType, maxCharacters);}
        else if (position == Values.vertical_level_4) {flipper4.createTypeBox(name, inputType, maxCharacters);}
        else if (position == Values.vertical_level_5) {flipper5.createTypeBox(name, inputType, maxCharacters);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in type box "+name, Toast.LENGTH_SHORT).show();
        }
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    public void createTextDropdown(String name, ArrayList<String> array, int position) {
        if (position == Values.vertical_level_1) {flipper1.createTextDropdown(name, array);}
        else if (position == Values.vertical_level_2) {flipper2.createTextDropdown(name, array);}
        else if (position == Values.vertical_level_3) {flipper3.createTextDropdown(name, array);}
        else if (position == Values.vertical_level_4) {flipper4.createTextDropdown(name, array);}
        else if (position == Values.vertical_level_5) {flipper5.createTextDropdown(name, array);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in dropdown "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    public void createTeamNumberDropdown(String name, int position) {
        if (position == Values.vertical_level_1) {flipper1.createTeamNumberDropdown(name);}
        else if (position == Values.vertical_level_2) {flipper2.createTeamNumberDropdown(name);}
        else if (position == Values.vertical_level_3) {flipper3.createTeamNumberDropdown(name);}
        else if (position == Values.vertical_level_4) {flipper4.createTeamNumberDropdown(name);}
        else if (position == Values.vertical_level_5) {flipper5.createTeamNumberDropdown(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in dropdown "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    public void createToggle(String name, int position) {
        if (position == Values.vertical_level_1) {flipper1.createToggle(name);}
        else if (position == Values.vertical_level_2) {flipper2.createToggle(name);}
        else if (position == Values.vertical_level_3) {flipper3.createToggle(name);}
        else if (position == Values.vertical_level_4) {flipper4.createToggle(name);}
        else if (position == Values.vertical_level_5) {flipper5.createToggle(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in toggle "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    public void createCounter(String name, int maxValue, int position) {
        if (position == Values.vertical_level_1) {flipper1.createCounter(name, maxValue);}
        else if (position == Values.vertical_level_2) {flipper2.createCounter(name, maxValue);}
        else if (position == Values.vertical_level_3) {flipper3.createCounter(name, maxValue);}
        else if (position == Values.vertical_level_4) {flipper4.createCounter(name, maxValue);}
        else if (position == Values.vertical_level_5) {flipper5.createCounter(name, maxValue);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    // With 1 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1) {
        if (position == Values.vertical_level_1) {flipper1.createMultiCounter(name, maxValue, colorPattern, incAmount1);}
        else if (position == Values.vertical_level_2) {flipper2.createMultiCounter(name, maxValue, colorPattern, incAmount1);}
        else if (position == Values.vertical_level_3) {flipper3.createMultiCounter(name, maxValue, colorPattern, incAmount1);}
        else if (position == Values.vertical_level_4) {flipper4.createMultiCounter(name, maxValue, colorPattern, incAmount1);}
        else if (position == Values.vertical_level_5) {flipper5.createMultiCounter(name, maxValue, colorPattern, incAmount1);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}
        wantsPadding = false;
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    // With 2 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1, int incAmount2) {
        if (position == Values.vertical_level_1) {flipper1.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);}
        else if (position == Values.vertical_level_2) {flipper2.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);}
        else if (position == Values.vertical_level_3) {flipper3.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);}
        else if (position == Values.vertical_level_4) {flipper4.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);}
        else if (position == Values.vertical_level_5) {flipper5.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}
        wantsPadding = false;
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    // With 3 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1, int incAmount2, int incAmount3) {
        if (position == Values.vertical_level_1) {flipper1.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);}
        else if (position == Values.vertical_level_2) {flipper2.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);}
        else if (position == Values.vertical_level_3) {flipper3.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);}
        else if (position == Values.vertical_level_4) {flipper4.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);}
        else if (position == Values.vertical_level_5) {flipper5.createMultiCounter(name, maxValue, colorPattern, incAmount1, incAmount2, incAmount3);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in counter "+name, Toast.LENGTH_SHORT).show();}
        wantsPadding = false;
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    public void createStopwatch(String name, int position) {
        if (position == Values.vertical_level_1) {flipper1.createStopwatch(name);}
        else if (position == Values.vertical_level_2) {flipper2.createStopwatch(name);}
        else if (position == Values.vertical_level_3) {flipper3.createStopwatch(name);}
        else if (position == Values.vertical_level_4) {flipper4.createStopwatch(name);}
        else if (position == Values.vertical_level_5) {flipper5.createStopwatch(name);}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in stopwatch "+name, Toast.LENGTH_SHORT).show();}
    }
    /** @param position The level that this component is in. Levels go from top to bottom.
     *                  Keep in mind how many rows were created for this component.
     * <ul>
     *      <li>Values.vertical_level_1 - The component is in the top box</li>
     *      <li>Values.vertical_level_2 - The component is in the second from top box</li>
     *      <li>Values.vertical_level_3 - The component is in the third from top box</li>
     *      <li>Values.vertical_level_4 - The component is in the fourth from top box</li>
     *      <li>Values.vertical_level_5 - The component is in the fifth from top box</li>
     * </ul>
     */
    public void pauseStopwatch(int position) {
        if (position == Values.vertical_level_1) {flipper1.pauseStopwatch();}
        else if (position == Values.vertical_level_2) {flipper2.pauseStopwatch();}
        else if (position == Values.vertical_level_3) {flipper3.pauseStopwatch();}
        else if (position == Values.vertical_level_4) {flipper4.pauseStopwatch();}
        else if (position == Values.vertical_level_5) {flipper5.pauseStopwatch();}
        else{
            Toast.makeText(this.getContext(), "ERROR: Invalid position in stopwatch pause", Toast.LENGTH_SHORT).show();}
    }
}