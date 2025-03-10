package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Creates a row with one large box. <br/>
 * Usually, the layout weight of this row is 2 out of a weight sum of 5
 *
 * @requires app:text=""
 * <br> The text that will be displayed in the box header
 *
 * @requires app:box_type=""
 * <br> The type of component that will be in the box
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
public class RowThinLargeBox extends LinearLayout {

    TextView textView;
    ComponentFlipper flipper;

    public RowThinLargeBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_thin_large_box, this);

        CharSequence text = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");
        CharSequence box = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type");

        initComponents();

        // Sets the padding of the flipper
        flipper.setPadding(10, 10, Values.thin_large_box_flipper_height, Values.thin_large_box_flipper_width);
        // The first time this row, gets the size of it and sets the flipper padding
        flipper.post(() -> {
            // If the flipper padding was set to 0 (aka the flipper is loaded for the first time)
            if (flipper.getPadding() == 0) {
                Values.thin_large_box_flipper_width = flipper.getWidth();
                Values.thin_large_box_flipper_height = flipper.getHeight();
                flipper.setPadding(10, 10, Values.thin_large_box_flipper_height, Values.thin_large_box_flipper_width);
            }
        });

        setText(text);
        setBox(box);
    }

    private void initComponents() {
        textView = findViewById(R.id.oneText);
        flipper = findViewById(R.id.oneFlipper);
    }

    public void setText(CharSequence value) {
        textView.setText(value);
    }

    public void setBox(CharSequence value) {
        if (value == null) {
            return;
        }
        flipper.changeTo(value);
    }

    public void createTypeBox(String name, int inputType, int  maxCharacters) {flipper.createTypeBox(name, inputType, maxCharacters);}

    public void createTextDropdown(String name, ArrayList<String> array) {flipper.createTextDropdown(name, array);}

    public void createTeamNumberDropdown(String name) {flipper.createTeamNumberDropdown(name);}

    public void createToggle(String name, int position) {flipper.createToggle(name);}

    public void createCounter(String name, int maxValue) {flipper.createCounter(name, maxValue);}

    public void createStopwatch(String name) {flipper.createStopwatch(name);}
}
